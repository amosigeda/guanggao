package com.ymei_inc.ymadvert.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.ymei_inc.ymadvert.App;
import com.ymei_inc.ymadvert.dao.IBusinessDao;
import com.ymei_inc.ymadvert.dao.ISaveLogDao;
import com.ymei_inc.ymadvert.ip.IPSeeker;
import com.ymei_inc.ymadvert.model.AdvSent;
import com.ymei_inc.ymadvert.model.BlackWhite;
import com.ymei_inc.ymadvert.model.Channel;
import com.ymei_inc.ymadvert.model.City;
import com.ymei_inc.ymadvert.model.Constant;
import com.ymei_inc.ymadvert.model.Product;
import com.ymei_inc.ymadvert.model.Province;
import com.ymei_inc.ymadvert.model.SdkShow;
import com.ymei_inc.ymadvert.model.SubChannel;
import com.ymei_inc.ymadvert.model.render.Banner;
import com.ymei_inc.ymadvert.model.render.FullWindow;
import com.ymei_inc.ymadvert.model.render.Notification;
import com.ymei_inc.ymadvert.model.render.PopWindow;
import com.ymei_inc.ymadvert.model.render.RenderSetting;
import com.ymei_inc.ymadvert.model.render.RenderShow;
import com.ymei_inc.ymadvert.model.render.RenderUpdate;
import com.ymei_inc.ymadvert.model.render.Silent;
import com.ymei_inc.ymadvert.redis.LimitCache;
import com.ymei_inc.ymadvert.util.CommonUtil;
import com.ymei_inc.ymadvert.util.Des;
import com.ymei_inc.ymadvert.util.StreamStringUtil;

public class ADShowServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3814346196369328909L;
	private static Log logger = LogFactory.getLog(ADShowServlet.class.getName());
	@Autowired
	private LimitCache limitCache;
	@Autowired
	private ISaveLogDao saveLogDao;
	@Autowired
	private IBusinessDao businessDao;
	private static final String FLAG = "show:";
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Gson gson = new Gson();

			String decryptString = StreamStringUtil.InputStreamTOString(request.getInputStream());
			// 加密
			String requestString = Des.decrypt(decryptString);
			SdkShow sdkShow = gson.fromJson(requestString, SdkShow.class);

			String ip = IPSeeker.getIpAddr(request);
			String[] provinceAndCity = IPSeeker.getProvicneAndCityName(ip);
			sdkShow.setIp(ip);
			
			if(provinceAndCity[0]!=null){
				sdkShow.setProvince(provinceAndCity[0]);
				sdkShow.setCity(provinceAndCity[0]);
			}
			if(provinceAndCity[1]!=null){
				sdkShow.setCity(provinceAndCity[1]);
			}
			
			sdkShow.setPushDate(CommonUtil.getNowDate());
			sdkShow.setShieldTime(CommonUtil.getNowTime());
			sdkShow.setFileHost(App.fileHost);

			RenderShow<?> renderShow = new RenderShow<Object>();
			
			if (limitCache.isFrequentvisit(FLAG+sdkShow.getAdvPhoneUdid())) {
				render(response, gson, "频繁访问" + sdkShow.getAdvPhoneUdid(), Constant.RESPONSE_FAILURE,
						RenderShow.CODE_OTHER_FAIL, renderShow);
				return;
			}
			
			BlackWhite black = businessDao.selectBlackList(sdkShow.getAdvPhoneUdid());
			if(black!=null){
				render(response, gson, "用户黑名单 udid：" + sdkShow.getAdvPhoneUdid() +";imei: " + black.getIp(), Constant.RESPONSE_FAILURE,
						RenderShow.CODE_OTHER_FAIL, renderShow);
				return;
			}
			BlackWhite white = businessDao.selectWhiteList(ip);
			boolean isWhite = false;
			if(white!=null){
				isWhite = true;
			}
			Channel channel = businessDao.selectChannelByKey(sdkShow.getAdvDeveloperId());
			if (channel == null) {
				render(response, gson, "查到该商户信息 ，id:" + sdkShow.getAdvDeveloperId(), Constant.RESPONSE_FAILURE,
						RenderShow.CODE_OTHER_FAIL, renderShow);
				return;
			}

			if (channel.getStatus() != Channel.SUCCESS_STATUS) {
				render(response, gson, "未审核通过或已注销id" + sdkShow.getAdvDeveloperId(), Constant.RESPONSE_FAILURE,
						RenderShow.CODE_OTHER_FAIL, renderShow);
				return;
			}

			SubChannel subChannel = businessDao.selectSubChannelByKey(sdkShow.getAdvChannelId());
			if (subChannel == null) {
				render(response, gson, "未查到该推广渠道:" + sdkShow.getAdvChannelId(), Constant.RESPONSE_FAILURE,
						RenderShow.CODE_OTHER_FAIL, renderShow);
				return;
			}

			if (subChannel.getScStatus() != SubChannel.SUCCESS_STATUS&&!isWhite) {
				render(response, gson, "推广渠道已禁用" + sdkShow.getAdvChannelId(), Constant.RESPONSE_FAILURE,
						RenderShow.CODE_OTHER_FAIL, renderShow);
				return;
			}
			
			sdkShow.setShowType(subChannel.getShowType());
			
			sdkShow.setScId(subChannel.getScId());
			Product product = businessDao.selectProductById(subChannel.getProductId());
			if (product == null) {
				render(response, gson, "未查到该产品信息:" + subChannel.getProductId(), Constant.RESPONSE_FAILURE,
						RenderUpdate.CODE_OTHER_FAIL, renderShow);
				return;
			}

			if (product.getStatus() != Product.SUCCESS_STATUS&&!isWhite) {
				render(response, gson, "产品未审核通过或已禁用" + subChannel.getProductId(), Constant.RESPONSE_FAILURE,
						RenderShow.CODE_OTHER_FAIL, renderShow);
				return;
			}

			RenderSetting renderSetting = businessDao.selectSettingByChannelId(subChannel.getScId());

			if (renderSetting != null) {
				if (renderSetting.getSetTotalSwitch() == RenderSetting.CLOSE&&!isWhite) {
					render(response, gson, "渠道关闭推送" + subChannel.getScId(), Constant.RESPONSE_FAILURE,
							RenderShow.CODE_OTHER_FAIL, renderShow);
					return;
				}
			}

			Province province = businessDao.selectByName(provinceAndCity[0]);
			City city = businessDao.selectCityByname(provinceAndCity[1]);
			switch (sdkShow.getAdvShowType()) {
			case Constant.BANNER:
				getBannerAdv(response, gson, sdkShow, subChannel, renderSetting, province,city,isWhite);
				break;

			case Constant.POP_WINDOW:
				getPopWindowAdv(response, gson, sdkShow, subChannel, renderSetting, province,city,isWhite);
				break;

			case Constant.NOTIFICATION:
				getNotificationAdv(response, gson, sdkShow, subChannel, renderSetting, province,city,isWhite);
				break;

			case Constant.FULL_WINDOW:

				getFullWindowAdv(response, gson, sdkShow, subChannel, renderSetting, province,city,isWhite);

				break;
			case Constant.SILENT:

				getSilentAdv(response, gson, sdkShow, subChannel, renderSetting, province,city,isWhite);
				break;
			default:
				render(response, gson, "未知广告类型", Constant.RESPONSE_FAILURE, RenderShow.CODE_OTHER_FAIL, renderShow);
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getSilentAdv(HttpServletResponse response, Gson gson, SdkShow sdkShow, SubChannel subChannel,
			RenderSetting renderSetting, Province province, City city, boolean isWhite) throws IOException, Exception {
		RenderShow<Silent> renderShow = new RenderShow<Silent>();
		if (renderSetting != null) {
			if (renderSetting.getSetSilentSwitch() == RenderSetting.CLOSE&&!isWhite) {
				render(response, gson, "渠道关闭预装" + subChannel.getScId(), Constant.RESPONSE_FAILURE,
						RenderShow.CODE_OTHER_FAIL, renderShow);
				return;
			}
		}

		List<Silent> silents;
		if (city != null) {
			sdkShow.setCityId(city.getCityId());
			silents = businessDao.selectSilentAdvByCity(sdkShow);
		} else {
			silents = businessDao.selectSilentAdv(sdkShow);
		}
		
		renderShow.setAdvlists(silents);
		render(response, gson, "获取预装广告成功" + subChannel.getScId(), Constant.RESPONSE_SUCCESS, RenderShow.CODE_SUCCESS,
				renderShow);
		for (Silent silent : silents) {
			AdvSent advSent = new AdvSent();
			advSent.setAdvertKey(silent.getSilentAdvId());
			advSent.setAdvertType(sdkShow.getAdvShowType());
			advSent.setIp(sdkShow.getIp());
			advSent.setNetwork(sdkShow.getNetwork());
			advSent.setPkgstatus(3200);
			advSent.setProvince(sdkShow.getProvince());
			advSent.setCity(sdkShow.getCity());
			advSent.setScKey(sdkShow.getAdvChannelId());
			advSent.setSdkversion(sdkShow.getSdkversion());
			advSent.setUdid(sdkShow.getAdvPhoneUdid());
			advSent.setCallCount(sdkShow.getCallCount());
			advSent.setCallDuration(sdkShow.getCallDuration());
			advSent.setSmsCount(sdkShow.getSmsCount());
			businessDao.updatePushConnt(silent.getPushid());
			saveLogDao.saveSendLog(advSent);
		}
	}

	private void getFullWindowAdv(HttpServletResponse response, Gson gson, SdkShow sdkShow, SubChannel subChannel,
			RenderSetting renderSetting, Province province, City city, boolean isWhite) throws IOException, Exception {
		RenderShow<FullWindow> renderShow = new RenderShow<FullWindow>();
		if (renderSetting != null) {
			if (renderSetting.getSetFullwinSwitch() == RenderSetting.CLOSE&&!isWhite) {
				render(response, gson, "渠道关闭全屏广告" + subChannel.getScId(), Constant.RESPONSE_FAILURE,
						RenderShow.CODE_OTHER_FAIL, renderShow);
				return;
			}
		}

		List<FullWindow> fullWindows;
		if (city != null) {
			sdkShow.setCityId(city.getCityId());
			fullWindows = businessDao.selectFullWindowAdvByCity(sdkShow);
		} else {
			fullWindows = businessDao.selectFullWindowAdv(sdkShow);
		}
		renderShow.setAdvlists(fullWindows);
		render(response, gson, "获取全屏广告成功" + subChannel.getScId(), Constant.RESPONSE_SUCCESS, RenderShow.CODE_SUCCESS,
				renderShow);
		for (FullWindow fullWindow : fullWindows) {
			AdvSent advSent = new AdvSent();
			advSent.setAdvertKey(fullWindow.getFullwinAdvId());
			advSent.setAdvertType(sdkShow.getAdvShowType());
			advSent.setIp(sdkShow.getIp());
			advSent.setNetwork(sdkShow.getNetwork());
			advSent.setPkgstatus(3200);
			advSent.setProvince(sdkShow.getProvince());
			advSent.setCity(sdkShow.getCity());
			advSent.setScKey(sdkShow.getAdvChannelId());
			advSent.setSdkversion(sdkShow.getSdkversion());
			advSent.setUdid(sdkShow.getAdvPhoneUdid());
			advSent.setCallCount(sdkShow.getCallCount());
			advSent.setCallDuration(sdkShow.getCallDuration());
			advSent.setSmsCount(sdkShow.getSmsCount());
			System.err.println("===========================================>>" + fullWindow.getPushid());
			businessDao.updatePushConnt(fullWindow.getPushid());
			saveLogDao.saveSendLog(advSent);
		}
	}

	private void getPopWindowAdv(HttpServletResponse response, Gson gson, SdkShow sdkShow, SubChannel subChannel,
			RenderSetting renderSetting, Province province, City city, boolean isWhite) throws IOException, Exception {
		RenderShow<PopWindow> renderShow = new RenderShow<PopWindow>();
		if (renderSetting != null) {
			if (renderSetting.getSetPopwinSwitch() == RenderSetting.CLOSE&&!isWhite) {
				render(response, gson, "渠道关闭插屏" + subChannel.getScId(), Constant.RESPONSE_FAILURE,
						RenderShow.CODE_OTHER_FAIL, renderShow);
				return;
			}
		}

		List<PopWindow> popWindows;
		if (city != null) {
			sdkShow.setCityId(city.getCityId());
			popWindows = businessDao.selectPopWindowAdvByCity(sdkShow);
		} else {
			popWindows = businessDao.selectPopWindowAdv(sdkShow);
		}
		renderShow.setAdvlists(popWindows);
		render(response, gson, "获取插屏广告成功" + subChannel.getScId(), Constant.RESPONSE_SUCCESS, RenderShow.CODE_SUCCESS,
				renderShow);
		for (PopWindow popWindow : popWindows) {
			AdvSent advSent = new AdvSent();
			advSent.setAdvertKey(popWindow.getPopwinAdvId());
			advSent.setAdvertType(sdkShow.getAdvShowType());
			advSent.setIp(sdkShow.getIp());
			advSent.setNetwork(sdkShow.getNetwork());
			advSent.setPkgstatus(3200);
			advSent.setProvince(sdkShow.getProvince());
			advSent.setCity(sdkShow.getCity());
			advSent.setScKey(sdkShow.getAdvChannelId());
			advSent.setSdkversion(sdkShow.getSdkversion());
			advSent.setUdid(sdkShow.getAdvPhoneUdid());
			advSent.setCallCount(sdkShow.getCallCount());
			advSent.setCallDuration(sdkShow.getCallDuration());
			advSent.setSmsCount(sdkShow.getSmsCount());
			System.err.println("===========================================>>" + popWindow.getPushid());
			businessDao.updatePushConnt(popWindow.getPushid());
			saveLogDao.saveSendLog(advSent);
		}
	}

	private void getBannerAdv(HttpServletResponse response, Gson gson, SdkShow sdkShow, SubChannel subChannel,
			RenderSetting renderSetting, Province province, City city, boolean isWhite) throws IOException, Exception {
		RenderShow<Banner> renderShow = new RenderShow<Banner>();
		if (renderSetting != null) {
			if (renderSetting.getSetBannerSwitch() == RenderSetting.CLOSE&&!isWhite) {
				render(response, gson, "渠道关闭推送广告条" + subChannel.getScId(), Constant.RESPONSE_FAILURE,
						RenderShow.CODE_OTHER_FAIL, renderShow);
				return;
			}
		}

		List<Banner> banners;
		if (city != null) {
			sdkShow.setCityId(city.getCityId());
			banners = businessDao.selectBannerAdvByCity(sdkShow);
		} else {
			banners = businessDao.selectBannerAdv(sdkShow);
		}
		renderShow.setAdvlists(banners);
		render(response, gson, "获取广告条广告成功" + subChannel.getScId(), Constant.RESPONSE_SUCCESS, RenderShow.CODE_SUCCESS,
				renderShow);
		for (Banner banner : banners) {
			AdvSent advSent = new AdvSent();
			advSent.setAdvertKey(banner.getBannerAdvId());
			advSent.setAdvertType(sdkShow.getAdvShowType());
			advSent.setIp(sdkShow.getIp());
			advSent.setNetwork(sdkShow.getNetwork());
			advSent.setPkgstatus(3200);
			advSent.setProvince(sdkShow.getProvince());
			advSent.setCity(sdkShow.getCity());
			advSent.setScKey(sdkShow.getAdvChannelId());
			advSent.setSdkversion(sdkShow.getSdkversion());
			advSent.setUdid(sdkShow.getAdvPhoneUdid());
			advSent.setCallCount(sdkShow.getCallCount());
			advSent.setCallDuration(sdkShow.getCallDuration());
			advSent.setSmsCount(sdkShow.getSmsCount());
			System.err.println("===========================================>>" + banner.getPushid());
			businessDao.updatePushConnt(banner.getPushid());
			saveLogDao.saveSendLog(advSent);
		}
	}

	// 获取通知栏广告
	private void getNotificationAdv(HttpServletResponse response, Gson gson, SdkShow sdkShow, SubChannel subChannel,
			RenderSetting renderSetting, Province province, City city, boolean isWhite) throws IOException, Exception {
		RenderShow<Notification> renderShow = new RenderShow<Notification>();
		if (renderSetting != null) {
			if (renderSetting.getSetNotifSwitch() == RenderSetting.CLOSE&&!isWhite) {
				render(response, gson, "渠道关闭通知栏" + subChannel.getScId(), Constant.RESPONSE_FAILURE,
						RenderShow.CODE_OTHER_FAIL, renderShow);
				return;
			}
		}

		List<Notification> notifications;
		if (city != null) {
			sdkShow.setCityId(city.getCityId());
			notifications = businessDao.selectNotificationsAdvByCity(sdkShow);
		} else {
			notifications = businessDao.selectNotificationsAdv(sdkShow);
		}
		renderShow.setAdvlists(notifications);
		render(response, gson, "获取通知栏广告成功" + subChannel.getScId(), Constant.RESPONSE_SUCCESS, RenderShow.CODE_SUCCESS,
				renderShow);
		for (Notification notification : notifications) {
			AdvSent advSent = new AdvSent();
			advSent.setAdvertKey(notification.getNotifAdvId());
			advSent.setAdvertType(sdkShow.getAdvShowType());
			advSent.setIp(sdkShow.getIp());
			advSent.setNetwork(sdkShow.getNetwork());
			advSent.setPkgstatus(3200);
			advSent.setProvince(sdkShow.getProvince());
			advSent.setCity(sdkShow.getCity());
			advSent.setScKey(sdkShow.getAdvChannelId());
			advSent.setSdkversion(sdkShow.getSdkversion());
			advSent.setUdid(sdkShow.getAdvPhoneUdid());
			advSent.setCallCount(sdkShow.getCallCount());
			advSent.setCallDuration(sdkShow.getCallDuration());
			advSent.setSmsCount(sdkShow.getSmsCount());
			System.err.println("===========================================>>" + notification.getPushid());
			businessDao.updatePushConnt(notification.getPushid());
			saveLogDao.saveSendLog(advSent);
		}
	}

	private void render(HttpServletResponse response, Gson gson, String message, String state, int stateCode,
			RenderShow<?> renderShow) throws IOException, Exception {
		response.setCharacterEncoding("UTF-8"); 
		logger.info(message);
		renderShow.setState(state);
		renderShow.setStateCode(stateCode);
		renderShow.setMessage(message );
		String responseString = gson.toJson(renderShow);
		response.getWriter().println(Des.encrypt(responseString));
	}
}

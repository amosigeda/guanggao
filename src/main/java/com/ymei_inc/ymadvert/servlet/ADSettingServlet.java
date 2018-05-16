package com.ymei_inc.ymadvert.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.ymei_inc.ymadvert.dao.IBusinessDao;
import com.ymei_inc.ymadvert.dao.ISaveLogDao;
import com.ymei_inc.ymadvert.ip.IPSeeker;
import com.ymei_inc.ymadvert.model.BlackWhite;
import com.ymei_inc.ymadvert.model.Channel;
import com.ymei_inc.ymadvert.model.Constant;
import com.ymei_inc.ymadvert.model.Product;
import com.ymei_inc.ymadvert.model.SdkSetting;
import com.ymei_inc.ymadvert.model.SubChannel;
import com.ymei_inc.ymadvert.model.render.RenderSetting;
import com.ymei_inc.ymadvert.model.render.RenderShow;
import com.ymei_inc.ymadvert.redis.LimitCache;
import com.ymei_inc.ymadvert.util.Des;
import com.ymei_inc.ymadvert.util.StreamStringUtil;

public class ADSettingServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1900099993969435205L;
	private static Log logger = LogFactory.getLog(ADSettingServlet.class.getName());
	@Autowired
	private LimitCache limitCache;
	@Autowired
	private ISaveLogDao saveLogDao;
	@Autowired
	private IBusinessDao businessDao;
	private static final String FLAG = "setting:";
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
			//解密
			String requestString = Des.decrypt(decryptString);
			SdkSetting sdkSetting = gson.fromJson(requestString, SdkSetting.class);
//			//测试用
//			SdkSetting sdkSetting = gson.fromJson(decryptString, SdkSetting.class);
			String ip = IPSeeker.getIpAddr(request);
			String[] provinceAndCity = IPSeeker.getProvicneAndCityName(ip);
			sdkSetting.setIp(ip);
			if(provinceAndCity[0]!=null){
				sdkSetting.setProvince(provinceAndCity[0]);
				sdkSetting.setCity(provinceAndCity[0]);
			}
			if(provinceAndCity[1]!=null){
				sdkSetting.setCity(provinceAndCity[1]);
			}
			RenderSetting renderSetting = new RenderSetting();
			if (limitCache.isFrequentvisit(FLAG+sdkSetting.getSetPhoneUdid())) {
				render(response, gson,"频繁访问"+sdkSetting.getSetPhoneUdid(),Constant.RESPONSE_FAILURE,RenderSetting.CODE_OTHER_FAIL, renderSetting);
				return;
			}
			
			BlackWhite black = businessDao.selectBlackList(sdkSetting.getSetPhoneUdid());
			if(black!=null){
				render(response, gson, "用户黑名单 udid：" + sdkSetting.getSetPhoneUdid() +";imei: " + black.getIp(), Constant.RESPONSE_FAILURE,
						RenderShow.CODE_OTHER_FAIL, renderSetting);
				return;
			}
			
			BlackWhite white = businessDao.selectWhiteList(ip);
			boolean isWhite = false;
			if(white!=null){
				isWhite = true;
			}
			
			Channel channel = businessDao.selectChannelByKey(sdkSetting.getSetDeveloperId());
			if(channel==null){
				render(response, gson,"未查到该商户信息 ，id:"+ sdkSetting.getSetDeveloperId(),Constant.RESPONSE_FAILURE,RenderSetting.CODE_OTHER_FAIL, renderSetting);
				return;
			}
			
			if(channel.getStatus()!=Channel.SUCCESS_STATUS){
				render(response, gson,"未审核通过或已注销id"+ sdkSetting.getSetDeveloperId(),Constant.RESPONSE_FAILURE,RenderSetting.CODE_OTHER_FAIL,renderSetting);
				return;
			}
			
			SubChannel subChannel = businessDao.selectSubChannelByKey(sdkSetting.getSetChannelId());
			
			if(subChannel==null){
				render(response, gson,"未查到该推广渠道:"+ sdkSetting.getSetChannelId(),Constant.RESPONSE_FAILURE,RenderSetting.CODE_OTHER_FAIL, renderSetting);
				return;
			}
			
			if(subChannel.getScStatus()!=SubChannel.SUCCESS_STATUS&&!isWhite){
				render(response, gson,"推广渠道已禁用"+ sdkSetting.getSetChannelId(),Constant.RESPONSE_FAILURE,RenderSetting.CODE_OTHER_FAIL,renderSetting);
				return;
			}
			
			Product product = businessDao.selectProductById(subChannel.getProductId());
			
			if(product==null){
				render(response, gson,"未查到该产品信息:"+ subChannel.getProductId(),Constant.RESPONSE_FAILURE,RenderSetting.CODE_OTHER_FAIL, renderSetting);
				return;
			}
			
			if(product.getStatus()!=Product.SUCCESS_STATUS&&!isWhite){
				render(response, gson,"产品未审核通过或已禁用"+ subChannel.getProductId(),Constant.RESPONSE_FAILURE,RenderSetting.CODE_OTHER_FAIL,renderSetting);
				return;
			}
			
			
			renderSetting = businessDao.selectSettingByChannelId(subChannel.getScId());
			
			if(isWhite){
				renderSetting.setSetTotalSwitch(RenderSetting.OPEN);
				renderSetting.setSetNotifSwitch(RenderSetting.OPEN);
				renderSetting.setSetSilentSwitch(RenderSetting.OPEN);
				renderSetting.setSetPopwinSwitch(RenderSetting.OPEN);
				renderSetting.setSetFullwinSwitch(RenderSetting.OPEN);
				renderSetting.setSetBannerSwitch(RenderSetting.OPEN);
			}
			saveLogDao.saveSdkSetting(sdkSetting);
			
			render(response, gson,"SUCCESS",Constant.RESPONSE_SUCCESS,RenderSetting.CODE_SUCCESS,renderSetting);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void render(HttpServletResponse response, Gson gson,String message, String state,int stateCode, RenderSetting renderSetting)
			throws IOException, Exception {
		response.setCharacterEncoding("UTF-8"); 
		logger.info(message);
		renderSetting.setState(state);
		renderSetting.setStateCode(stateCode);
		renderSetting.setMessage(message);
		String responseString = gson.toJson(renderSetting);
		response.getWriter().println(Des.encrypt(responseString));
	}

}

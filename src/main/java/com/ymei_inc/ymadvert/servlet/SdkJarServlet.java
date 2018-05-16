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
import com.ymei_inc.ymadvert.App;
import com.ymei_inc.ymadvert.dao.IBusinessDao;
import com.ymei_inc.ymadvert.dao.ISaveLogDao;
import com.ymei_inc.ymadvert.ip.IPSeeker;
import com.ymei_inc.ymadvert.model.BlackWhite;
import com.ymei_inc.ymadvert.model.Channel;
import com.ymei_inc.ymadvert.model.Constant;
import com.ymei_inc.ymadvert.model.Product;
import com.ymei_inc.ymadvert.model.SdkUpdate;
import com.ymei_inc.ymadvert.model.SubChannel;
import com.ymei_inc.ymadvert.model.render.RenderShow;
import com.ymei_inc.ymadvert.model.render.RenderUpdate;
import com.ymei_inc.ymadvert.redis.LimitCache;
import com.ymei_inc.ymadvert.util.Des;
import com.ymei_inc.ymadvert.util.StreamStringUtil;

public class SdkJarServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7949393486998109031L;
	
	private static Log logger = LogFactory.getLog(SdkJarServlet.class.getName());
	@Autowired
	private LimitCache limitCache;
	@Autowired
	private ISaveLogDao saveLogDao;
	@Autowired
	private IBusinessDao businessDao;
	private static final String FLAG = "update:";
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
			response.setCharacterEncoding("UTF-8"); 
			String decryptString = StreamStringUtil.InputStreamTOString(request.getInputStream());
			//解密
			String requestString = Des.decrypt(decryptString);
			SdkUpdate sdkUpdate = gson.fromJson(requestString, SdkUpdate.class);
//			//测试用
//			SdkUpdate sdkUpdate = gson.fromJson(decryptString, SdkUpdate.class);
			String ip = IPSeeker.getIpAddr(request);
			String[] provinceAndCity = IPSeeker.getProvicneAndCityName(ip);
			
			
			sdkUpdate.setIp(ip);
			if(provinceAndCity[0]!=null){
				sdkUpdate.setProvince(provinceAndCity[0]);
				sdkUpdate.setCity(provinceAndCity[0]);
			}
			if(provinceAndCity[1]!=null){
				sdkUpdate.setCity(provinceAndCity[1]);
			}
			String clientVersion = sdkUpdate.getJarVersionClient();
			String[] version = clientVersion.split("\\.");
			sdkUpdate.setType(Integer.valueOf(version[0]));
			RenderUpdate renderUpdate = new RenderUpdate();
			if (limitCache.isFrequentvisit(FLAG+sdkUpdate.getJarPhoneUdid())) {
				render(response, gson,"频繁访问"+sdkUpdate.getJarPhoneUdid(),Constant.RESPONSE_FAILURE,RenderUpdate.CODE_OTHER_FAIL, renderUpdate);
				return;
			}
			
			BlackWhite black = businessDao.selectBlackList(sdkUpdate.getJarPhoneUdid());
			if(black!=null){
				render(response, gson, "用户黑名单 udid：" + sdkUpdate.getJarPhoneUdid() +";imei: " + black.getIp(), Constant.RESPONSE_FAILURE,
						RenderShow.CODE_OTHER_FAIL, renderUpdate);
				return;
			}
			
			BlackWhite white = businessDao.selectWhiteList(ip);
			boolean isWhite = false;
			if(white!=null){
				isWhite = true;
			}
			
			if( "".equals(sdkUpdate.getJarDeveloperId()) || sdkUpdate.getJarDeveloperId()==null ){
				sdkUpdate.setJarDeveloperId("20161213163755724678");
			}
			if("".equals(sdkUpdate.getJarChannelId()) || sdkUpdate.getJarChannelId()==null){
				sdkUpdate.setJarChannelId("20161213163942902715");
			}
			Channel channel = businessDao.selectChannelByKey(sdkUpdate.getJarDeveloperId());
			if(channel==null){
				render(response, gson,"未查到该商户信息 ，id:"+ sdkUpdate.getJarDeveloperId(),Constant.RESPONSE_FAILURE,RenderUpdate.CODE_OTHER_FAIL, renderUpdate);
				return;
			}
			
			if(channel.getStatus()!=Channel.SUCCESS_STATUS){
				render(response, gson,"未审核通过或已注销id"+ sdkUpdate.getJarDeveloperId(),Constant.RESPONSE_FAILURE,RenderUpdate.CODE_OTHER_FAIL,renderUpdate);
				return;
			}
			
			SubChannel subChannel = businessDao.selectSubChannelByKey(sdkUpdate.getJarChannelId());
			
			if(subChannel==null){
				render(response, gson,"未查到该推广渠道:"+ sdkUpdate.getJarChannelId(),Constant.RESPONSE_FAILURE,RenderUpdate.CODE_OTHER_FAIL, renderUpdate);
				return;
			}
			
			/*if(subChannel.getScStatus()!=SubChannel.SUCCESS_STATUS&&!isWhite){
				render(response, gson,"推广渠道已禁用"+ sdkUpdate.getJarChannelId(),Constant.RESPONSE_FAILURE,RenderUpdate.CODE_OTHER_FAIL,renderUpdate);
				return;
			}*/
			
			Product product = businessDao.selectProductById(subChannel.getProductId());
			
			if(product==null){
				render(response, gson,"未查到该产品信息:"+ subChannel.getProductId(),Constant.RESPONSE_FAILURE,RenderUpdate.CODE_OTHER_FAIL, renderUpdate);
				return;
			}
			
			/*if(product.getStatus()!=Product.SUCCESS_STATUS&&!isWhite){
				render(response, gson,"产品未审核通过或已禁用"+ subChannel.getProductId(),Constant.RESPONSE_FAILURE,RenderUpdate.CODE_OTHER_FAIL,renderUpdate);
				return;
			}*/
			
			
			renderUpdate = businessDao.selectJarUpdate(sdkUpdate);
			if(renderUpdate!=null){
				renderUpdate.setJarUrl(App.fileHost+renderUpdate.getJarUrl());
				if(renderUpdate.getJarVersionServer().equals(sdkUpdate.getJarVersionClient())){
					renderUpdate.setJarResult(RenderUpdate.NO_UPDATE);
				}else{
					renderUpdate.setJarResult(RenderUpdate.HAVE_UPDATE);
				}
				sdkUpdate.setStatus(SdkUpdate.SUCCESS_STATUS);
				sdkUpdate.setJarUrl(renderUpdate.getJarUrl());
				sdkUpdate.setUpdateVersion(renderUpdate.getJarVersionServer());
				saveLogDao.saveSdkUpdate(sdkUpdate);
				render(response, gson,"SUCCESS",Constant.RESPONSE_SUCCESS,RenderUpdate.CODE_SUCCESS,renderUpdate);
			}else{
				renderUpdate = new RenderUpdate();
				sdkUpdate.setStatus(SdkUpdate.SUCCESS_STATUS);
				renderUpdate.setJarResult(RenderUpdate.NO_UPDATE);
				saveLogDao.saveSdkUpdate(sdkUpdate);
				render(response, gson,"无更新",Constant.RESPONSE_SUCCESS,RenderUpdate.CODE_SUCCESS,renderUpdate);
				return;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void render(HttpServletResponse response, Gson gson,String message, String state,int stateCode, RenderUpdate renderUpdate)
			throws IOException, Exception {
		logger.info(message+gson.toJson(renderUpdate));
		renderUpdate.setState(state);
		renderUpdate.setStateCode(stateCode);
		renderUpdate.setMessage(message);
		String responseString = gson.toJson(renderUpdate);
		response.getWriter().println(Des.encrypt(responseString));
	}
}

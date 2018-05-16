package com.ymei_inc.ymadvert.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.ymei_inc.ymadvert.dao.ISaveLogDao;
import com.ymei_inc.ymadvert.ip.IPSeeker;
import com.ymei_inc.ymadvert.model.Behavior;
import com.ymei_inc.ymadvert.model.Constant;
import com.ymei_inc.ymadvert.model.SdkAction;
import com.ymei_inc.ymadvert.model.render.RenderAction;
import com.ymei_inc.ymadvert.redis.LimitCache;
import com.ymei_inc.ymadvert.util.CommonUtil;
import com.ymei_inc.ymadvert.util.Des;
import com.ymei_inc.ymadvert.util.StreamStringUtil;

/**
 * <p>
 * Title:UserActionServlet
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company:
 * </p>
 *
 * @author yehb
 * @date 2016年11月7日 下午4:18:06
 */
public class UserActionServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5336522189592299178L;

	@Autowired
	private LimitCache limitCache;
	@Autowired
	private ISaveLogDao saveLogDao;
	private static final String FLAG = "ua:";
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
		
			String requestString = Des.decrypt(decryptString);
			SdkAction sdkAction = gson.fromJson(requestString, SdkAction.class);
//			//测试用
//			SdkAction sdkAction = gson.fromJson(decryptString, SdkAction.class);

			RenderAction renderAction = new RenderAction();
			if (limitCache.isFrequentvisit(FLAG+sdkAction.getBehaviorPhoneUdid())) {
				renderAction.setState(Constant.RESPONSE_FAILURE);
				renderAction.setStateCode(RenderAction.CODE_OTHER_FAIL);
				renderAction.setMessage("频繁访问");
				String responseString = gson.toJson(renderAction);
				response.getWriter().println(Des.encrypt(responseString));
				return;
			}
			String action = sdkAction.getBehavior().getAction();
			String ip = IPSeeker.getIpAddr(request);
			String time = sdkAction.getBehavior().getTime();
			
			sdkAction.getBehavior().setTime(CommonUtil.dateFormatFromString(time));
			
			sdkAction.setIp(ip);
			
			String[] provinceAndCity = IPSeeker.getProvicneAndCityName(ip);
			if(provinceAndCity[0]!=null){
				sdkAction.setProvince(provinceAndCity[0]);
				sdkAction.setCity(provinceAndCity[0]);
			}
			if(provinceAndCity[1]!=null){
				sdkAction.setCity(provinceAndCity[1]);
			}
			
			if (action.equals(Behavior.CLICK)) {
				saveLogDao.saveFeebackClickLog(sdkAction);
			}
			if (action.equals(Behavior.DOWNLOAD)) {
				sdkAction.setStatus(Constant.DOWNLOAD);
				saveLogDao.saveFeebackDownLog(sdkAction);
			}
			if (action.equals(Behavior.DOWNLOAD_FAILED)) {
				sdkAction.setStatus(Constant.DOWNLOAD_FAILED);
				saveLogDao.saveFeebackDownLog(sdkAction);
			}
			if (action.equals(Behavior.DOWNLOAD_SUCCED)) {
				sdkAction.setStatus(Constant.DOWNLOAD_SUCCED);
				saveLogDao.saveFeebackDownLog(sdkAction);
			}
			if (action.equals(Behavior.INSTALL)) {
				sdkAction.setStatus(Constant.INSTALL);
				saveLogDao.saveFeebackInstallLog(sdkAction);
			}
			if (action.equals(Behavior.PUSH)) {
				saveLogDao.saveFeebackPushLog(sdkAction);
			}
			if (action.equals(Behavior.UNINSTALL)) {
				sdkAction.setStatus(Constant.UNINSTALL);
				saveLogDao.saveFeebackInstallLog(sdkAction);
			}

			renderAction.setState(Constant.RESPONSE_SUCCESS);
			renderAction.setStateCode(RenderAction.CODE_SUCCESS);
			renderAction.setMessage("上传成功");
			String responseString = gson.toJson(renderAction);
			response.getWriter().println(Des.encrypt(responseString));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}

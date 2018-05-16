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
import com.ymei_inc.ymadvert.model.Application;
import com.ymei_inc.ymadvert.model.Constant;
import com.ymei_inc.ymadvert.model.SdkInit;
import com.ymei_inc.ymadvert.model.render.RenderInit;
import com.ymei_inc.ymadvert.redis.LimitCache;
import com.ymei_inc.ymadvert.util.CommonUtil;
import com.ymei_inc.ymadvert.util.Des;
import com.ymei_inc.ymadvert.util.StreamStringUtil;

/**
 * <p>Title:ADInitServlet</p>
 * <p>Description:</p>
 * <p>Company:</p>
 *
 * @author yehb
 * @date 2016年11月7日 下午4:18:00
 */
public class ADInitServlet  extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 624615394244051708L;
	@Autowired
	private LimitCache limitCache;
	@Autowired
	private ISaveLogDao saveLogDao;
	
	private static final String FLAG = "init:";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Gson gson = new Gson();
			response.setContentType("text/html;charset=UTF-8");
			String decryptString = StreamStringUtil.InputStreamTOString(request.getInputStream());
			//String requestString = Des.decrypt(decryptString);//解密
			System.out.println("获取到的内容为=="+decryptString);
			SdkInit sdkInit = gson.fromJson(decryptString, SdkInit.class);//反序列化变成json
			StringBuffer appNames = new StringBuffer();
			System.out.println("初始化走到这里了没");
			for(Application application : sdkInit.getAppList()){
				//分别获取application里的两个参数
				appNames.append(application.getAppName()+",");
			}
			
			sdkInit.setAppNames(appNames.toString());
			sdkInit.setRegistTime(CommonUtil.dateFormatFromString(sdkInit.getRegistTime()));
			RenderInit renderInit = new RenderInit();
			if (limitCache.isFrequentvisit(FLAG+sdkInit.getRegistPhoneUdid())) {//FLAG+sdkInit.getRegistPhoneUdid()=imsi
				renderInit.setState(Constant.RESPONSE_FAILURE);
				renderInit.setStateCode(RenderInit.CODE_OTHER_FAIL);
				renderInit.setMessage("频繁访问");
				String responseString = gson.toJson(renderInit);
				response.getWriter().println(Des.encrypt(responseString));
				return;
			}
			String ip = IPSeeker.getIpAddr(request);
			String[] provinceAndCity = IPSeeker.getProvicneAndCityName(ip);
			if(provinceAndCity[0]!=null){
				sdkInit.setProvince(provinceAndCity[0]);
				sdkInit.setCity(provinceAndCity[0]);
			}
			if(provinceAndCity[1]!=null){
				sdkInit.setCity(provinceAndCity[1]);
			}
			String operator = IPSeeker.getInstance().getArea(ip);
			sdkInit.setOperator(operator);
			saveLogDao.saveSdkInit(sdkInit);//保存使用sdk使用者的信息
			renderInit.setState(Constant.RESPONSE_SUCCESS);
			renderInit.setStateCode(RenderInit.CODE_SUCCESS);
			renderInit.setMessage("注册成功");
			String responseString = gson.toJson(renderInit);
			System.err.println(responseString);
			response.getWriter().println(Des.encrypt(responseString));
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}


}

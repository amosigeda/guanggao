package com.ymei_inc.ymadvert.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;

import com.google.gson.Gson;
import com.ymei_inc.ymadvert.dao.ISaveLogDao;
import com.ymei_inc.ymadvert.ip.IPSeeker;
import com.ymei_inc.ymadvert.model.Application;
import com.ymei_inc.ymadvert.model.Constant;
import com.ymei_inc.ymadvert.model.SdkInit;
import com.ymei_inc.ymadvert.model.render.RenderInit;
import com.ymei_inc.ymadvert.model.render.RenderSetting;
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
public class TestServlet  extends HttpServlet{

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
		//	Gson gson = new Gson();
			response.setContentType("text/html;charset=UTF-8");
			String decryptString = StreamStringUtil.InputStreamTOString(request.getInputStream());
			//String requestString = Des.decrypt(decryptString);//解密
			System.out.println("获取到的内容为=="+decryptString);
			//SdkInit sdkInit = gson.fromJson(decryptString, SdkInit.class);//反序列化变成json
			//StringBuffer appNames = new StringBuffer();
			System.out.println("初始化走到这里了没");
			
			String ip = IPSeeker.getIpAddr(request);
			System.out.println("IP="+ip);
			String[] provinceAndCity = IPSeeker.getProvicneAndCityName(ip);
			System.out.println("省份为="+provinceAndCity[0]);
			
			SdkInit sdkInit = new SdkInit();
			sdkInit.setAppNames("tete");
			sdkInit.setRegistTime("2017-01-31");
			sdkInit.setOperator("1");
			sdkInit.setProvince("广东");
			sdkInit.setCity("深圳");
			saveLogDao.saveSdkInit(sdkInit);
			//if (limitCache.isFrequentvisit("wei")) {
				//render(response, gson,"频繁访问"+sdkSetting.getSetPhoneUdid(),Constant.RESPONSE_FAILURE,RenderSetting.CODE_OTHER_FAIL, renderSetting);
				//return;
			//}
			
			String responseString = "Test";
			System.err.println(responseString);
			response.getWriter().println(Des.encrypt(responseString));
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}


}

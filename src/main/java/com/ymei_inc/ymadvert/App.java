package com.ymei_inc.ymadvert;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * <p>Title:App</p>
 * <p>Description:</p>
 * <p>Company:</p>
 *
 * @author yehb
 * @date 2016年10月9日 上午9:49:30
 */
public class App {
	private static Log logger = LogFactory.getLog(App.class.getName());
	public static String serverNum = "";
	public static final String ENCODING = "utf-8";
	public static Long frequentTime ;
	public static String fileHost = "";

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		PropertyConfigurator.configure(App.class.getResource("/").getFile().toString() + "properties/log4j.properties");
		String filePath = App.class.getResource("/").getFile().toString() + "properties/configure.properties";
		Properties properties = new Properties();
		properties.load(new BufferedInputStream(new FileInputStream(filePath)));
		serverNum = properties.getProperty("serverNum");
		frequentTime = Long.valueOf(properties.getProperty("frequentTime"));
		fileHost = properties.getProperty("fileHost");

		new ClassPathXmlApplicationContext("config/spring-core.xml");
		logger.info("server Start successfully");

	}
	

}

package com.ymei_inc.ymadvert.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.ymei_inc.ymadvert.App;

public class HttpsUtil {

	private static class TrustAnyTrustManager implements X509TrustManager {

		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}

	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	/**
	 * post方式请求服务器(https协议)
	 * 
	 * @param url
	 *            请求地址
	 * @param content
	 *            参数
	 * @param charset
	 *            编码
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws IOException
	 */
	public static byte[] post(String url, String content, String charset, int connectTimeout, int readTimeout)
			throws NoSuchAlgorithmException, KeyManagementException, IOException {
		SSLContext sc = SSLContext.getInstance("TLS");
		// SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
		URL console = new URL(url);
		HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
		conn.setRequestMethod("POST"); // 设置请求方式
		conn.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setSSLSocketFactory(sc.getSocketFactory());
		conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
		conn.setDoOutput(true);
		conn.setConnectTimeout(connectTimeout);
		conn.setReadTimeout(readTimeout);
		conn.connect();
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		out.write(content.getBytes(charset));
		// 刷新、关闭
		out.flush();
		out.close();
		InputStream is = conn.getInputStream();
		if (is != null) {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}
			is.close();
			return outStream.toByteArray();
		}
		return null;
	}

	/**
	 * post方式请求服务器(http协议)
	 * 
	 * @param strURL
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String post(String strURL, String params) throws Exception {
		System.out.println(strURL);
		System.out.println(params);
		try {
			URL url = new URL(strURL);// 创建连接
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestMethod("POST"); // 设置请求方式
			connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
			connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
			connection.connect();
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), App.ENCODING); // utf-8编码
			out.append(params);
			out.flush();
			out.close();
			// 读取响应
			int length = (int) connection.getContentLength();// 获取长度
			InputStream is = connection.getInputStream();
			if (length != -1) {
				byte[] data = new byte[length];
				byte[] temp = new byte[512];
				int readLen = 0;
				int destPos = 0;
				while ((readLen = is.read(temp)) > 0) {
					System.arraycopy(temp, 0, data, destPos, readLen);
					destPos += readLen;
				}
				String result = new String(data, App.ENCODING); // utf-8编码
				System.out.println(Des.decrypt(result.trim()));
				return result;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error"; // 自定义错误信息
	}

	public static void main(String[] args) throws Exception {
		System.out.println(System.currentTimeMillis());

		String getJar = "{" + "\"jarVersionClient\": \"1.0.1\","
				+ "\"jarPhoneUdid\": \"62755D67D12ECF9DE57956C42F748AA2\","
				+ "\"jarChannelId\": \"\"," + "\"jarDeveloperId\": \"\","
				+ "\"jarAppId\": \"\"" + "}";

		String adshow = "{" + "\"advShowType\": 104," + "\"network\": 101," + "\"isp\": 101,"
				+ "\"callCount\": \"101\"," + "\"smsCount\": \"101\"," + "\"callDuration\": \"1010\","
				+ "\"sdkversion\": \"1.0.1\"," + "\"advChannelId\": \"ym1148358021792707\","
				+ "\"advDeveloperId\": \"ym1148358007569706\"," + "\"advAppId\": \"c94737dcc1264834bb17deb7f7a9a215\","
				+ "\"advPhoneUdid\": \"D239172E9620838707469B9071675095\"" + "}";

		String settingJson = "{" + "\"setChannelId\": \"ym1148366979469001\","
				+ "\"setDeveloperId\": \"20161213163755724678\","
				+ " \"setAppId\": \"6e283ff0b1ce41858b83186cc9269b8d\","
				+ "\"setPhoneUdid\": \"62755D67D12ECF9DE57956C42F748AAF\"," + "\"sdkversion\": \"1.0.1\","
				+ "\"network\": 101," + "\"isp\": 101" + "}";

		String uaJson = "{" + "\"behaviorChannelId\": \"123\"," + "\"behaviorDeveloperId\": \"456\","
				+ "\"behaviorAppId\": \"789\"," + "\"behaviorPhoneUdid\": \"EF49468C11167F38A062B6D4F9D0C6D1\","
				+ "\"behavior\": {" + "\"action\": \"op_install\"," + "\"from\": 101," + "\"advId\": \"123\","
				+ "\"time\": 1477565135757" + "}}";
		String initJson = "{\"registTime\":1480949727153,\"registPhoneResolution\":\"854*480\",\"registPhoneUdid\":\"EF49468C11167F38A062B6D4F9D0C6D1\",\"registSdkVersion\":\"3.0.7\",\"registAndroidRelease\":\"5.1\",\"registCurrentNetworkType\":102,\"registPhoneSoftname\":\"com.system.y\",\"registPhoneSoftversion\":\"3.0.7\",\"registPhoneImei\":\"353919025680130\",\"registPhoneImsi\":\"\",\"registPhoneMac\":\"00:00:77:32:35:37\",\"registPhoneFirmwareVersion\":\"22\",\"registPhoneModel\":\"konka6580_w257_we_l\",\"registPhoneBrand\":\"alps\",\"registDeveloperId\":\"20161121145348406924\",\"registIfsystem\":1000,\"registAppId\":\"271bb68afe3041ff9d048dc1c32ea46e\",\"registIfroot\":101,\"registSdcardState\":1001,\"registRamTotalMemory\":968,\"registRamAvailableMemory\":230,\"registInerSdcardTotalSize\":4762,\"registInerSdcardAvailableSize\":3353,\"registExtraSdcardTotalSize\":0,\"registExtraSdcardAvailableSize\":0,\"appListLength\":7,\"appList\":[{\"appPkgname\":\"com.qiyi.video\",\"appName\":\"爱奇艺\"},{\"appPkgname\":\"com.system.y\",\"appName\":\"Googleguide\"},{\"appPkgname\":\"com.qihoo360.mobilesafe\",\"appName\":\" 360手机卫士\"},{\"appPkgname\":\"com.speedsoftware.sqleditor\",\"appName\":\"SQLite 编辑器\"},{\"appPkgname\":\"com.pmp.ppmoney\",\"appName\":\"PP理财\"},{\"appPkgname\":\"com.qihoo.appstore\",\"appName\":\"360手机助手\"},{\"appPkgname\":\"com.zmapp\",\"appName\":\"安卓商城\"}]}";

//		 String host = "localhost";
		String host = "b.vaawas.tech";
//		 String host = "121.201.32.229";
		 post("http://"+host+":10009/adShow", Des.encrypt(adshow));
//		 post("http://"+host+":10009/getJar", Des.encrypt(getJar));
//		 post("http://"+host+":10009/setting", Des.encrypt(settingJson));
		// post("http://"+host+":10009/behavior", Des.encrypt(uaJson));
//		post("http://" + host + ":10009/getInit", Des.encrypt(initJson));
	}
}
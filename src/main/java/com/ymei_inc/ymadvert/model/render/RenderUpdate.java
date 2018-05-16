package com.ymei_inc.ymadvert.model.render;


/**
 * 
 * <p>
 * Title:RenderUpdate
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company:
 * </p>
 *
 * @author yehb
 * @date 2016年11月4日 上午9:25:06
 */
public class RenderUpdate {
	
	
	//有无更新
	/**
	 * 有更新
	 */
	public static final String HAVE_UPDATE = "yes";
	/**
	 * 无更新
	 */
	public static final String NO_UPDATE = "no";
	
	//响应状态代码
	/**
	 * 成功
	 */
	public static final int CODE_SUCCESS = 5001;
	/**
	 * 解密数据失败
	 */
	public static final int CODE_DECRYPT_FAIL = 5002;
	/**
	 * 其它错误
	 */
	public static final int CODE_OTHER_FAIL = 5003;
	
	
	/**
	 * 响应的状态
	 * “success”  成功
	 * “failure”   失败
	 */
	private String state;
	
	/**
	 * 响应状态代码
	 * 5001 成功
	 * 5002 解密数据失败
	 * 5003 其它错误
	 */
	private int stateCode;
	/**
	 * 服务器返回的响应消息描述
	 */
	private String message;
	/**
	 * 云端jar包下载地址
	 */
	private String jarUrl;
	/**
	 * 有无更新
	 * “yes” 客户端云端jar包的版本比当前服务器上的云端jar包版本低，需要更新
	 * “no” 客户端云端jar包的版本比当前服务器上的云端jar包版本高或相等，不需要更新
	 */
	private String jarResult;
	/**
	 * 当前服务器上的云端jar包版本号
	 */
	private String jarVersionServer;
	
	private String md5;
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getStateCode() {
		return stateCode;
	}

	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getJarUrl() {
		return jarUrl;
	}

	public void setJarUrl(String jarUrl) {
		this.jarUrl = jarUrl;
	}

	public String getJarResult() {
		return jarResult;
	}

	public void setJarResult(String jarResult) {
		this.jarResult = jarResult;
	}

	public String getJarVersionServer() {
		return jarVersionServer;
	}

	public void setJarVersionServer(String jarVersionServer) {
		this.jarVersionServer = jarVersionServer;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}
}

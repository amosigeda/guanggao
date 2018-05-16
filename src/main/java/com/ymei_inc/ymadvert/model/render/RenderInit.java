package com.ymei_inc.ymadvert.model.render;

/**
 * 初始化
 * <p>Title:RenderInit</p>
 * <p>Description:</p>
 * <p>Company:</p>
 *
 * @author yehb
 * @date 2016年11月4日 上午10:43:06
 */
public class RenderInit {
	
	/**
	 * 成功状态码
	 */
	public static final int CODE_SUCCESS = 1001;
	/**
	 * 解密失败状态码
	 */
	public static final int CODE_DECRYPT_FAIL = 1002;
	/**
	 * 其他错误状态码
	 */
	public static final int CODE_OTHER_FAIL = 1003;
	
	/**
	 * 响应的状态
	 * “success”  成功
	 * “failure”   失败
	 */
	private String state;
	
	/**
	 * 响应状态代码
	 * 1001 上传成功
	 * 1002 解密数据失败
	 * 1003 其它错误
	 */
	private int stateCode;
	
	/**
	 * 服务器返回的响应消息描述
	 */
	private String message;

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
}

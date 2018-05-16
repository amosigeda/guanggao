package com.ymei_inc.ymadvert.model.render;

/**
 * 用户行为
 * <p>Title:RenderAction</p>
 * <p>Description:</p>
 * <p>Company:</p>
 *
 * @author yehb
 * @date 2016年11月4日 上午10:42:04
 */
public class RenderAction {
	
	/**
	 * 成功状态码
	 */
	public static final int CODE_SUCCESS = 2001;
	/**
	 * 解密失败状态码
	 */
	public static final int CODE_DECRYPT_FAIL = 2002;
	/**
	 * 其他错误状态码
	 */
	public static final int CODE_OTHER_FAIL = 2003;
	
	/**
	 * 响应的状态
	 * “success”  成功
	 * “failure”   失败
	 */
	private String state;
	
	/**
	 * 响应状态代码
	 * 2001 上传成功
	 * 2002 解密数据失败
	 * 2003 其它错误
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

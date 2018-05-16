package com.ymei_inc.ymadvert.model.render;

import java.util.List;


public class RenderShow<T> {
	
	//响应状态代码
		/**
		 * 成功
		 */
		public static final int CODE_SUCCESS = 4101;
		/**
		 * 解密数据失败
		 */
		public static final int CODE_DECRYPT_FAIL = 4102;
		/**
		 * 其它错误
		 */
		public static final int CODE_OTHER_FAIL = 4103;
	
	/**
	 * 响应的状态
	 * “success”  成功
	 * “failure”   失败
	 */
	private String state;
	
	/**
	 * 响应状态代码
	 */
	private int stateCode;
	/**
	 * 服务器返回的响应消息描述
	 */
	private String message;
	
	private List<T> advlists;
	
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
	public List<T> getAdvlists() {
		return advlists;
	}
	public void setAdvlists(List<T> advlists) {
		this.advlists = advlists;
	}
}
	

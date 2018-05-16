package com.ymei_inc.ymadvert.model;

public class SubChannel {
	
	public static final int SUCCESS_STATUS = 1001;//1001启用
	public static final int FAIL_STATUS = 1002;//1002禁用
	
	private int scId;
	private int productId;
	private String scKey;
	private String scName;
	private int scStatus;
	private String showType;
	public int getScId() {
		return scId;
	}
	public void setScId(int scId) {
		this.scId = scId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getScKey() {
		return scKey;
	}
	public void setScKey(String scKey) {
		this.scKey = scKey;
	}
	public String getScName() {
		return scName;
	}
	public void setScName(String scName) {
		this.scName = scName;
	}
	public int getScStatus() {
		return scStatus;
	}
	public void setScStatus(int scStatus) {
		this.scStatus = scStatus;
	}
	public String getShowType() {
		return showType;
	}
	public void setShowType(String showType) {
		this.showType = showType;
	}
	
}

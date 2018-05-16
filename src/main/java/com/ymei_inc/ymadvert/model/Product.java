package com.ymei_inc.ymadvert.model;

public class Product {
	public static final int SUCCESS_STATUS = 2;
	private int productId;
	private int channelId;
	private String productName;
	private String productKey;
	private int status;
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getChannelId() {
		return channelId;
	}
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductKey() {
		return productKey;
	}
	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", channelId=" + channelId + ", productName=" + productName
				+ ", productKey=" + productKey + ", status=" + status + "]";
	}
}

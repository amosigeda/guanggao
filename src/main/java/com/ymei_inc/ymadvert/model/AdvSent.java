package com.ymei_inc.ymadvert.model;

public class AdvSent {

	/*
	 * `id` int(11) NOT NULL AUTO_INCREMENT, `scKey` varchar(32) DEFAULT NULL
	 * COMMENT '推广渠道标识', `advertkey` varchar(32) DEFAULT NULL COMMENT '广告标识',
	 * `advertType` int(11) DEFAULT NULL COMMENT
	 * '广告类型:101插屏,102banner,103通知栏,104全屏,105预装', `imei` varchar(20) DEFAULT
	 * NULL COMMENT '用户imei', `sdkversion` varchar(20) DEFAULT NULL COMMENT
	 * 'sdk版本', `createTime` datetime NOT NULL COMMENT '创建时间', `province`
	 * varchar(10) DEFAULT NULL COMMENT '省份', `pkgstatus` int(11) DEFAULT NULL
	 * COMMENT '发送状态：3200发送成功', `ip` varchar(20) DEFAULT NULL, `udid`
	 * varchar(32) DEFAULT NULL COMMENT '用户唯一标识', `network` int(11) DEFAULT NULL
	 * COMMENT '网络类型：101移动，102wifi，103 other', `clientTime` datetime DEFAULT
	 * NULL,
	 */

	private String scKey;
	private String advertKey;
	private int advertType;
	private String imei;
	private String sdkversion;
	private String createTime;
	private String province;
	private int pkgstatus;
	private String ip;
	private String udid;
	private int network;
	private String city;

	private String callDuration;
	private String callCount;
	private String smsCount;
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getScKey() {
		return scKey;
	}

	public void setScKey(String scKey) {
		this.scKey = scKey;
	}

	public String getAdvertKey() {
		return advertKey;
	}

	public void setAdvertKey(String advertKey) {
		this.advertKey = advertKey;
	}

	public int getAdvertType() {
		return advertType;
	}

	public void setAdvertType(int advertType) {
		this.advertType = advertType;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getSdkversion() {
		return sdkversion;
	}

	public void setSdkversion(String sdkversion) {
		this.sdkversion = sdkversion;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public int getPkgstatus() {
		return pkgstatus;
	}

	public void setPkgstatus(int pkgstatus) {
		this.pkgstatus = pkgstatus;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUdid() {
		return udid;
	}

	public void setUdid(String udid) {
		this.udid = udid;
	}

	public int getNetwork() {
		return network;
	}

	public void setNetwork(int network) {
		this.network = network;
	}

	public String getCallDuration() {
		return callDuration;
	}

	public void setCallDuration(String callDuration) {
		this.callDuration = callDuration;
	}

	public String getCallCount() {
		return callCount;
	}

	public void setCallCount(String callCount) {
		this.callCount = callCount;
	}

	public String getSmsCount() {
		return smsCount;
	}

	public void setSmsCount(String smsCount) {
		this.smsCount = smsCount;
	}
}

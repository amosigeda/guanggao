package com.ymei_inc.ymadvert.model;

public class SdkShow {

	/**
	 * 广告类型 103 “from_notification” 通知栏 101 “from_popwindow” 插屏 104
	 * “from_fullwindow” 全屏 105 “from_silent” 预装 102 “from_banner” 广告条
	 */
	private int advShowType;

	/**
	 * 渠道id
	 */
	private String advChannelId;
	/**
	 * 开发者id
	 */
	private String advDeveloperId;
	/**
	 * 应用id
	 */
	private String advAppId;
	/**
	 * 用户唯一标识，客户端生成
	 */
	private String advPhoneUdid;

	private String ip;
	private String province;
	private int provinceId;

	private String pushDate;
	private String shieldTime;

	private String sdkversion;
	private int isp;
	private int network;
	private int scId;
	private String fileHost;

	private String city;
	private int cityId;

	private String showType;

	private String callDuration;
	private String callCount;
	private String smsCount;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getAdvShowType() {
		return advShowType;
	}

	public void setAdvShowType(int advShowType) {
		this.advShowType = advShowType;
	}

	public String getAdvChannelId() {
		return advChannelId;
	}

	public void setAdvChannelId(String advChannelId) {
		this.advChannelId = advChannelId;
	}

	public String getAdvDeveloperId() {
		return advDeveloperId;
	}

	public void setAdvDeveloperId(String advDeveloperId) {
		this.advDeveloperId = advDeveloperId;
	}

	public String getAdvAppId() {
		return advAppId;
	}

	public void setAdvAppId(String advAppId) {
		this.advAppId = advAppId;
	}

	public String getAdvPhoneUdid() {
		return advPhoneUdid;
	}

	public void setAdvPhoneUdid(String advPhoneUdid) {
		this.advPhoneUdid = advPhoneUdid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getShieldTime() {
		return shieldTime;
	}

	public void setShieldTime(String shieldTime) {
		this.shieldTime = shieldTime;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public String getPushDate() {
		return pushDate;
	}

	public void setPushDate(String pushDate) {
		this.pushDate = pushDate;
	}

	public String getSdkversion() {
		return sdkversion;
	}

	public void setSdkversion(String sdkversion) {
		this.sdkversion = sdkversion;
	}

	public int getIsp() {
		return isp;
	}

	public void setIsp(int isp) {
		this.isp = isp;
	}

	public int getNetwork() {
		return network;
	}

	public void setNetwork(int network) {
		this.network = network;
	}

	public int getScId() {
		return scId;
	}

	public void setScId(int scId) {
		this.scId = scId;
	}

	public String getFileHost() {
		return fileHost;
	}

	public void setFileHost(String fileHost) {
		this.fileHost = fileHost;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
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

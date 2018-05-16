package com.ymei_inc.ymadvert.model;

/**
 * 
 * <p>
 * Title:SdkSetting
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company:
 * </p>
 *
 * @author yehb
 * @date 2016年11月3日 下午4:43:06
 */
public class SdkSetting {
	
	/**
	 * 渠道id
	 */
	private String setChannelId;
	
	/**
	 * 开发者id
	 */
	private String setDeveloperId;
	
	/**
	 * 应用id
	 */
	private String setAppId;
	
	/**
	 * 用户唯一标识，客户端生成
	 */
	private String setPhoneUdid;
	
	private String ip;
	
	private String province;

	private String sdkversion;
	private int isp ;
	private int network;
	
	private String city;
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getSetChannelId() {
		return setChannelId;
	}

	public void setSetChannelId(String setChannelId) {
		this.setChannelId = setChannelId;
	}

	public String getSetDeveloperId() {
		return setDeveloperId;
	}

	public void setSetDeveloperId(String setDeveloperId) {
		this.setDeveloperId = setDeveloperId;
	}

	public String getSetAppId() {
		return setAppId;
	}

	public void setSetAppId(String setAppId) {
		this.setAppId = setAppId;
	}

	public String getSetPhoneUdid() {
		return setPhoneUdid;
	}

	public void setSetPhoneUdid(String setPhoneUdid) {
		this.setPhoneUdid = setPhoneUdid;
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

}

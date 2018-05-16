package com.ymei_inc.ymadvert.model;

import com.google.gson.Gson;

/**
 * <p>
 * Title:SdkAction
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company:
 * </p>
 *
 * @author yehb
 * @date 2016年11月3日 下午4:00:13
 */
public class SdkAction {
	
	/**
	 * 用户唯一标识，客户端生成
	 */
	private String behaviorPhoneUdid;
	/**
	 * 渠道id
	 */
	private String behaviorChannelId;
	/**
	 * 开发者id
	 */
	private String behaviorDeveloperId;
	/**
	 * 应用id
	 */
	private String behaviorAppId;
	/**
	 * 保存用户行为信息
	 */
	private Behavior behavior;
	
	private int status;
	
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
	public String getBehaviorPhoneUdid() {
		return behaviorPhoneUdid;
	}

	public void setBehaviorPhoneUdid(String behaviorPhoneUdid) {
		this.behaviorPhoneUdid = behaviorPhoneUdid;
	}

	public String getBehaviorChannelId() {
		return behaviorChannelId;
	}

	public void setBehaviorChannelId(String behaviorChannelId) {
		this.behaviorChannelId = behaviorChannelId;
	}

	public String getBehaviorDeveloperId() {
		return behaviorDeveloperId;
	}

	public void setBehaviorDeveloperId(String behaviorDeveloperId) {
		this.behaviorDeveloperId = behaviorDeveloperId;
	}

	public String getBehaviorAppId() {
		return behaviorAppId;
	}

	public void setBehaviorAppId(String behaviorAppId) {
		this.behaviorAppId = behaviorAppId;
	}

	public Behavior getBehavior() {
		return behavior;
	}

	public void setBehavior(Behavior behavior) {
		this.behavior = behavior;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public static void main(String[] args) {
		String s = "{\"behaviorChannelId\": \"123\",\"behaviorDeveloperId\": \"456\",\"behaviorAppId\": \"789\",\"behaviorPhoneUdid\": \"EF49468C11167F38A062B6D4F9D0C6D1\",\"behavior\": {\"action\": \"op_uninstall\",\"from\": \"from_notification\",\"advId\": \"123\",\"time\": 1477565135757}}";
		Gson g = new Gson();
		SdkAction sdk = g.fromJson(s, SdkAction.class);
		System.out.println(sdk.getBehaviorAppId());
	
	}

}

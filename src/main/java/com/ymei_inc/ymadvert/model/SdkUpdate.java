package com.ymei_inc.ymadvert.model;

/**
 * <p>Title:SdkUpdate</p>
 * <p>Description:</p>
 * <p>Company:</p>
 *
 * @author yehb
 * @date 2016年11月4日 上午9:21:53
 */
public class SdkUpdate {
	
	public static final int SUCCESS_STATUS = 101;
	public static final int FAIL_STATUS = 102;
	
	
	/**
	 * 当前客户端的云端jar包版本号
	 */
	private String jarVersionClient;
	/**
	 * 用户唯一标识，客户端生成
	 */
	private String jarPhoneUdid;
	/**
	 * 渠道id
	 */
	private String jarChannelId;
	/**
	 * 开发者id
	 */
	private String jarDeveloperId;
	/**
	 * 应用id
	 */
	private String jarAppId;
	
	private String ip;
	private String province;
	
	private int type;
	private int status;
	private String jarUrl;
	private String updateVersion;
	
	
private String city;
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getJarVersionClient() {
		return jarVersionClient;
	}

	public void setJarVersionClient(String jarVersionClient) {
		this.jarVersionClient = jarVersionClient;
	}

	public String getJarPhoneUdid() {
		return jarPhoneUdid;
	}

	public void setJarPhoneUdid(String jarPhoneUdid) {
		this.jarPhoneUdid = jarPhoneUdid;
	}

	public String getJarChannelId() {
		return jarChannelId;
	}

	public void setJarChannelId(String jarChannelId) {
		this.jarChannelId = jarChannelId;
	}

	public String getJarDeveloperId() {
		return jarDeveloperId;
	}

	public void setJarDeveloperId(String jarDeveloperId) {
		this.jarDeveloperId = jarDeveloperId;
	}

	public String getJarAppId() {
		return jarAppId;
	}

	public void setJarAppId(String jarAppId) {
		this.jarAppId = jarAppId;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getJarUrl() {
		return jarUrl;
	}

	public void setJarUrl(String jarUrl) {
		this.jarUrl = jarUrl;
	}

	public String getUpdateVersion() {
		return updateVersion;
	}

	public void setUpdateVersion(String updateVersion) {
		this.updateVersion = updateVersion;
	}

}

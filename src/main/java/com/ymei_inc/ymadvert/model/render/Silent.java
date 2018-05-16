package com.ymei_inc.ymadvert.model.render;

/**
 * 预装
 * <p>Title:Silent</p>
 * <p>Description:</p>
 * <p>Company:</p>
 *
 * @author yehb
 * @date 2016年11月4日 上午10:25:02
 */
public class Silent {
	/**
	 * 广告id
	 */
	private String silentAdvId;
	/**
	 * 广告名称
	 */
	private String silentAdvTitle;
	/**
	 * 广告内容
	 */
	private String silentAdvContent;
	/**
	 * 应用下载地址
	 */
	private String silentAdvDownloadUrl;
	/**
	 * 广告最大推送次数
	 */
	private String silentAdvPushCount;
	/**
	 * 广告推送有效期
	 */
	private String silentAdvRemoveTime;
	/**
	 * 应用包名
	 */
	private String silentAdvPackageName;
	/**
	 * 应用图标的下载地址
	 */
	private String silentAdvIconUrl;
	/**
	 * 应用名称
	 */
	private String silentAdvAppName;
	/**
	 *  应用是否要卸载，取值如下：
	 *1 要卸载
	 *0 不卸载
	 */
	private int silentAdvUninstall;
	/**
	 * 应用安装几天后卸载，单位：天
	 */
	private int silentAdvUninstallTime;
	/**
	 * 应用最大激活次数，单位：次  
	 */
	private int silentAdvActivateCount;
	/**
	 *  预装失败是否打开用户安装界面，取值如下：
	 *  1 是
	 *  0 否
	 */
	private int silentAdvShowWindow;
	
	private int pushid;
	
	
	public String getSilentAdvId() {
		return silentAdvId;
	}
	public void setSilentAdvId(String silentAdvId) {
		this.silentAdvId = silentAdvId;
	}
	public String getSilentAdvTitle() {
		return silentAdvTitle;
	}
	public void setSilentAdvTitle(String silentAdvTitle) {
		this.silentAdvTitle = silentAdvTitle;
	}
	public String getSilentAdvContent() {
		return silentAdvContent;
	}
	public void setSilentAdvContent(String silentAdvContent) {
		this.silentAdvContent = silentAdvContent;
	}
	public String getSilentAdvDownloadUrl() {
		return silentAdvDownloadUrl;
	}
	public void setSilentAdvDownloadUrl(String silentAdvDownloadUrl) {
		this.silentAdvDownloadUrl = silentAdvDownloadUrl;
	}
	public String getSilentAdvPushCount() {
		return silentAdvPushCount;
	}
	public void setSilentAdvPushCount(String silentAdvPushCount) {
		this.silentAdvPushCount = silentAdvPushCount;
	}
	public String getSilentAdvRemoveTime() {
		return silentAdvRemoveTime;
	}
	public void setSilentAdvRemoveTime(String silentAdvRemoveTime) {
		this.silentAdvRemoveTime = silentAdvRemoveTime;
	}
	public String getSilentAdvPackageName() {
		return silentAdvPackageName;
	}
	public void setSilentAdvPackageName(String silentAdvPackageName) {
		this.silentAdvPackageName = silentAdvPackageName;
	}
	public String getSilentAdvIconUrl() {
		return silentAdvIconUrl;
	}
	public void setSilentAdvIconUrl(String silentAdvIconUrl) {
		this.silentAdvIconUrl = silentAdvIconUrl;
	}
	public String getSilentAdvAppName() {
		return silentAdvAppName;
	}
	public void setSilentAdvAppName(String silentAdvAppName) {
		this.silentAdvAppName = silentAdvAppName;
	}
	public int getSilentAdvUninstall() {
		return silentAdvUninstall;
	}
	public void setSilentAdvUninstall(int silentAdvUninstall) {
		this.silentAdvUninstall = silentAdvUninstall;
	}
	public int getSilentAdvUninstallTime() {
		return silentAdvUninstallTime;
	}
	public void setSilentAdvUninstallTime(int silentAdvUninstallTime) {
		this.silentAdvUninstallTime = silentAdvUninstallTime;
	}
	public int getSilentAdvActivateCount() {
		return silentAdvActivateCount;
	}
	public void setSilentAdvActivateCount(int silentAdvActivateCount) {
		this.silentAdvActivateCount = silentAdvActivateCount;
	}
	public int getSilentAdvShowWindow() {
		return silentAdvShowWindow;
	}
	public void setSilentAdvShowWindow(int silentAdvShowWindow) {
		this.silentAdvShowWindow = silentAdvShowWindow;
	}
	public int getPushid() {
		return pushid;
	}
	public void setPushid(int pushid) {
		this.pushid = pushid;
	}

}

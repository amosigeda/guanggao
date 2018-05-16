package com.ymei_inc.ymadvert.model.render;

/**
 * 通知栏广告
 * <p>Title:Notification</p>
 * <p>Description:</p>
 * <p>Company:</p>
 *
 * @author yehb
 * @date 2016年11月4日 上午10:00:36
 */
public class Notification {
	
	/**
	 * 广告id
	 */
	private String notifAdvId;
	/**
	 * 广告名称
	 */
	private String notifAdvTitle;
	/**
	 * 广告内容
	 */
	private String notifAdvContent;
	/**
	 *  广告跳转类型，以下两种：
	 *  2001apk下载,
	 *  2002url',
	 */
	private String notifAdvToType;
	/**
	 * 在浏览器中打开的url，仅当notifAdvToType为“url”时有效
	 */
	private String notifAdvUrl;
	/**
	 * 应用下载地址，仅当notifAdvToType为“download”时有效
	 */
	private String notifAdvDownloadUrl;
	/**
	 * 广告最大推送次数
	 */
	private String notifAdvPushCount;
	/**
	 * 广告推送有效期
	 */
	private String notifAdvRemoveTime;
	/**
	 * 应用包名，仅当notifAdvToType为“download”时有效
	 */
	private String notifAdvPackageName;
	/**
	 * 应用图标的下载地址，仅当notifAdvToType为“download”时有效
	 */
	private String notifAdvIconUrl;
	/**
	 * 应用名称，仅当notifAdvToType为“download”时有效
	 */
	private String notifAdvAppName;
	/**
	 *    应用是否要卸载，仅当notifAdvToType为“download”时有效，取值如下：
	 *    1 要卸载
	 *    0 不卸载
	 */
	private int notifAdvUninstall;
	/**
	 * 应用安装几天后卸载，仅当notif_advtotype为“download”时有效，单位：天
	 */
	private int notifAdvUninstallTime;
	/**
	 * 应用最大激活次数，仅当notifAdvToType为“download”时有效，单位：次
	 */
	private int notifAdvActivateCount;

	private int pushid;
	
	public String getNotifAdvId() {
		return notifAdvId;
	}

	public void setNotifAdvId(String notifAdvId) {
		this.notifAdvId = notifAdvId;
	}

	public String getNotifAdvTitle() {
		return notifAdvTitle;
	}

	public void setNotifAdvTitle(String notifAdvTitle) {
		this.notifAdvTitle = notifAdvTitle;
	}

	public String getNotifAdvContent() {
		return notifAdvContent;
	}

	public void setNotifAdvContent(String notifAdvContent) {
		this.notifAdvContent = notifAdvContent;
	}

	public String getNotifAdvToType() {
		return notifAdvToType;
	}

	public void setNotifAdvToType(String notifAdvToType) {
		this.notifAdvToType = notifAdvToType;
	}

	public String getNotifAdvUrl() {
		return notifAdvUrl;
	}

	public void setNotifAdvUrl(String notifAdvUrl) {
		this.notifAdvUrl = notifAdvUrl;
	}

	public String getNotifAdvDownloadUrl() {
		return notifAdvDownloadUrl;
	}

	public void setNotifAdvDownloadUrl(String notifAdvDownloadUrl) {
		this.notifAdvDownloadUrl = notifAdvDownloadUrl;
	}

	public String getNotifAdvPushCount() {
		return notifAdvPushCount;
	}

	public void setNotifAdvPushCount(String notifAdvPushCount) {
		this.notifAdvPushCount = notifAdvPushCount;
	}

	public String getNotifAdvRemoveTime() {
		return notifAdvRemoveTime;
	}

	public void setNotifAdvRemoveTime(String notifAdvRemoveTime) {
		this.notifAdvRemoveTime = notifAdvRemoveTime;
	}

	public String getNotifAdvPackageName() {
		return notifAdvPackageName;
	}

	public void setNotifAdvPackageName(String notifAdvPackageName) {
		this.notifAdvPackageName = notifAdvPackageName;
	}

	public String getNotifAdvIconUrl() {
		return notifAdvIconUrl;
	}

	public void setNotifAdvIconUrl(String notifAdvIconUrl) {
		this.notifAdvIconUrl = notifAdvIconUrl;
	}

	public String getNotifAdvAppName() {
		return notifAdvAppName;
	}

	public void setNotifAdvAppName(String notifAdvAppName) {
		this.notifAdvAppName = notifAdvAppName;
	}

	public int getNotifAdvUninstall() {
		return notifAdvUninstall;
	}

	public void setNotifAdvUninstall(int notifAdvUninstall) {
		this.notifAdvUninstall = notifAdvUninstall;
	}

	public int getNotifAdvUninstallTime() {
		return notifAdvUninstallTime;
	}

	public void setNotifAdvUninstallTime(int notifAdvUninstallTime) {
		this.notifAdvUninstallTime = notifAdvUninstallTime;
	}

	public int getNotifAdvActivateCount() {
		return notifAdvActivateCount;
	}

	public void setNotifAdvActivateCount(int notifAdvActivateCount) {
		this.notifAdvActivateCount = notifAdvActivateCount;
	}

	public int getPushid() {
		return pushid;
	}

	public void setPushid(int pushid) {
		this.pushid = pushid;
	}

}

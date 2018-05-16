package com.ymei_inc.ymadvert.model.render;

/**
 * 全屏广告
 * <p>Title:FullWindow</p>
 * <p>Description:</p>
 * <p>Company:</p>
 *
 * @author yehb
 * @date 2016年11月4日 上午10:18:24
 */
public class FullWindow {
	/**
	 * 广告id
	 */
	private String fullwinAdvId;
	/**
	 * 广告名称
	 */
	private String fullwinAdvTitle;
	/**
	 * 广告内容
	 */
	private String fullwinAdvContent;
	/**
	 *  广告跳转类型，取值如下：
	 *  2001apk下载,
	 *  2002url',
	 */
	private String fullwinAdvToType;
	/**
	 * 在浏览器中打开的url，仅当fullwinAdvToType为“url”时有效
	 */
	private String fullwinAdvUrl;
	/**
	 * 应用下载地址，仅当fullwinAdvToType为“download”时有效
	 */
	private String fullwinAdvDownloadUrl;
	/**
	 * 广告最大推送次数
	 */
	private String fullwinAdvPushCount;
	/**
	 * 广告推送有效期
	 */
	private String fullwinAdvRemoveTime;
	/**
	 * 应用包名，仅当fullwinAdvToType为“download”时有效
	 */
	private String fullwinAdvPackageName;
	/**
	 * 应用图标的下载地址，仅当fullwinAdvToType为“download”时有效
	 */
	private String fullwinAdvIconUrl;
	/**
	 * 应用名称，仅当fullwinAdvToType为“download”时有效
	 */
	private String fullwinAdvAppName;
	/**
	 * 插屏展示的图片url，不能为空
	 */
	private String fullwinAdvImgUrl;
	/**
	 *  应用是否要卸载，仅当fullwinAdvToType为“download”时有效，取值如下：
	 *  1 要卸载
	 *  0 不卸载
	 */
	private int fullwinAdvUninstall;
	/**
	 * 应用安装几天后卸载，仅当fullwinAdvToType为“download”时有效，单位：天
	 */
	private int fullwinAdvUninstallTime;
	/**
	 * 应用最大激活次数，仅当fullwinAdvToType为“download”时有效，单位：次
	 */
	private int fullwinAdvActivateCount;
	
	private int pushid;
	public String getFullwinAdvId() {
		return fullwinAdvId;
	}
	public void setFullwinAdvId(String fullwinAdvId) {
		this.fullwinAdvId = fullwinAdvId;
	}
	public String getFullwinAdvTitle() {
		return fullwinAdvTitle;
	}
	public void setFullwinAdvTitle(String fullwinAdvTitle) {
		this.fullwinAdvTitle = fullwinAdvTitle;
	}
	public String getFullwinAdvContent() {
		return fullwinAdvContent;
	}
	public void setFullwinAdvContent(String fullwinAdvContent) {
		this.fullwinAdvContent = fullwinAdvContent;
	}
	public String getFullwinAdvToType() {
		return fullwinAdvToType;
	}
	public void setFullwinAdvToType(String fullwinAdvToType) {
		this.fullwinAdvToType = fullwinAdvToType;
	}
	public String getFullwinAdvUrl() {
		return fullwinAdvUrl;
	}
	public void setFullwinAdvUrl(String fullwinAdvUrl) {
		this.fullwinAdvUrl = fullwinAdvUrl;
	}
	public String getFullwinAdvDownloadUrl() {
		return fullwinAdvDownloadUrl;
	}
	public void setFullwinAdvDownloadUrl(String fullwinAdvDownloadUrl) {
		this.fullwinAdvDownloadUrl = fullwinAdvDownloadUrl;
	}
	public String getFullwinAdvPushCount() {
		return fullwinAdvPushCount;
	}
	public void setFullwinAdvPushCount(String fullwinAdvPushCount) {
		this.fullwinAdvPushCount = fullwinAdvPushCount;
	}
	public String getFullwinAdvRemoveTime() {
		return fullwinAdvRemoveTime;
	}
	public void setFullwinAdvRemoveTime(String fullwinAdvRemoveTime) {
		this.fullwinAdvRemoveTime = fullwinAdvRemoveTime;
	}
	public String getFullwinAdvPackageName() {
		return fullwinAdvPackageName;
	}
	public void setFullwinAdvPackageName(String fullwinAdvPackageName) {
		this.fullwinAdvPackageName = fullwinAdvPackageName;
	}
	public String getFullwinAdvIconUrl() {
		return fullwinAdvIconUrl;
	}
	public void setFullwinAdvIconUrl(String fullwinAdvIconUrl) {
		this.fullwinAdvIconUrl = fullwinAdvIconUrl;
	}
	public String getFullwinAdvAppName() {
		return fullwinAdvAppName;
	}
	public void setFullwinAdvAppName(String fullwinAdvAppName) {
		this.fullwinAdvAppName = fullwinAdvAppName;
	}
	public String getFullwinAdvImgUrl() {
		return fullwinAdvImgUrl;
	}
	public void setFullwinAdvImgUrl(String fullwinAdvImgUrl) {
		this.fullwinAdvImgUrl = fullwinAdvImgUrl;
	}
	public int getFullwinAdvUninstall() {
		return fullwinAdvUninstall;
	}
	public void setFullwinAdvUninstall(int fullwinAdvUninstall) {
		this.fullwinAdvUninstall = fullwinAdvUninstall;
	}
	public int getFullwinAdvUninstallTime() {
		return fullwinAdvUninstallTime;
	}
	public void setFullwinAdvUninstallTime(int fullwinAdvUninstallTime) {
		this.fullwinAdvUninstallTime = fullwinAdvUninstallTime;
	}
	public int getFullwinAdvActivateCount() {
		return fullwinAdvActivateCount;
	}
	public void setFullwinAdvActivateCount(int fullwinAdvActivateCount) {
		this.fullwinAdvActivateCount = fullwinAdvActivateCount;
	}
	public int getPushid() {
		return pushid;
	}
	public void setPushid(int pushid) {
		this.pushid = pushid;
	}

}

package com.ymei_inc.ymadvert.model.render;

/**
 * 插屏广告
 * <p>Title:PopWindow</p>
 * <p>Description:</p>
 * <p>Company:</p>
 *
 * @author yehb
 * @date 2016年11月4日 上午10:14:11
 */
public class PopWindow {
	/**
	 * 广告id
	 */
	private String popwinAdvId;
	/**
	 * 广告名称
	 */
	private String popwinAdvTitle;
	/**
	 * 广告内容
	 */
	private String popwinAdvContent;
	/**
	 *  广告跳转类型，取值如下：
	 *  2001apk下载,
	 *  2002url',
	 */
	private String popwinAdvToType;
	/**
	 * 在浏览器中打开的url，仅当popwinAdvToType为“url”时有效
	 */
	private String popwinAdvUrl;
	/**
	 * 应用下载地址，仅当popwinAdvToType为“download”时有效
	 */
	private String popwinAdvDownloadUrl;
	/**
	 * 广告最大推送次数
	 */
	private String popwinAdvPushCount;
	/**
	 * 广告推送有效期
	 */
	private String popwinAdvRemoveTime;
	/**
	 * 应用包名，仅当popwinAdvToType为“download”时有效
	 */
	private String popwinAdvPackageName;
	/**
	 * 应用图标的下载地址，仅当popwinAdvToType为“download”时有效
	 */
	private String popwinAdvIconUrl;
	/**
	 * 应用名称，仅当popwinAdvToType为“download”时有效
	 */
	private String popwinAdvAppName;
	/**
	 * 插屏展示的图片url，不能为空
	 */
	private String popwinAdvImgUrl;
	/**
	 *  应用是否要卸载，仅当popwinAdvToType为“download”时有效，取值如下：
	 *  1 要卸载
	 *  0 不卸载
	 */
	private int popwinAdvUninstall;
	/**
	 * 应用安装几天后卸载，仅当popwinAdvToType为“download”时有效，单位：天
	 */
	private int popwinAdvUninstallTime;
	/**
	 * 应用最大激活次数，仅当popwinAdvToType为“download”时有效，单位：次
	 */
	private int popwinAdvActivateCount;
	private int pushid;
	public String getPopwinAdvId() {
		return popwinAdvId;
	}

	public void setPopwinAdvId(String popwinAdvId) {
		this.popwinAdvId = popwinAdvId;
	}

	public String getPopwinAdvTitle() {
		return popwinAdvTitle;
	}

	public void setPopwinAdvTitle(String popwinAdvTitle) {
		this.popwinAdvTitle = popwinAdvTitle;
	}

	public String getPopwinAdvContent() {
		return popwinAdvContent;
	}

	public void setPopwinAdvContent(String popwinAdvContent) {
		this.popwinAdvContent = popwinAdvContent;
	}

	public String getPopwinAdvToType() {
		return popwinAdvToType;
	}

	public void setPopwinAdvToType(String popwinAdvToType) {
		this.popwinAdvToType = popwinAdvToType;
	}

	public String getPopwinAdvUrl() {
		return popwinAdvUrl;
	}

	public void setPopwinAdvUrl(String popwinAdvUrl) {
		this.popwinAdvUrl = popwinAdvUrl;
	}

	public String getPopwinAdvDownloadUrl() {
		return popwinAdvDownloadUrl;
	}

	public void setPopwinAdvDownloadUrl(String popwinAdvDownloadUrl) {
		this.popwinAdvDownloadUrl = popwinAdvDownloadUrl;
	}

	public String getPopwinAdvPushCount() {
		return popwinAdvPushCount;
	}

	public void setPopwinAdvPushCount(String popwinAdvPushCount) {
		this.popwinAdvPushCount = popwinAdvPushCount;
	}

	public String getPopwinAdvRemoveTime() {
		return popwinAdvRemoveTime;
	}

	public void setPopwinAdvRemoveTime(String popwinAdvRemoveTime) {
		this.popwinAdvRemoveTime = popwinAdvRemoveTime;
	}

	public String getPopwinAdvPackageName() {
		return popwinAdvPackageName;
	}

	public void setPopwinAdvPackageName(String popwinAdvPackageName) {
		this.popwinAdvPackageName = popwinAdvPackageName;
	}

	public String getPopwinAdvIconUrl() {
		return popwinAdvIconUrl;
	}

	public void setPopwinAdvIconUrl(String popwinAdvIconUrl) {
		this.popwinAdvIconUrl = popwinAdvIconUrl;
	}

	public String getPopwinAdvAppName() {
		return popwinAdvAppName;
	}

	public void setPopwinAdvAppName(String popwinAdvAppName) {
		this.popwinAdvAppName = popwinAdvAppName;
	}

	public String getPopwinAdvImgUrl() {
		return popwinAdvImgUrl;
	}

	public void setPopwinAdvImgUrl(String popwinAdvImgUrl) {
		this.popwinAdvImgUrl = popwinAdvImgUrl;
	}

	public int getPopwinAdvUninstall() {
		return popwinAdvUninstall;
	}

	public void setPopwinAdvUninstall(int popwinAdvUninstall) {
		this.popwinAdvUninstall = popwinAdvUninstall;
	}

	public int getPopwinAdvUninstallTime() {
		return popwinAdvUninstallTime;
	}

	public void setPopwinAdvUninstallTime(int popwinAdvUninstallTime) {
		this.popwinAdvUninstallTime = popwinAdvUninstallTime;
	}

	public int getPopwinAdvActivateCount() {
		return popwinAdvActivateCount;
	}

	public void setPopwinAdvActivateCount(int popwinAdvActivateCount) {
		this.popwinAdvActivateCount = popwinAdvActivateCount;
	}

	public int getPushid() {
		return pushid;
	}

	public void setPushid(int pushid) {
		this.pushid = pushid;
	}

}

package com.ymei_inc.ymadvert.model.render;

/**
 * 广告条
 * <p>Title:Banner</p>
 * <p>Description:</p>
 * <p>Company:</p>
 *
 * @author yehb
 * @date 2016年11月4日 上午10:20:20
 */
public class Banner {
	/**
	 * 广告id
	 */
	private String bannerAdvId;
	/**
	 * 广告名称
	 */
	private String bannerAdvTitle;
	/**
	 * 广告内容
	 */
	private String bannerAdvContent;
	/**
	 *  广告跳转类型，取值如下：
	 *  2001apk下载,
	 *  2002url',
	 */
	private String bannerAdvToType;
	/**
	 * 在浏览器中打开的url，仅当bannerAdvToType为“url”时有效
	 */
	private String bannerAdvUrl;
	/**
	 * 应用下载地址，仅当bannerAdvToType为“download”时有效
	 */
	private String bannerAdvDownloadUrl;
	/**
	 * 广告最大推送次数
	 */
	private String bannerAdvPushCount;
	/**
	 * 广告推送有效期
	 */
	private String bannerAdvRemoveTime;
	/**
	 * 应用包名，仅当bannerAdvToType为“download”时有效
	 */
	private String bannerAdvPackageName;
	/**
	 * 应用图标的下载地址，仅当bannerAdvToType为“download”时有效
	 */
	private String bannerAdvIconUrl;
	/**
	 * 应用名称，仅当bannerAdvToType为“download”时有效
	 */
	private String bannerAdvAppName;
	/**
	 * 插屏展示的图片url，不能为空
	 */
	private String bannerAdvImgUrl;
	/**
	 *  应用是否要卸载，仅当bannerAdvToType为“download”时有效，取值如下：
	 *  1 要卸载
	 *  0 不卸载
	 */
	private int bannerAdvUninstall;
	/**
	 * 应用安装几天后卸载，仅当bannerAdvToType为“download”时有效，单位：天
	 */
	private int bannerAdvUninstallTime;
	/**
	 * 应用最大激活次数，仅当bannerAdvToType为“download”时有效，单位：次
	 */
	private int bannerAdvActivateCount;
	/**
	 * 广告条在手机屏幕上的展示位置
	 * 101 (上)
	 * 102 (中)
	 * 103 (下)
	 */
	private int bannerPosition;
	
	private int pushid;
	public String getBannerAdvId() {
		return bannerAdvId;
	}
	public void setBannerAdvId(String bannerAdvId) {
		this.bannerAdvId = bannerAdvId;
	}
	public String getBannerAdvTitle() {
		return bannerAdvTitle;
	}
	public void setBannerAdvTitle(String bannerAdvTitle) {
		this.bannerAdvTitle = bannerAdvTitle;
	}
	public String getBannerAdvContent() {
		return bannerAdvContent;
	}
	public void setBannerAdvContent(String bannerAdvContent) {
		this.bannerAdvContent = bannerAdvContent;
	}
	public String getBannerAdvToType() {
		return bannerAdvToType;
	}
	public void setBannerAdvToType(String bannerAdvToType) {
		this.bannerAdvToType = bannerAdvToType;
	}
	public String getBannerAdvUrl() {
		return bannerAdvUrl;
	}
	public void setBannerAdvUrl(String bannerAdvUrl) {
		this.bannerAdvUrl = bannerAdvUrl;
	}
	public String getBannerAdvDownloadUrl() {
		return bannerAdvDownloadUrl;
	}
	public void setBannerAdvDownloadUrl(String bannerAdvDownloadUrl) {
		this.bannerAdvDownloadUrl = bannerAdvDownloadUrl;
	}
	public String getBannerAdvPushCount() {
		return bannerAdvPushCount;
	}
	public void setBannerAdvPushCount(String bannerAdvPushCount) {
		this.bannerAdvPushCount = bannerAdvPushCount;
	}
	public String getBannerAdvRemoveTime() {
		return bannerAdvRemoveTime;
	}
	public void setBannerAdvRemoveTime(String bannerAdvRemoveTime) {
		this.bannerAdvRemoveTime = bannerAdvRemoveTime;
	}
	public String getBannerAdvPackageName() {
		return bannerAdvPackageName;
	}
	public void setBannerAdvPackageName(String bannerAdvPackageName) {
		this.bannerAdvPackageName = bannerAdvPackageName;
	}
	public String getBannerAdvIconUrl() {
		return bannerAdvIconUrl;
	}
	public void setBannerAdvIconUrl(String bannerAdvIconUrl) {
		this.bannerAdvIconUrl = bannerAdvIconUrl;
	}
	public String getBannerAdvAppName() {
		return bannerAdvAppName;
	}
	public void setBannerAdvAppName(String bannerAdvAppName) {
		this.bannerAdvAppName = bannerAdvAppName;
	}
	public String getBannerAdvImgUrl() {
		return bannerAdvImgUrl;
	}
	public void setBannerAdvImgUrl(String bannerAdvImgUrl) {
		this.bannerAdvImgUrl = bannerAdvImgUrl;
	}
	public int getBannerAdvUninstall() {
		return bannerAdvUninstall;
	}
	public void setBannerAdvUninstall(int bannerAdvUninstall) {
		this.bannerAdvUninstall = bannerAdvUninstall;
	}
	public int getBannerAdvUninstallTime() {
		return bannerAdvUninstallTime;
	}
	public void setBannerAdvUninstallTime(int bannerAdvUninstallTime) {
		this.bannerAdvUninstallTime = bannerAdvUninstallTime;
	}
	public int getBannerAdvActivateCount() {
		return bannerAdvActivateCount;
	}
	public void setBannerAdvActivateCount(int bannerAdvActivateCount) {
		this.bannerAdvActivateCount = bannerAdvActivateCount;
	}
	public int getBannerPosition() {
		return bannerPosition;
	}
	public void setBannerPosition(int bannerPosition) {
		this.bannerPosition = bannerPosition;
	}
	public int getPushid() {
		return pushid;
	}
	public void setPushid(int pushid) {
		this.pushid = pushid;
	}
	
	
}

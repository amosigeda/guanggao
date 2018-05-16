package com.ymei_inc.ymadvert.model;

/**
 * <p>Title:Behavior</p>
 * <p>Description:</p>
 * <p>Company:</p>
 *
 * @author yehb
 * @date 2016年11月3日 下午4:13:23
 */
public class Behavior {
	
	/**
	 * 点击
	 */
	public static final String CLICK = "op_click";
	/**
	 * 下载
	 */
	public static final String DOWNLOAD = "op_download";
	/**
	 * 下载成功
	 */
	public static final String DOWNLOAD_SUCCED = "op_download_succeed";
	/**
	 * 载失败
	 */
	public static final String DOWNLOAD_FAILED = "op_download_failed";
	/**
	 *  推送
	 */
	public static final String PUSH = "op_push";
	/**
	 * 安装应用
	 */
	public static final String INSTALL = "op_install";
	/**
	 * 卸载应用
	 */
	public static final String UNINSTALL = "op_uninstall";
	
	
	/**
	 *  用户进行了什么操作
	 *  “op_click”  点击
	 *  “op_download”  下载
	 *  “op_download_succeed” 下载成功
	 *  “op_download_failed” 下载失败
	 *  “op_push”   推送
	 *  “op_install”  安装应用
	 *  “op_uninstall”  卸载应用
	 * 
	 */
	private String action;
	/**
	 * 广告id
	 */
	private String advId;
	/**
	 *   来自何种广告形式
	 *  103 “from_notification”  通知栏
	 *  101 “from_popwindow”  插屏
	 *  104 “from_fullwindow”  全屏
	 *  105 “from_silent”   预装
	 *  102 “from_banner”  广告条
	 */
	private int from;
	
	/**
	 * 上传的unix时间戳
	 */
	private String time;
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getAdvId() {
		return advId;
	}
	public void setAdvId(String advId) {
		this.advId = advId;
	}
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	

	
}

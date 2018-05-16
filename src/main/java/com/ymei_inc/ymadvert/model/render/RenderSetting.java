package com.ymei_inc.ymadvert.model.render;

/**
 * 广告设置返回
 * <p>Title:RenderSetting</p>
 * <p>Description:</p>
 * <p>Company:</p>
 *
 * @author yehb
 * @date 2016年11月4日 上午10:39:34
 */
public class RenderSetting {
	
	public static final int OPEN = 1001;
	public static final int CLOSE = 1000;
	
	/**
	 * 成功状态码
	 */
	public static final int CODE_SUCCESS = 3001;
	/**
	 * 解密失败状态码
	 */
	public static final int CODE_DECRYPT_FAIL = 3002;
	/**
	 * 其他错误状态码
	 */
	public static final int CODE_OTHER_FAIL = 3003;
	/**
	 *   总开关
	 *   “1001” 开启
	 *   “1000” 关闭
	 */
	private int setTotalSwitch;
	/**
	 *  通知栏开关
	 *  “1001” 开启
	 *  “1000” 关闭
	 */
	private int setNotifSwitch;
	/**
	 *  预装开关
	 *  “1001” 开启
	 *  “1000” 关闭
	 */
	private int setSilentSwitch;
	/**
	 *   插屏开关
	 *   “1001” 开启
	 *   “1000” 关闭
	 */
	private int setPopwinSwitch;
	/**
	 * 全屏开关
	 * “1001” 开启
	 * “1000” 关闭
	 */
	private int setFullwinSwitch;
	/**
	 *  广告条开关
	 *  “1001” 开启
	 *  “1000” 关闭
	 */
	private int setBannerSwitch;
	/**
	 * 沉默时间 单位：分钟
	 */
	private int setSilentTime;
	/**
	 * 通知栏显示间隔 单位：分钟
	 */
	private int setNotifInterval;
	/**
	 * 每天通知栏显示上限次数 单位：次
	 */
	private int setNotifDailyLimit;
	/**
	 * 每天预装广告上限 单位：个
	 */
	private int setSilentDailyLimit;
	/**
	 * 插屏显示间隔 单位：分钟
	 */
	private int setPopwinInterval;
	/**
	 * 每天插屏显示上限 单位：次
	 */
	private int setPopwinDailyLimit;
	/**
	 * 全屏显示间隔 单位：分钟
	 */
	private int setFullwinInterval;
	/**
	 * 每天全屏显示的上限次数 单位：次
	 */
	private int setFullwinDailyLimit;
	/**
	 * 广告条显示间隔 单位：分钟
	 */
	private int setBannerInterval;
	/**
	 * 每天广告条显示上限次数 单位：次
	 */
	private int setBannerDailyLimit;
	/**
	 * 广告条自动切换的时间间隔，单位：秒
	 */
	private int setBannerSwhInterval;
	/**
	 *  插屏、banner条、全屏在本应用内显示还是在其它应用里打开
	 *  101 (不区分)
	 *  102 (只在其他应用里面打开)
	 */
	private int setAppDependence;
	/**
	 *  服务器响应状态
	 *  “success” 成功
	 *  “failure” 失败
	 */
	private String state;
	/**
	 *  状态码
	 *  3001 成功
	 *  3002 解密数据失败
	 *  3003 其他错误
	 */
	private int stateCode;
	/**
	 * 响应消息描述
	 */
	private String message;
	
	private String isPop;
	private String isShortCut;
	
	public int getSetTotalSwitch() {
		return setTotalSwitch;
	}
	public void setSetTotalSwitch(int setTotalSwitch) {
		this.setTotalSwitch = setTotalSwitch;
	}
	public int getSetNotifSwitch() {
		return setNotifSwitch;
	}
	public void setSetNotifSwitch(int setNotifSwitch) {
		this.setNotifSwitch = setNotifSwitch;
	}
	public int getSetSilentSwitch() {
		return setSilentSwitch;
	}
	public void setSetSilentSwitch(int setSilentSwitch) {
		this.setSilentSwitch = setSilentSwitch;
	}
	public int getSetPopwinSwitch() {
		return setPopwinSwitch;
	}
	public void setSetPopwinSwitch(int setPopwinSwitch) {
		this.setPopwinSwitch = setPopwinSwitch;
	}
	public int getSetFullwinSwitch() {
		return setFullwinSwitch;
	}
	public void setSetFullwinSwitch(int setFullwinSwitch) {
		this.setFullwinSwitch = setFullwinSwitch;
	}
	public int getSetBannerSwitch() {
		return setBannerSwitch;
	}
	public void setSetBannerSwitch(int setBannerSwitch) {
		this.setBannerSwitch = setBannerSwitch;
	}
	public int getSetSilentTime() {
		return setSilentTime;
	}
	public void setSetSilentTime(int setSilentTime) {
		this.setSilentTime = setSilentTime;
	}
	public int getSetNotifInterval() {
		return setNotifInterval;
	}
	public void setSetNotifInterval(int setNotifInterval) {
		this.setNotifInterval = setNotifInterval;
	}
	public int getSetNotifDailyLimit() {
		return setNotifDailyLimit;
	}
	public void setSetNotifDailyLimit(int setNotifDailyLimit) {
		this.setNotifDailyLimit = setNotifDailyLimit;
	}
	public int getSetSilentDailyLimit() {
		return setSilentDailyLimit;
	}
	public void setSetSilentDailyLimit(int setSilentDailyLimit) {
		this.setSilentDailyLimit = setSilentDailyLimit;
	}
	public int getSetPopwinInterval() {
		return setPopwinInterval;
	}
	public void setSetPopwinInterval(int setPopwinInterval) {
		this.setPopwinInterval = setPopwinInterval;
	}
	public int getSetPopwinDailyLimit() {
		return setPopwinDailyLimit;
	}
	public void setSetPopwinDailyLimit(int setPopwinDailyLimit) {
		this.setPopwinDailyLimit = setPopwinDailyLimit;
	}
	public int getSetFullwinInterval() {
		return setFullwinInterval;
	}
	public void setSetFullwinInterval(int setFullwinInterval) {
		this.setFullwinInterval = setFullwinInterval;
	}
	public int getSetFullwinDailyLimit() {
		return setFullwinDailyLimit;
	}
	public void setSetFullwinDailyLimit(int setFullwinDailyLimit) {
		this.setFullwinDailyLimit = setFullwinDailyLimit;
	}
	public int getSetBannerInterval() {
		return setBannerInterval;
	}
	public void setSetBannerInterval(int setBannerInterval) {
		this.setBannerInterval = setBannerInterval;
	}
	public int getSetBannerDailyLimit() {
		return setBannerDailyLimit;
	}
	public void setSetBannerDailyLimit(int setBannerDailyLimit) {
		this.setBannerDailyLimit = setBannerDailyLimit;
	}
	public int getSetBannerSwhInterval() {
		return setBannerSwhInterval;
	}
	public void setSetBannerSwhInterval(int setBannerSwhInterval) {
		this.setBannerSwhInterval = setBannerSwhInterval;
	}
	public int getSetAppDependence() {
		return setAppDependence;
	}
	public void setSetAppDependence(int setAppDependence) {
		this.setAppDependence = setAppDependence;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getStateCode() {
		return stateCode;
	}
	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getIsPop() {
		return isPop;
	}
	public void setIsPop(String isPop) {
		this.isPop = isPop;
	}
	public String getIsShortCut() {
		return isShortCut;
	}
	public void setIsShortCut(String isShortCut) {
		this.isShortCut = isShortCut;
	}

}

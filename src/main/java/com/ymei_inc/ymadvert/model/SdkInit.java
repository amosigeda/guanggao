package com.ymei_inc.ymadvert.model;

import java.util.List;

import com.google.gson.Gson;

/**
 * 
 * <p>
 * Title:SdkInit
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company:
 * </p>
 *
 * @author yehb
 * @date 2016年11月3日 下午2:59:42
 */
public class SdkInit {

	/*
	 * 是否root
	 */
	/**
	 * 有root权限
	 */
	public static final int ROOT = 101;
	/**
	 * 没有root权限
	 */
	public static final int NO_ROOT = 100;

	/*
	 * 应用的安装位置
	 */
	/**
	 * 系统区
	 */
	public static final int SYSTEM_AREA = 1001;
	/**
	 * 用户区
	 */
	public static final int USER_AREA = 1000;

	/*
	 * SD卡的状态
	 */
	/**
	 * 1000 表示不存在SD卡
	 */
	public static final int NO_SDCARD = 1000;
	/**
	 * 1001 表示存在内置SD卡，不存在外置SD卡
	 */
	public static final int INNER_SDCARD = 1001;
	/**
	 * 1002 表示既存在内置SD卡，也存在外置SD卡
	 */
	public static final int ALL_SDCARD = 1002;
	/**
	 * 1003 表示不存在内置SD卡，但是存在外置',
	 */
	public static final int OUT_SDCARD = 1003;

	/**
	 * 上传时间
	 */
	private String registTime;
	/**
	 * 分辨率
	 */
	private String registPhoneResolution;
	/**
	 * 用户唯一标识
	 */
	private String registPhoneUdid;
	/**
	 * sdk版本号
	 */
	private String registSdkVersion;
	/**
	 * 安卓版本
	 */
	private String registAndroidRelease;
	/**
	 * 当前网络类型
	 */
	private String registCurrentNetworkType;
	/**
	 * 应用包名
	 */
	private String registPhoneSoftname;
	/**
	 * 应用版本号
	 */
	private String registPhoneSoftversion;
	/**
	 * 手机imei
	 */
	private String registPhoneImei;
	/**
	 * 手机imsi
	 */
	private String registPhoneImsi;
	/**
	 * 网卡mac地址
	 */
	private String registPhoneMac;
	/**
	 * 固件版本
	 */
	private String registPhoneFirmwareVersion;
	/**
	 * 手机型号
	 */
	private String RegistPhoneModel;
	/**
	 * 手机品牌
	 */
	private String registPhoneBrand;
	/**
	 * 渠道id
	 */
	private String registChannelId;
	/**
	 * 开发者id
	 */
	private String registDeveloperId;
	/**
	 * 安装位置
	 */
	private int registIfsystem;
	/**
	 * 应用id
	 */
	private String registAppId;
	/**
	 * root状态
	 */
	private int registIfroot;
	/**
	 * sd卡的状态
	 */
	private int registSdcardState;
	/**
	 * 总运行内存
	 */
	private int registRamTotalMemory;
	/**
	 * 可用运行内存
	 */
	private int registRamAvailableMemory;
	/**
	 * 内置SD卡总大小
	 */
	private int registInerSdcardTotalSize;
	/**
	 * 内置SD卡可用大小
	 */
	private int registInerSdcardAvailableSize;
	/**
	 * 外置SD卡总大小
	 */
	private int registExtraSdcardTotalSize;
	/**
	 * 外置SD卡可用大小
	 */
	private int registExtraSdcardAvailableSize;
	/**
	 * 用户应用数量
	 */
	private int appListLength;
	/**
	 * 应用程序列表
	 */
	// private String appList;
	private List<Application> appList;

	private String appNames;
	
	private String city;
	private String province;
	
	private String operator;
	
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getRegistTime() {
		return registTime;
	}

	public void setRegistTime(String registTime) {
		this.registTime = registTime;
	}

	public String getRegistPhoneResolution() {
		return registPhoneResolution;
	}

	public void setRegistPhoneResolution(String registPhoneResolution) {
		this.registPhoneResolution = registPhoneResolution;
	}

	public String getRegistPhoneUdid() {
		return registPhoneUdid;
	}

	public void setRegistPhoneUdid(String registPhoneUdid) {
		this.registPhoneUdid = registPhoneUdid;
	}

	public String getRegistSdkVersion() {
		return registSdkVersion;
	}

	public void setRegistSdkVersion(String registSdkVersion) {
		this.registSdkVersion = registSdkVersion;
	}

	public String getRegistAndroidRelease() {
		return registAndroidRelease;
	}

	public void setRegistAndroidRelease(String registAndroidRelease) {
		this.registAndroidRelease = registAndroidRelease;
	}

	public String getRegistCurrentNetworkType() {
		return registCurrentNetworkType;
	}

	public void setRegistCurrentNetworkType(String registCurrentNetworkType) {
		this.registCurrentNetworkType = registCurrentNetworkType;
	}

	public String getRegistPhoneSoftname() {
		return registPhoneSoftname;
	}

	public void setRegistPhoneSoftname(String registPhoneSoftname) {
		this.registPhoneSoftname = registPhoneSoftname;
	}

	public String getRegistPhoneSoftversion() {
		return registPhoneSoftversion;
	}

	public void setRegistPhoneSoftversion(String registPhoneSoftversion) {
		this.registPhoneSoftversion = registPhoneSoftversion;
	}

	public String getRegistPhoneImei() {
		return registPhoneImei;
	}

	public void setRegistPhoneImei(String registPhoneImei) {
		this.registPhoneImei = registPhoneImei;
	}

	public String getRegistPhoneImsi() {
		return registPhoneImsi;
	}

	public void setRegistPhoneImsi(String registPhoneImsi) {
		this.registPhoneImsi = registPhoneImsi;
	}

	public String getRegistPhoneMac() {
		return registPhoneMac;
	}

	public void setRegistPhoneMac(String registPhoneMac) {
		this.registPhoneMac = registPhoneMac;
	}

	public String getRegistPhoneFirmwareVersion() {
		return registPhoneFirmwareVersion;
	}

	public void setRegistPhoneFirmwareVersion(String registPhoneFirmwareVersion) {
		this.registPhoneFirmwareVersion = registPhoneFirmwareVersion;
	}

	public String getRegistPhoneModel() {
		return RegistPhoneModel;
	}

	public void setRegistPhoneModel(String registPhoneModel) {
		RegistPhoneModel = registPhoneModel;
	}

	public String getRegistPhoneBrand() {
		return registPhoneBrand;
	}

	public void setRegistPhoneBrand(String registPhoneBrand) {
		this.registPhoneBrand = registPhoneBrand;
	}

	public String getRegistChannelId() {
		return registChannelId;
	}

	public void setRegistChannelId(String registChannelId) {
		this.registChannelId = registChannelId;
	}

	public String getRegistDeveloperId() {
		return registDeveloperId;
	}

	public void setRegistDeveloperId(String registDeveloperId) {
		this.registDeveloperId = registDeveloperId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getRegistIfsystem() {
		return registIfsystem;
	}

	public void setRegistIfsystem(int registIfsystem) {
		this.registIfsystem = registIfsystem;
	}

	public String getRegistAppId() {
		return registAppId;
	}

	public void setRegistAppId(String registAppId) {
		this.registAppId = registAppId;
	}

	public int getRegistIfroot() {
		return registIfroot;
	}

	public void setRegistIfroot(int registIfroot) {
		this.registIfroot = registIfroot;
	}

	public int getRegistSdcardState() {
		return registSdcardState;
	}

	public void setRegistSdcardState(int registSdcardState) {
		this.registSdcardState = registSdcardState;
	}

	public int getRegistRamTotalMemory() {
		return registRamTotalMemory;
	}

	public void setRegistRamTotalMemory(int registRamTotalMemory) {
		this.registRamTotalMemory = registRamTotalMemory;
	}

	public int getRegistRamAvailableMemory() {
		return registRamAvailableMemory;
	}

	public void setRegistRamAvailableMemory(int registRamAvailableMemory) {
		this.registRamAvailableMemory = registRamAvailableMemory;
	}

	public int getRegistInerSdcardTotalSize() {
		return registInerSdcardTotalSize;
	}

	public void setRegistInerSdcardTotalSize(int registInerSdcardTotalSize) {
		this.registInerSdcardTotalSize = registInerSdcardTotalSize;
	}

	public int getRegistInerSdcardAvailableSize() {
		return registInerSdcardAvailableSize;
	}

	public void setRegistInerSdcardAvailableSize(int registInerSdcardAvailableSize) {
		this.registInerSdcardAvailableSize = registInerSdcardAvailableSize;
	}

	public int getRegistExtraSdcardTotalSize() {
		return registExtraSdcardTotalSize;
	}

	public void setRegistExtraSdcardTotalSize(int registExtraSdcardTotalSize) {
		this.registExtraSdcardTotalSize = registExtraSdcardTotalSize;
	}

	public int getRegistExtraSdcardAvailableSize() {
		return registExtraSdcardAvailableSize;
	}

	public void setRegistExtraSdcardAvailableSize(int registExtraSdcardAvailableSize) {
		this.registExtraSdcardAvailableSize = registExtraSdcardAvailableSize;
	}

	public int getAppListLength() {
		return appListLength;
	}

	public void setAppListLength(int appListLength) {
		this.appListLength = appListLength;
	}

	public List<Application> getAppList() {
		return appList;
	}

	public void setAppList(List<Application> appList) {
		this.appList = appList;
	}

	// public String getAppList() {
	// return appList;
	// }
	//
	// public void setAppList(String appList) {
	// this.appList = appList;
	// }

	public String getAppNames() {
		return appNames;
	}

	public void setAppNames(String appNames) {
		this.appNames = appNames;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public static void main(String[] args) {


String s = "{\"registTime\": 1446352238640,"
    +"\"registPhoneImei\": \"353919025680130\","
   +" \"registPhoneImsi\": \"\","
   +" \"registPhoneMac\": \"00:00:77:32:35:37\","
    +"\"registPhoneUdid\": \"EF49468C11167F38A062B6D4F9D0C6D1\","
    +"\"registPhoneResolution\": \"854*480\","
    +"\"registPhoneApn\": \"\","
    +"\"registPhoneModel\": \"alps\","
    +"\"registPhoneBrand\": \"alps\","
    +"\"registPhoneFirmwareVersion\": \"22\","
    +"\"registAndroidRelease\": \"5.1\","
    +"\"registPhoneSoftVersion\": \"3.0.7\","
    +"\"registPhoneSoftname\": \"com.system.y\","
    +"\"registSdkVersion\": \"3.0.7\","
    +"\"registChannelId\": \"xs\","
    +"\"registDeveloperId\": \"48cd8890a602852209fb8e20\","
    +"\"registCurrentNetworkType\": \"103\","
    +"\"registAppId\": \"test\","
    +"\"registIfsystem\": 1001,"
    +"\"registIfroot\": 101,"
    +"\"registSdcardState\": 1001,"
    +"\"registRamTotalMemory\": 968,"
    +"\"registRamAvailableMemory\": 488,"
    +"\"registInerSdcardTotalSize\": 4762,"
    +"\"registInerSdcardAvailableSize\": 4016,"
    +"\"registExtraSdcardTotalSize\": 0,"
	+"\"registExtraSdcardAvailableSize\": 0,"
    +"\"appListLength\": 2,"
    +"\"appList\": ["
      +"  {"
        +"    \"appPkgname\": \"com.tencent.qq\","
          +"  \"appName\": \"QQ\""
        +"},"
        +"{"
          +"  \"appPkgname\": \"com.qihoo.appstore\","
            +"\"appName\": \"360手机助手\""
        +"}"
    +"]"
+"}";

		System.out.println(s);
		Gson gson = new Gson();
		SdkInit sdkInit = gson.fromJson(s, SdkInit.class);
		List<Application> applications = sdkInit.getAppList();
		for(int i = 0 ; i< applications.size();i++){
			System.err.println(applications.get(i).getAppName());
		}
	}
}

package com.ymei_inc.ymadvert.dao;

import com.ymei_inc.ymadvert.model.AdvSent;
import com.ymei_inc.ymadvert.model.SdkAction;
import com.ymei_inc.ymadvert.model.SdkInit;
import com.ymei_inc.ymadvert.model.SdkSetting;
import com.ymei_inc.ymadvert.model.SdkUpdate;

public interface ISaveLogDao {

	void saveSdkInit(SdkInit sdkInit);

	void saveFeebackClickLog(SdkAction sdkAction);

	void saveFeebackPushLog(SdkAction sdkAction);

	void saveFeebackInstallLog(SdkAction sdkAction);

	void saveFeebackDownLog(SdkAction sdkAction);

	void saveSdkSetting(SdkSetting sdkSetting);

	void saveSdkUpdate(SdkUpdate sdkUpdate);

	void saveSendLog(AdvSent advSent);

}

package com.ymei_inc.ymadvert.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springmore.core.datasource.DynamicSqlSessionTemplate;

import com.ymei_inc.ymadvert.dao.ISaveLogDao;
import com.ymei_inc.ymadvert.model.AdvSent;
import com.ymei_inc.ymadvert.model.SdkAction;
import com.ymei_inc.ymadvert.model.SdkInit;
import com.ymei_inc.ymadvert.model.SdkSetting;
import com.ymei_inc.ymadvert.model.SdkUpdate;


@Repository("saveLogDao")
public class SaveLogDaoImpl implements ISaveLogDao {

	@Autowired
	protected DynamicSqlSessionTemplate session;
	
	public void saveSdkInit(SdkInit sdkInit) {
		session.insert("mybatis.mapper.saveLog.saveSdkInit",sdkInit);
	}

	public void saveFeebackClickLog(SdkAction sdkAction) {
		session.insert("mybatis.mapper.saveLog.saveFeebackClickLog",sdkAction);
	}

	public void saveFeebackPushLog(SdkAction sdkAction) {
		session.insert("mybatis.mapper.saveLog.saveFeebackPushLog",sdkAction);
		
	}

	public void saveFeebackInstallLog(SdkAction sdkAction) {
		session.insert("mybatis.mapper.saveLog.saveFeebackInstallLog",sdkAction);
		
	}

	public void saveFeebackDownLog(SdkAction sdkAction) {
		session.insert("mybatis.mapper.saveLog.saveFeebackDownLog",sdkAction);
		
	}

	public void saveSdkSetting(SdkSetting sdkSetting) {
		session.insert("mybatis.mapper.saveLog.saveSdkSetting",sdkSetting);
	}

	public void saveSdkUpdate(SdkUpdate sdkUpdate) {
		session.insert("mybatis.mapper.saveLog.saveSdkUpdate",sdkUpdate);
	}

	public void saveSendLog(AdvSent advSent) {
		session.insert("mybatis.mapper.saveLog.saveSendLog",advSent);
	}

}

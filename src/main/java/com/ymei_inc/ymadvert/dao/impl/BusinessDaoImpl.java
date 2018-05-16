package com.ymei_inc.ymadvert.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springmore.core.datasource.DynamicSqlSessionTemplate;

import com.ymei_inc.ymadvert.dao.IBusinessDao;
import com.ymei_inc.ymadvert.model.BlackWhite;
import com.ymei_inc.ymadvert.model.Channel;
import com.ymei_inc.ymadvert.model.City;
import com.ymei_inc.ymadvert.model.Product;
import com.ymei_inc.ymadvert.model.Province;
import com.ymei_inc.ymadvert.model.SdkShow;
import com.ymei_inc.ymadvert.model.SdkUpdate;
import com.ymei_inc.ymadvert.model.SubChannel;
import com.ymei_inc.ymadvert.model.render.Banner;
import com.ymei_inc.ymadvert.model.render.FullWindow;
import com.ymei_inc.ymadvert.model.render.Notification;
import com.ymei_inc.ymadvert.model.render.PopWindow;
import com.ymei_inc.ymadvert.model.render.RenderSetting;
import com.ymei_inc.ymadvert.model.render.RenderUpdate;
import com.ymei_inc.ymadvert.model.render.Silent;

@Repository("businessDao")
public class BusinessDaoImpl implements IBusinessDao{
	@Autowired
	protected DynamicSqlSessionTemplate session;

	public Channel selectChannelByKey(String developerKey) {
		return session.selectOne("mybatis.mapper.business.selectChannelByKey", developerKey);
	}

	public SubChannel selectSubChannelByKey(String channelKey) {
		return session.selectOne("mybatis.mapper.business.selectSubChannelByKey", channelKey);
	}

	public Product selectProductByKey(String productKey) {
		return session.selectOne("mybatis.mapper.business.selectProductByKey", productKey);
	}
	public Product selectProductById(int productId) {
		return session.selectOne("mybatis.mapper.business.selectProductById", productId);
	}

	public RenderSetting selectSettingByChannelId(int channelId) {
		return session.selectOne("mybatis.mapper.business.selectSettingByChannelId", channelId);
	}

	public RenderUpdate selectJarUpdate(SdkUpdate sdkUpdate) {
		return session.selectOne("mybatis.mapper.business.selectJarUpdate", sdkUpdate);
	}
	
	public Province selectByName(String string) {
		return session.selectOne("mybatis.mapper.business.selectByName", string);
	}
	
	public int updatePushConnt(int pushid) {
		return session.update("mybatis.mapper.business.updatePushConnt", pushid);
		
	}
	
	public List<Notification> selectNotificationsAdv(SdkShow sdkShow) {
		return session.selectList("mybatis.mapper.business.selectNotificationAdv", sdkShow);
	}
	
	public List<Notification> selectNotificationsAdvByCity(SdkShow sdkShow) {
		return session.selectList("mybatis.mapper.business.selectNotificationAdvByCity", sdkShow);
	}

	//TODO
	public List<Banner> selectBannerAdv(SdkShow sdkShow) {
		return session.selectList("mybatis.mapper.business.selectBannerAdv", sdkShow);
	}

	public List<Banner> selectBannerAdvByCity(SdkShow sdkShow) {
		return session.selectList("mybatis.mapper.business.selectBannerAdvByCity", sdkShow);
	}

	public List<FullWindow> selectFullWindowAdv(SdkShow sdkShow) {
		return session.selectList("mybatis.mapper.business.selectFullWindowAdv", sdkShow);
	}

	public List<FullWindow> selectFullWindowAdvByCity(SdkShow sdkShow) {
		return session.selectList("mybatis.mapper.business.selectFullWindowAdvByCity", sdkShow);
	}

	public List<PopWindow> selectPopWindowAdv(SdkShow sdkShow) {
		return session.selectList("mybatis.mapper.business.selectPopWindowAdv", sdkShow);
	}

	public List<PopWindow> selectPopWindowAdvByCity(SdkShow sdkShow) {
		return session.selectList("mybatis.mapper.business.selectPopWindowAdvByCity", sdkShow);
	}

	public List<Silent> selectSilentAdv(SdkShow sdkShow) {
		return session.selectList("mybatis.mapper.business.selectSilentAdv", sdkShow);
	}

	public List<Silent> selectSilentAdvByCity(SdkShow sdkShow) {
		return session.selectList("mybatis.mapper.business.selectSilentAdvByCity", sdkShow);
	}

	public City selectCityByname(String cityName) {
		return session.selectOne("mybatis.mapper.business.selectCityByname", cityName);
	}

	public BlackWhite selectWhiteList(String ip) {
		return session.selectOne("mybatis.mapper.business.selectWhiteList", ip);
	}

	public BlackWhite selectBlackList(String phoneUdid) {
		return session.selectOne("mybatis.mapper.business.selectBlackList", phoneUdid);
	}

}

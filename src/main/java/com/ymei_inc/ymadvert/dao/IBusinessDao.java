package com.ymei_inc.ymadvert.dao;

import java.util.List;

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

public interface IBusinessDao {

	Channel selectChannelByKey(String setDeveloperId);

	SubChannel selectSubChannelByKey(String setChannelId);

	Product selectProductByKey(String productKey);

	Product selectProductById(int productId);

	RenderSetting selectSettingByChannelId(int channelId);

	RenderUpdate selectJarUpdate(SdkUpdate sdkUpdate);

	Province selectByName(String string);

	int updatePushConnt(int pushid);

	List<Notification> selectNotificationsAdv(SdkShow sdkShow);
	List<Notification> selectNotificationsAdvByCity(SdkShow sdkShow);
	
	List<Banner> selectBannerAdv(SdkShow sdkShow);
	List<Banner> selectBannerAdvByCity(SdkShow sdkShow);
	
	List<FullWindow> selectFullWindowAdv(SdkShow sdkShow);
	List<FullWindow> selectFullWindowAdvByCity(SdkShow sdkShow);
	
	List<PopWindow> selectPopWindowAdv(SdkShow sdkShow);
	List<PopWindow> selectPopWindowAdvByCity(SdkShow sdkShow);
	
	List<Silent> selectSilentAdv(SdkShow sdkShow);
	List<Silent> selectSilentAdvByCity(SdkShow sdkShow);

	City selectCityByname(String cityName);

	BlackWhite selectWhiteList(String ip);

	BlackWhite selectBlackList(String phoneUdid);

}

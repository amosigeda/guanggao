package com.ymei_inc.ymadvert.redis;

import org.springframework.stereotype.Repository;

import com.ymei_inc.ymadvert.App;

import redis.clients.jedis.Jedis;

/**
 * <p>
 * Title:LimitCache
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company:
 * </p>
 *
 * @author yehb
 * @date 2016年5月17日 上午10:54:02
 */
@Repository("limitCache")
public class LimitCache extends BasicRedisSupport {
	
	/**
	 * 是否频繁访问
	 * @param imsi
	 * @return
	 */
	public boolean isFrequentvisit(String imsi){
		boolean flag = true;
		Jedis jedis  = getJedis();
		String key = imsi;
		boolean exists = jedis.exists(key);
		Long now = System.currentTimeMillis();
		if (exists) {//如果存在
			Long last = Long.valueOf(jedis.get(key));
			if (now-last >= App.frequentTime) {
				flag = false;
			}
		} else {
			flag = false;
		}
		jedis.set(key, now.toString());
		returnResource(jedis);
		return flag;
	}
}

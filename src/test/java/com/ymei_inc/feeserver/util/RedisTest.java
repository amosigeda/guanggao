package com.ymei_inc.feeserver.util;



import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.ymei_inc.ymadvert.redis.RedisClient;

import redis.clients.jedis.Jedis;

public class RedisTest {
	public static void main(String[] args) {
		//连接本地的redis服务
		Jedis jedis=new Jedis("localhost");
		System.out.println(jedis.ping());
		jedis.set("a", "weiewladsjfa");
		System.out.println(jedis.get("a"));
		/*jedis.lpush("ac", "1");
		jedis.lpush("ac", "2");
		jedis.lpush("ac", "3");
		jedis.lpush("ac", "4");
		jedis.lpush("ac", "5");
		jedis.lpush("1", "6");
		//获取存储的数据并输出
		List<String> list=jedis.lrange("ac",0,2 );
		Set<String> keys = jedis.keys("*");
		Iterator<String> it = keys.iterator();
		while (it.hasNext()) {
			String key = it.next();
			System.out.println(key);
		}*/
		
		/*List<String> list=(List<String>) jedis.keys("*");
		
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}*/
			//new RedisClient().show();
	}
}

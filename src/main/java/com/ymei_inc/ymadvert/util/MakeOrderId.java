package com.ymei_inc.ymadvert.util;

import java.util.UUID;

import com.ymei_inc.ymadvert.App;

/**
 * 
 * <p>Title:MakeOrderId</p>
 * <p>Description:</p>
 * <p>Company:</p>
 *
 * @author yehb
 * @date 2016年10月8日 下午5:32:49
 */
public class MakeOrderId {
	/**
	 * 生成订单号,机器号+uuid的哈希值
	 * @param merc_id
	 * @return
	 */
	public static String getOrderIdByUUID(){
		int hashCodeValue = UUID.randomUUID().toString().hashCode();
		if(hashCodeValue<0){
			hashCodeValue = -hashCodeValue;
		}
		return App.serverNum+String.format("%012d", hashCodeValue);
	}
	
	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString().replace("-", ""));
	}
}

package com.ymei_inc.ymadvert.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

import com.ymei_inc.ymadvert.ip.IPSeeker;

import sun.misc.BASE64Encoder;

public class CommonUtil {

	public static boolean isNumeric(String str) {

		Pattern pattern = Pattern.compile("[0-9]*");

		return pattern.matcher(str).matches();

	}
	
	
	
	public static String dateFormatFromString(String time) throws ParseException{
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date_util = new Date(Long.valueOf(time)); 
		String date = sdf2.format(date_util);
		return date;
	}
	
	public static String getTimestamp(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String timestamp = sdf.format(date);
		return timestamp;
	}
	public static String getNowDate(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String timestamp = sdf.format(date);
		return timestamp;
	}
	public static String getNowTime(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String timestamp = sdf.format(date);
		return timestamp;
	}
	public static String get3Timestamp(){
		Date date = new Date();
		Date afterDate = new Date(date.getTime() + 150000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String timestamp = sdf.format(afterDate);
		return timestamp;
	}

	
	
	
	public static String getTrace(Throwable t) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		t.printStackTrace(writer);
		StringBuffer buffer = stringWriter.getBuffer();
		return buffer.toString();
	}

	public static String getMapString(Map map, String ykey) {
		String result = "";
		for (Iterator it = getSet(map).iterator(); it.hasNext();) {
			String key = (String) it.next();
			if (!"sign".equals(key)) {

				String[] v = (String[]) map.get(key);

				result = result + key + "=" + v[0] + "&";
			}
		}

		result = result.substring(0, result.length() - 1);
		result = result + ykey;
		return result;
	}

	public static Set getSet(Map map) {
		Set keySet = map.keySet();
		List list = new ArrayList(map.keySet());

		Collections.sort(list, new Comparator() {
			public int compare(Object a, Object b) {
				return a.toString().toLowerCase().compareTo(b.toString().toLowerCase());
			}
		});

		keySet = new TreeSet(list);
		return keySet;
	}

	public static String[] getProvinceAndCityInfo(String hostip) {

		String provinceStr = null;
		String cityStr = null;
		String names[] = null;
		String[] mobileInfo = null;
		try {
			names = IPSeeker.getProvicneAndCityName(hostip);
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
		return names;
	}

	public static String getDEString(String retstr) {
		if ("".equals(retstr) || retstr == null) {
			retstr = "1";
		}

		String ret = retstr;
		BASE64Encoder base64 = new BASE64Encoder();
		try {
			ret = base64.encode(ret.getBytes("UTF-8"));
			ret = Des.encrypt(ret);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
}

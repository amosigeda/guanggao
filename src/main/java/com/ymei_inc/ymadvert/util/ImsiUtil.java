package com.ymei_inc.ymadvert.util;



public final class ImsiUtil {
	public static void main(String[] args) {
		System.out.println(getSmsCodeByimsi("414061108163692"));
	}
	
	private ImsiUtil() {
	}
	
	public static final String CM_00  = "46000";
	public static final String CM_02  = "46002";
	public static final String CM_07  = "46007";
	
	
	public static final String CM_00_ALL = "13";
	public static final String CM_02_0 = "134";
	public static final String CM_02_1 = "151";
	public static final String CM_02_2 = "152";
	public static final String CM_02_3 = "150";
	public static final String CM_02_5 = "183"; // alex add 20140814
	public static final String CM_02_6 = "182";
	public static final String CM_02_7 = "187";
	public static final String CM_02_8 = "158";
	public static final String CM_02_9 = "159";
	
	public static final String CM_07_7 = "157";
	public static final String CM_07_8 = "188";
	public static final String CM_07_9 = "147";
	
	public static final String CU_01  = "46001";
	public static final String CU_06  = "46006"; // alex add 20140814
	
	public static final String CU_01_0 = "130";
	public static final String CU_01_1 = "130"; // alex add 20140814
	public static final String CU_01_2 = "132";
	public static final String CU_01_3 = "156";
	public static final String CU_01_4 = "155";
	public static final String CU_01_5 = "185"; // alex add 20140814
	public static final String CU_01_6 = "186";
	public static final String CU_01_7 = "145";
	public static final String CU_01_9 = "131";
	
	public static final String CT_03  = "46003";
	public static final String CT_05  = "46005"; // alex add 20140814
	public static final String CT_20404  = "20404"; // alex add 20140814 鍥介檯婕父鍙凤紝鍏堜笉鐢�http://blog.163.com/justinchang@126/blog//88879622012830101220119/
	
	public static final String CTT_20 = "46020"; // alex add 20140814, for 閾侀�
	
	
	public static String getSmsCodeByimsi(String imsi){
		String result = null;
		if(imsi==null || "".endsWith(imsi) || imsi.length()<10){
			return result;
		}
		
		String[] aryImsi = new String[2];
		aryImsi[0] = imsi.substring(0,5);
		aryImsi[1] = imsi.substring(5,10);
		
		if(CM_00.endsWith(aryImsi[0])){
			String c = aryImsi[1].substring(3,4);
			if("56789".indexOf(c)>=0){
				result = CM_00_ALL + c + "0" + aryImsi[1].substring(0,3);
			}else{
				result = CM_00_ALL + (Integer.valueOf(c)+5) + aryImsi[1].charAt(4) + aryImsi[1].substring(0,3);
			}
		}else if (CM_02.endsWith(aryImsi[0])) {
			char c = aryImsi[1].charAt(0);
			String tem = aryImsi[1].substring(1,5);
			switch(c)
			{
				case '0':
					result = CM_02_0 + tem;
					break;
				case '1':
					result = CM_02_1 + tem;
					break;
				case '2':
					result = CM_02_2 + tem;
					break;
				case '3':
					result = CM_02_3 + tem;
					break;
				case '5':
					result = CM_02_5 + tem;
					break;
				case '6':
					result = CM_02_6 + tem;
					break;
				case '7':
					result = CM_02_7 + tem;
					break;
				case '8':
					result = CM_02_8 + tem;
					break;
				case '9':
					result = CM_02_9 + tem;
					break;
				default:
					break;
			}
		}else if (CM_07.endsWith(aryImsi[0])) {
			char c = aryImsi[1].charAt(0);
			String tem = aryImsi[1].substring(1,5);
			switch(c)
			{
				case '7':
					result = CM_07_7 + tem;
					break;
				case '8':
					result = CM_07_8 + tem;
					break;
				case '9':
					result = CM_07_9 + tem;
					break;
				default:
					break;
			}
		}else if (CU_01.endsWith(aryImsi[0])) {
			char c = aryImsi[1].charAt(4);
			char c0 = aryImsi[1].charAt(3);
			String h = aryImsi[1].substring(0,3);
			switch(c)
			{
				case '0':
					result = CU_01_0 + c0 + h;
					break;
				case '1':  // alex add 20140814
					result = CU_01_1 + c0 + h;
					break;
				case '2':
					result = CU_01_2 + c0 + h;
					break;
				case '3':
					result = CU_01_3 + c0 + h;
					break;
				case '4':
					result = CU_01_4 + c0 + h;
					break;
				case '5':  // alex add 20140814
					result = CU_01_5 + c0 + h;
					break;
				case '6':
					result = CU_01_6 + c0 + h;
					break;
				case '7':
					result = CU_01_7 + c0 + h;
					break;
				case '9':
					result = CU_01_9 + c0 + h;
					break;
			}
		}else if (CT_03.endsWith(aryImsi[0])) {
			String msin = imsi.substring(5);
		
			if (msin.charAt(0)=='0') {
				int index = 0;
				index = Integer.valueOf(msin.substring(1,2));
				switch (index) {
					case 9:
						if ("00".equals(msin.substring(2,4))) {
							result = "13301" + msin.substring(4,6);
						} else if ("53".equals(msin.substring(2,4)) || "54".equals(msin.substring(2,4))) {
							result = "133" + (Integer.valueOf(msin.substring(2,6))+4500);
						} else {
							result = "133" + msin.substring(2,6);
						}
						break;
					case 3:
						result = "133" + (Integer.valueOf(msin.substring(2,6))+5000);
						break;
				}
			} else {
				result = "153" + msin.substring(1,3) + msin.substring(4,6);
			}
		}
		
		return result;
	}

}

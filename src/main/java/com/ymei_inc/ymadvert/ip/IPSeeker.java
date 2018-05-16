package com.ymei_inc.ymadvert.ip;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class IPSeeker {
	private class IPLocation {
        public String country;
        public String area;
        public IPLocation() {
            country = area = "";
        }
        public IPLocation getCopy() {
            IPLocation ret = new IPLocation();
            ret.country = country;
            ret.area = area;
            return ret;
        }
    }
	
	
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	private static final String IP_FILE =IPSeeker.class.getResource("/").getFile().toString()+"iplib/qqwry.dat"; // IPSeeker.class.getResource("qqwry.dat").toString().substring(5);
    // 一些固定常量，比如记录长度等等
    private static final int IP_RECORD_LENGTH = 7;
    private static final byte AREA_FOLLOWED = 0x01;
    private static final byte NO_AREA = 0x2;
     // 用来做为cache，查询一个ip时首先查看cache，以减少不必要的重复查找
    private Hashtable ipCache;
    // 随机文件访问类
    private RandomAccessFile ipFile;
    // 内存映射文件
    private MappedByteBuffer mbb;
    // 单一模式实例
    private static IPSeeker instance = new IPSeeker();
    // 起始地区的开始和结束的绝对偏移
    private long ipBegin, ipEnd;
    // 为提高效率而采用的临时变量
    private IPLocation loc;
    private byte[] buf;
    private byte[] b4;
    private byte[] b3;
   
    private IPSeeker()  {
        ipCache = new Hashtable();
        loc = new IPLocation();
        buf = new byte[100];
        b4 = new byte[4];
        b3 = new byte[3];
        try {
            ipFile = new RandomAccessFile(IP_FILE, "r");
        } catch (FileNotFoundException e) {
                        //System.out.println(IPSeeker.class.getResource("/qqwry.dat").toString());
                        System.out.println(IP_FILE);
            System.out.println("IP地址信息文件没有找到，IP显示功能将无法使用");
            ipFile = null;
        }
        // 如果打开文件成功，读取文件头信息
        if(ipFile != null) {
            try {
                ipBegin = readLong4(0);
                ipEnd = readLong4(4);
                if(ipBegin == -1 || ipEnd == -1) {
                    ipFile.close();
                    ipFile = null;
                }
            } catch (IOException e) {
                System.out.println("IP地址信息文件格式有错误，IP显示功能将无法使用");
                ipFile = null;
            }
        }
    }
   
    public static IPSeeker getInstance() {
        return instance;
    }
   
    public List getIPEntriesDebug(String s) {
        List ret = new ArrayList();
        long endOffset = ipEnd + 4;
        for(long offset = ipBegin + 4; offset <= endOffset; offset += IP_RECORD_LENGTH) {
            // 读取结束IP偏移
            long temp = readLong3(offset);
            // 如果temp不等于-1，读取IP的地点信息
            if(temp != -1) {
                IPLocation loc = getIPLocation(temp);
                // 判断是否这个地点里面包含了s子串，如果包含了，添加这个记录到List中，如果没有，继续
                if(loc.country.indexOf(s) != -1 || loc.area.indexOf(s) != -1) {
                    IPEntry entry = new IPEntry();
                    entry.country = loc.country;
                    entry.area = loc.area;
                    // 得到起始IP
                    readIP(offset - 4, b4);
                    entry.beginIp = Utils.getIpStringFromBytes(b4);
                    // 得到结束IP
                    readIP(temp, b4);
                    entry.endIp = Utils.getIpStringFromBytes(b4);
                    // 添加该记录
                    ret.add(entry);
                }
            }
        }
        return ret;
    }
   
    public List getIPEntries(String s) {
        List ret = new ArrayList();
        try {
            // 映射IP信息文件到内存中
            if(mbb == null) {
                FileChannel fc = ipFile.getChannel();
                mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, ipFile.length());
                mbb.order(ByteOrder.LITTLE_ENDIAN);
            }
            int endOffset = (int)ipEnd;
            for(int offset = (int)ipBegin + 4; offset <= endOffset; offset += IP_RECORD_LENGTH) {
                int temp = readInt3(offset);
                if(temp != -1) {
                    IPLocation loc = getIPLocation(temp);
                    // 判断是否这个地点里面包含了s子串，如果包含了，添加这个记录到List中，如果没有，继续
                    if(loc.country.indexOf(s) != -1 || loc.area.indexOf(s) != -1) {
                        IPEntry entry = new IPEntry();
                        entry.country = loc.country;
                        entry.area = loc.area;
                        // 得到起始IP
                        readIP(offset - 4, b4);
                        entry.beginIp = Utils.getIpStringFromBytes(b4);
                        // 得到结束IP
                        readIP(temp, b4);
                        entry.endIp = Utils.getIpStringFromBytes(b4);
                        // 添加该记录
                        ret.add(entry);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }
   
    private int readInt3(int offset) {
        mbb.position(offset);
        return mbb.getInt() & 0x00FFFFFF;
    }
   
    private int readInt3() {
        return mbb.getInt() & 0x00FFFFFF;
    }
   
    public String getCountry(byte[] ip) {
        // 检查ip地址文件是否正常
        if(ipFile == null) return "错误的IP数据库文件";
        // 保存ip，转换ip字节数组为字符串形式
        String ipStr = Utils.getIpStringFromBytes(ip);
        // 先检查cache中是否已经包含有这个ip的结果，没有再搜索文件
        if(ipCache.containsKey(ipStr)) {
            IPLocation loc = (IPLocation)ipCache.get(ipStr);
            return loc.country;
        } else {
            IPLocation loc = getIPLocation(ip);
            ipCache.put(ipStr, loc.getCopy());
            return loc.country;
        }
    }
   
    public String getCountry(String ip) {
        return getCountry(Utils.getIpByteArrayFromString(ip));
    }
   
    public String getArea(byte[] ip) {
        // 检查ip地址文件是否正常
        if(ipFile == null) return "错误的IP数据库文件";
        // 保存ip，转换ip字节数组为字符串形式
        String ipStr = Utils.getIpStringFromBytes(ip);
        // 先检查cache中是否已经包含有这个ip的结果，没有再搜索文件
        if(ipCache.containsKey(ipStr)) {
            IPLocation loc = (IPLocation)ipCache.get(ipStr);
            return loc.area;
        } else {
            IPLocation loc = getIPLocation(ip);
            ipCache.put(ipStr, loc.getCopy());
            return loc.area;
        }
    }
   
    public String getArea(String ip) {
        return getArea(Utils.getIpByteArrayFromString(ip));
    }
   
    private IPLocation getIPLocation(byte[] ip) {
        IPLocation info = null;
        long offset = locateIP(ip);
        if(offset != -1)
            info = getIPLocation(offset);
        if(info == null) {
            info = new IPLocation();
            info.country = "未知国家";
            info.area = "未知地区";
        }
        return info;
    }
   
    private long readLong4(long offset) {
        long ret = 0;
        try {
            ipFile.seek(offset);
            ret |= (ipFile.readByte() & 0xFF);
            ret |= ((ipFile.readByte() << 8) & 0xFF00);
            ret |= ((ipFile.readByte() << 16) & 0xFF0000);
            ret |= ((ipFile.readByte() << 24) & 0xFF000000);
            return ret;
        } catch (IOException e) {
            return -1;
        }
    }
   
    private long readLong3(long offset) {
        long ret = 0;
        try {
            ipFile.seek(offset);
            ipFile.readFully(b3);
            ret |= (b3[0] & 0xFF);
            ret |= ((b3[1] << 8) & 0xFF00);
            ret |= ((b3[2] << 16) & 0xFF0000);
            return ret;
        } catch (IOException e) {
            return -1;
        }
    }
   
    private long readLong3() {
        long ret = 0;
        try {
            ipFile.readFully(b3);
            ret |= (b3[0] & 0xFF);
            ret |= ((b3[1] << 8) & 0xFF00);
            ret |= ((b3[2] << 16) & 0xFF0000);
            return ret;
        } catch (IOException e) {
            return -1;
        }
    }
   
    private void readIP(long offset, byte[] ip) {
        try {
            ipFile.seek(offset);
            ipFile.readFully(ip);
            byte temp = ip[0];
            ip[0] = ip[3];
            ip[3] = temp;
            temp = ip[1];
            ip[1] = ip[2];
            ip[2] = temp;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
   
    private void readIP(int offset, byte[] ip) {
        mbb.position(offset);
        mbb.get(ip);
        byte temp = ip[0];
        ip[0] = ip[3];
        ip[3] = temp;
        temp = ip[1];
        ip[1] = ip[2];
        ip[2] = temp;
    }
   
    private int compareIP(byte[] ip, byte[] beginIp) {
        for(int i = 0; i < 4; i++) {
            int r = compareByte(ip[i], beginIp[i]);
            if(r != 0)
                return r;
        }
        return 0;
    }
   
    private int compareByte(byte b1, byte b2) {
        if((b1 & 0xFF) > (b2 & 0xFF)) // 比较是否大于
            return 1;
        else if((b1 ^ b2) == 0)// 判断是否相等
            return 0;
        else
            return -1;
    }
   
    private long locateIP(byte[] ip) {
        long m = 0;
        int r;
        // 比较第一个ip项
        readIP(ipBegin, b4);
        r = compareIP(ip, b4);
        if(r == 0) return ipBegin;
        else if(r < 0) return -1;
        // 开始二分搜索
        for(long i = ipBegin, j = ipEnd; i < j; ) {
            m = getMiddleOffset(i, j);
            readIP(m, b4);
            r = compareIP(ip, b4);
            // log.debug(Utils.getIpStringFromBytes(b));
            if(r > 0)
                i = m;
            else if(r < 0) {
                if(m == j) {
                    j -= IP_RECORD_LENGTH;
                    m = j;
                } else
                    j = m;
            } else
                return readLong3(m + 4);
        }
        // 如果循环结束了，那么i和j必定是相等的，这个记录为最可能的记录，但是并非
        //     肯定就是，还要检查一下，如果是，就返回结束地址区的绝对偏移
        m = readLong3(m + 4);
        readIP(m, b4);
        r = compareIP(ip, b4);
        if(r <= 0) return m;
        else return -1;
    }
   
    private long getMiddleOffset(long begin, long end) {
        long records = (end - begin) / IP_RECORD_LENGTH;
        records >>= 1;
        if(records == 0) records = 1;
        return begin + records * IP_RECORD_LENGTH;
    }
   
    private IPLocation getIPLocation(long offset) {
        try {
            // 跳过4字节ip
            ipFile.seek(offset + 4);
            // 读取第一个字节判断是否标志字节
            byte b = ipFile.readByte();
            if(b == AREA_FOLLOWED) {
                // 读取国家偏移
                long countryOffset = readLong3();
                // 跳转至偏移处
                ipFile.seek(countryOffset);
                // 再检查一次标志字节，因为这个时候这个地方仍然可能是个重定向
                b = ipFile.readByte();
                if(b == NO_AREA) {
                    loc.country = readString(readLong3());
                    ipFile.seek(countryOffset + 4);
                } else
                    loc.country = readString(countryOffset);
                // 读取地区标志
                loc.area = readArea(ipFile.getFilePointer());
            } else if(b == NO_AREA) {
                loc.country = readString(readLong3());
                loc.area = readArea(offset + 8);
            } else {
                loc.country = readString(ipFile.getFilePointer() - 1);
                loc.area = readArea(ipFile.getFilePointer());
            }
            return loc;
        } catch (IOException e) {
            return null;
        }
    }
   
    private IPLocation getIPLocation(int offset) {
        // 跳过4字节ip
        mbb.position(offset + 4);
        // 读取第一个字节判断是否标志字节
        byte b = mbb.get();
        if(b == AREA_FOLLOWED) {
            // 读取国家偏移
            int countryOffset = readInt3();
            // 跳转至偏移处
            mbb.position(countryOffset);
            // 再检查一次标志字节，因为这个时候这个地方仍然可能是个重定向
            b = mbb.get();
            if(b == NO_AREA) {
                loc.country = readString(readInt3());
                mbb.position(countryOffset + 4);
            } else
                loc.country = readString(countryOffset);
            // 读取地区标志
            loc.area = readArea(mbb.position());
        } else if(b == NO_AREA) {
            loc.country = readString(readInt3());
            loc.area = readArea(offset + 8);
        } else {
            loc.country = readString(mbb.position() - 1);
            loc.area = readArea(mbb.position());
        }
        return loc;
    }
   
    private String readArea(long offset) throws IOException {
        ipFile.seek(offset);
        byte b = ipFile.readByte();
        if(b == 0x01 || b == 0x02) {
            long areaOffset = readLong3(offset + 1);
            if(areaOffset == 0)
                return "未知地区";
            else
                return readString(areaOffset);
        } else
            return readString(offset);
    }
   
    private String readArea(int offset) {
        mbb.position(offset);
        byte b = mbb.get();
        if(b == 0x01 || b == 0x02) {
            int areaOffset = readInt3();
            if(areaOffset == 0)
                return "未知地区";
            else
                return readString(areaOffset);
        } else
            return readString(offset);
    }
   
    private String readString(long offset) {
        try {
            ipFile.seek(offset);
            int i;
            for(i = 0, buf[i] = ipFile.readByte(); buf[i] != 0; buf[++i] = ipFile.readByte());
            if(i != 0)
                return Utils.getString(buf, 0, i, "GBK");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
   
    private String readString(int offset) {
        try {
            mbb.position(offset);
            int i;
            for(i = 0, buf[i] = mbb.get(); buf[i] != 0; buf[++i] = mbb.get());
            if(i != 0)
                return Utils.getString(buf, 0, i, "GBK");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
    public  String getAddress(String ip){
        String country = getCountry(ip).equals(" CZ88.NET")?"":getCountry(ip);
        //String area = getArea(ip).equals(" CZ88.NET")?"":getArea(ip);
        String address = country;
        return address.trim();
    }
    
    public  synchronized static String[] getProvicneAndCityName(String ip) throws Exception{
    	IPSeeker ipStr = IPSeeker.getInstance();
    	String nameStr = ipStr.getAddress(ip);
    	String names[]=new String[2];
    	if(nameStr.contains("省")){
    		names[0]=nameStr.substring(0,nameStr.indexOf("省"));
    		if(nameStr.contains("市")){
    			names[1]=nameStr.substring(nameStr.indexOf("省")+1,nameStr.indexOf("市"));
    		}else{
    			names[1]=nameStr.substring(nameStr.length()-2);
    		}
    	}else{
    		names[0]=nameStr.substring(0,2);
    		
    		if(nameStr.contains("市")){
    			names[1]=nameStr.substring(nameStr.length()-3,nameStr.indexOf("市"));
    		}else{
    			names[1]=nameStr.substring(nameStr.length()-2);
    		}
    	}
    	return names;
    }
    public static void main(String[] args) {
    	IPSeeker ip = IPSeeker.getInstance();
    	String ipaddress = "119.122.255.31";
		System.out.println(ip.getAddress(ipaddress)  + " : " +ip.getCountry(ipaddress) +" : " +ip.getArea(ipaddress));
		String nameStr ="广东广州";
		String names[]=new String[2];
    	if(nameStr.contains("省")){
    		names[0]=nameStr.substring(0,nameStr.indexOf("省"));
    		if(nameStr.contains("市")){
    			names[1]=nameStr.substring(nameStr.indexOf("省")+1,nameStr.indexOf("市"));
    		}else{
    			names[1]=nameStr.substring(nameStr.length()-2);
    		}
    	}else{
    		names[0]=nameStr.substring(0,2);
    		
    		if(nameStr.contains("市")){
    			if(nameStr.contains("区")){
    				names[1]=nameStr.substring(0,nameStr.indexOf("市"));
    			}else{
    			names[1]=nameStr.substring(nameStr.length()-3,nameStr.indexOf("市"));
    			}
    		}else{
    			names[1]=nameStr.substring(nameStr.length()-2);
    		}
    	}
    	System.out.println(names[0]+"  "+names[1]);
	}
}

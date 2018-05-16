package com.ymei_inc.ymadvert.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import com.ymei_inc.ymadvert.App;

public class StreamStringUtil {
	
	final static int BUFFER_SIZE = 4096;
	public static String InputStreamTOString(InputStream in) throws Exception {

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
			outStream.write(data, 0, count);

		data = null;
		return new String(outStream.toByteArray(), App.ENCODING);
	}
}

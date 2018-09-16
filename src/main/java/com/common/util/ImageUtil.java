package com.common.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import java.io.*;
/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
public class ImageUtil {

//	//base64字符串转byte[]
//    public static byte[] base64String2ByteFun(String base64Str){
//        return Base64.decodeBase64(base64Str);
//    }
//    //byte[]转base64
//    public static String byte2Base64StringFun(byte[] b){
//        return Base64.encodeBase64String(b);
//    }
	
	// 图片到byte数组
	public static byte[] image2byte(String path) {
		byte[] data = null;
		FileImageInputStream input = null;
		try {
			input = new FileImageInputStream(new File(path));
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int numBytesRead = 0;
			while ((numBytesRead = input.read(buf)) != -1) {
				output.write(buf, 0, numBytesRead);
			}
			data = output.toByteArray();
			output.close();
			input.close();
		} catch (FileNotFoundException ex1) {
			ex1.printStackTrace();
		} catch (IOException ex1) {
			ex1.printStackTrace();
		}
		return data;
	}

	// byte数组到图片
	public static void byte2image(byte[] data, String path) {
		if (data.length < 3 || "".equals(path)) {
			return;
		}
		try {
			FileImageOutputStream imageOutput = new FileImageOutputStream(
					new File(path));
			imageOutput.write(data, 0, data.length);
			imageOutput.close();
			System.out.println("Make Picture success,Please find image in "
					+ path);
		} catch (Exception ex) {
			System.out.println("Exception: " + ex);
			ex.printStackTrace();
		}
	}


	public static String imageToBase64(String place) {
		InputStream in = null;
		byte[] data = null;
		try {
			in = new FileInputStream(place);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);
	}

	/** 一般插件返回的base64编码的字符串都是有一个前缀的。 */
	public static boolean base64ToImage(String base6sStr, String place,
			String name) {
		try {
			if (base6sStr == null) {
				return false;
			}
			if (place == null) {
				return false;
			}
			if (name == null) {
				return false;
			}
			String[] strArr = base6sStr.split(",");
			if (strArr == null || strArr.length < 1) {
				return false;
			}

			// "data:image/jpeg;base64," 解码之前这个得去掉。
			String str = strArr[1];
			if (str == null || "".equals(str.trim())) {
				return false;
			}
			File file = new File(place);
			if (!file.exists() && !file.isDirectory()) {
				file.mkdir();
			}
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] b = decoder.decodeBuffer(str);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			OutputStream out = new FileOutputStream(place + name);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/** java_base64转图片 */
	public static boolean base64ToImageByJAVA(String base6sStr, String place,
			String name) {
		try {
			if (base6sStr == null) {
				return false;
			}
			if (place == null) {
				return false;
			}
			if (name == null) {
				return false;
			}
			String str = base6sStr;
			if (str == null || "".equals(str.trim())) {
				return false;
			}
			File file = new File(place);
			if (!file.exists() && !file.isDirectory()) {
				file.mkdir();
			}
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] b = decoder.decodeBuffer(str);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			OutputStream out = new FileOutputStream(place + name);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}

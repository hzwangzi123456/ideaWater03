package com.common.util;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
public class IpUtil {

	private static final Logger logger = Logger.getLogger(IpUtil.class);

	public static String getRealIPAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		String localIP = "127.0.0.1";
		if ((ip == null) || (ip.length() == 0)
				|| (ip.equalsIgnoreCase(localIP))
				|| "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0)
				|| (ip.equalsIgnoreCase(localIP))
				|| "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0)
				|| (ip.equalsIgnoreCase(localIP))
				|| "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		String[] ipAddrArr = ip.split(",");
		for (String ipAddr : ipAddrArr) {
			if (null != ipAddr && !"unknown".equals(ipAddr)) {
				ip = ipAddr;
				break;
			}
		}
		return ip;
	}

	/**
	 * 获得本机IP
	 * 
	 * @return
	 */
	public static String getLocalIP() {
		InetAddress inetAddress = null;
		try {
			inetAddress = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			logger.error(e);
			return null;
		}
		byte[] ipByteArray = inetAddress.getAddress();
		if (null == ipByteArray) {
			return null;
		}
		StringBuilder ipAdderBuilder = new StringBuilder();
		for (int i = 0; i < ipByteArray.length; i++) {
			if (i > 0) {
				ipAdderBuilder.append(".");
			}
			ipAdderBuilder.append(ipByteArray[i] & 0xFF);
		}
		return ipAdderBuilder.toString();
	}

	public static void main(String[] args) {
		String ip = "1.85.199.146, 119.97.140.80 0";
		String regex = "((2[0-4] \\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";
		String[] ipAddrArr = ip.split(",");
		for (String ipAddr : ipAddrArr) {
			System.out.println(ipAddr + "_" + ipAddr.matches(regex));
			if (null != ipAddr && !"unknown".equals(ipAddr)
					&& ipAddr.matches(regex)) {
				ip = ipAddr;
				break;
			}
		}
		System.out.println(ip);
	}

}

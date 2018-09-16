package com.common.util;

import java.util.UUID;
/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
public class Uuid {
	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}
	
	public static void main(String[] args) {
		System.out.println(get32UUID());
	}
}

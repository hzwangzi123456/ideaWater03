package com.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Map;
/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
public class JSONUtil { 
	
	/**
	 * Object或Array转JSON字符串
	 * @return
	 */
	public static String Object2JsonString(Object object){
		return JSON.toJSONString(object).toString();
	}
	
	/**
	 * MAP转JSON字符串
	 * @return
	 */
	public static String Map2JsonString(Map map){
		return JSON.toJSONString(map,SerializerFeature.SortField);
	}
	
	
}

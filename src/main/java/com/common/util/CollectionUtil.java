package com.common.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;
/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
@SuppressWarnings("rawtypes")
public class CollectionUtil {

	public static boolean isEmpty(Collection collection) {
		return (null == collection || collection.isEmpty());
	}

	public static boolean isEmpty(Map map) {
		return (null == map || map.isEmpty());
	}

	public static boolean isNotEmpty(Collection collection) {
		return !isEmpty(collection);
	}

	public static boolean isNotEmpty(Map map) {
		return !isEmpty(map);
	}

	public static boolean isEmpty(Object[] objArray) {
		return (null == objArray || objArray.length == 0);
	}

	public static boolean isNotEmpty(Object[] objArray) {
		return !isEmpty(objArray);
	}

	/**
	 * 向集合中添加不为空的字符串
	 * 
	 * @param str
	 * @param list
	 */
	public static void addNotEmptyStr(String str, List<String> list) {
		if (StringUtil.isNullStr(str)) {
			return;
		}
		list.add(str);
	}

	/**
	 * 向集合中添加不为空的值
	 * 
	 * @param obj
	 * @param list
	 */
	public static void addNotEmptyVal(Object obj, List<Object> list) {
		if (null == obj) {
			return;
		}
		list.add(obj);
	}

	/**
	 * 判断字符窜是否在集合中
	 * 
	 * @param str
	 * @param list
	 * @return
	 */
	public static boolean strInList(String str, List<String> list) {
		if (CollectionUtil.isEmpty(list)) {
			return false;
		}
		if (null == str) {
			return false;
		}
		for (String listStr : list) {
			if (str.equals(listStr)) {
				return true;
			}
		}
		return false;
	}
}

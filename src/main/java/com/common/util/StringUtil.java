package com.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author zy.wu 2013-5-27 下午4:01:48
 */
public class StringUtil {

    private static Pattern pattern = Pattern.compile("[-]?\\d+[.]?\\d*");
    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return (null == str || "".equals(str) || str.trim().length() < 1);
    }

    /**
     * 判断字符是否为空
     *
     * @param str 需要判断的字符串
     * @return boolean true:空;false:不为空
     * @add by Nick Yau
     */
    public static boolean isNullStr(String str) {
        return (null == str || "".equals(str) || str.length() < 1);
    }

    /**
     * 是否非空
     *
     * @param str
     * @return
     */
    public static boolean isNotNullStr(String str) {
        return !isNullStr(str);
    }

    /**
     * 编码
     *
     * @param str 待编码字符串
     * @param enc 编码格式
     * @return
     */
    public static String encode(String str, String enc) {
        if (str == null) {
            return null;
        }
        try {
            return URLEncoder.encode(str, enc);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        }
    }

    /**
     * 解码
     *
     * @param str 待解码字符串
     * @param enc 解码格式
     * @return
     */
    public static String decode(String str, String enc) {
        if (str == null) {
            return null;
        }
        try {
            return URLDecoder.decode(str, enc);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        }
    }

    /**
     * 去掉字符串前置空格
     *
     * @param in 字符串
     * @return String 字符串
     */
    public static String ltrim(String in) {
        if (in == null) {
            return null;
        }
        while (" ".equals(in.substring(0, 1))) {
            in = in.substring(1, in.length());
        }
        return in;
    }

    /**
     * 去掉字符串后置空格
     *
     * @param in 字符串
     * @return String 字符串
     */
    public static String rtrim(String in) {
        if (in == null) {
            return null;
        }
        while (" ".equals(in.substring(in.length() - 1, in.length()))) {
            in = in.substring(0, in.length() - 1);
        }
        return in;
    }

    /**
     * 去掉字符串前后置空格
     *
     * @param in 字符串
     * @return String 字符串
     */
    public static String trim(String in) {
        if (in == null) {
            return null;
        }
        while (" ".equals(in.substring(in.length() - 1, in.length()))) {
            in = in.substring(0, in.length() - 1);
        }
        while (" ".equals(in.substring(0, 1))) {
            in = in.substring(1, in.length());
        }

        return in;
    }

    /**
     * 去掉字符串数组前后置空格
     *
     * @param in 字符串数组
     * @return String 字符串数组
     */
    public static String[] trim(String[] arr) {
        if (arr == null || arr.length < 1) {
            return null;
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = trim(arr[i]);
        }
        return arr;
    }

    /**
     * 一维数组组装成带分隔符的字符串
     *
     * @param array 一维数组
     * @return String 字符串
     * @add by Nick Yau
     */
    public static String array2String(String[] array, String prefix) {

        StringBuilder builder = new StringBuilder();

        for (Object str : array) {
            builder.append(str).append(prefix);
        }

        return builder.toString().substring(0, builder.toString().length() - 1);
    }

    /**
     * 将字符串转换成大写
     *
     * @param str
     * @return
     */
    public static String greatString(String str) {
        String upStr = str.toUpperCase();
        StringBuffer buf = new StringBuffer(str.length());
        for (int i = 0; i < str.length(); i++) {
            buf.append(upStr.charAt(i));
        }
        return buf.toString();
    }

    /**
     * 将字符串转换成小写 @param str 字符串
     *
     * @param str
     * @return
     */
    public static String smallString(String str) {
        String upLower = str.toLowerCase();
        StringBuffer buf = new StringBuffer(str.length());
        for (int i = 0; i < str.length(); i++) {
            buf.append(upLower.charAt(i));
        }
        return buf.toString();
    }

    /**
     * 字符串大小写互转
     *
     * @param str
     * @return
     */
    public static String convertString(String str) {
        String upStr = str.toUpperCase();
        String lowStr = str.toLowerCase();
        StringBuffer buf = new StringBuffer(str.length());
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == upStr.charAt(i)) {
                buf.append(lowStr.charAt(i));
            } else {
                buf.append(upStr.charAt(i));
            }
        }
        return buf.toString();
    }

    /**
     * 判断字符串是否全为数字
     *
     * @param str 需要比较的字符串
     * @return boolean true:是数字;false:不是数字
     * @add by Nick Yau
     */
    public static boolean isNumeric(String str) {
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 防止特殊字符(‘)sql注入
     *
     * @param sqlParam
     * @return
     */
    public static String formatBaseQueryParam(String sqlParam) {
        return sqlParam.replace("'", "''");
    }

    /**
     * Like 查询条件sql参数特殊字符(',[,%,_,^)转译
     *
     * @param sqlParam
     * @return
     */
    public static String formatLikeQueryParam(String sqlParam) {
        return sqlParam.replace("'", "''").replace("[", "[[]").replace("%",
                "[%]").replace("_", "[_]").replace("^", "[^]");
    }

    /**
     * strA ASCII码比strB大
     * @param strA A字符串
     * @param strB B字符串
     * @return true:成立,false:失败
     */
    public static boolean strAIsBiggerThanstrB(String strA,String strB) {
        if(strA.compareTo(strB)>0) {
            return true;
        }
        return false;
    }

    /**
     * strA ASCII码比strB小
     * @param strA A字符串
     * @param strB B字符串
     * @return true:成立,false:失败
     */
    public static boolean strAIsSmallerThanstrB(String strA,String strB) {
        if(strA.compareTo(strB) < 0) {
            return true;
        }
        return false;
    }
}

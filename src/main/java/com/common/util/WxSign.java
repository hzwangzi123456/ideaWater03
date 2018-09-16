package com.common.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;

/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
public class WxSign {
    private static String characterEncoding = "UTF-8";

    @SuppressWarnings("rawtypes")
    public static String createSign(SortedMap<Object, Object> parameters, String key) {
        StringBuffer sb = new StringBuffer();

        //所有参与传参的参数按照accsii排序（升序）
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();

            //如果参数的值为空不参与签名；
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + key);
        String sign = MD5.MD5Encode(sb.toString()).toUpperCase();
        return sign;
    }

    public static String getNonceStr() {
        Random random = new Random();
        return MD5.MD5Encode(String.valueOf(random.nextInt(10000)));
    }

    public static String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

}

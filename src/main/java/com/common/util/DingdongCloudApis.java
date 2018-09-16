package com.common.util;
 
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;

 
/**
 * 基于http client 4.2.3叮咚云相关接口
 * @author liangyang
 * @date 2015年5月5日 下午11:47:45
 * @version 1.0
 */
public class DingdongCloudApis {
    // 查账户信息的http地址
    private static String URL_GET_USER_INFO = "https://api.dingdongcloud.com/v1/sms/userinfo";
    // 查询账户余额的http地址
    private static String URL_GET_BALANCE = "https://api.dingdongcloud.com/v1/sms/querybalance";
    // 验证码短信发送接口的http地址
    private static String URL_SEND_YZM = "https://api.dingdongcloud.com/v1/sms/sendyzm";
    private static String URL_SEND_YYYZM = "https://api.dingdongcloud.com/v1/sms/sendyyyzm";
    // 通知短信发送接口的http地址
    private static String URL_SEND_TZ = "https://api.dingdongcloud.com/v1/sms/sendtz";
    // 营销短信发送接口的http地址
    private static String URL_SEND_YX = "https://api.dingdongcloud.com/v1/sms/sendyx";
    // 模板获取
    private static String URL_GET_TPL = "https://api.dingdongcloud.com/v1/template/get";
    // 模板添加
    private static String URL_ADD_TPL = "https://api.dingdongcloud.com/v1/template/add";
    // 获取营销短信审核状态
    private static String URL_VERIFY_STATUS_YX = "https://api.dingdongcloud.com/v1/verify/add";
 
    // 编码格式。发送编码格式统一用UTF-8
    private static String ENCODING = "UTF-8";
 
    public static void main(String[] args) throws IOException, URISyntaxException {
    	 
        // 修改为您的apikey. apikey可在官网（https://www.dingdongcloud.com)登录后获取
        String apikey = "26e03c11f966880185cf0fab12016019";
        // 修改为您要发送的手机号
        String mobile = "18806509701";
        /**************** 查账户信息调用示例 *****************/
        // System.out.println(DingdongCloudApis.getUserInfo(apikey));
        /**************** 查账户余额调用示例 *****************/
        // System.out.println(DingdongCloudApis.getUserInfo(apikey));
        /**************** 发送验证码短信 *****************/
        // 设置您要发送的内容(内容必须和某个模板匹配。以下例子匹配的是系统提供的1号模板）
//        String yzmContent = "【叮咚云】您的验证码是：1234";
        // 发验证码短信调用示例
//        System.out.println(DingdongCloudApis.sendYzm(apikey, mobile, yzmContent));
        /**************** 发送语音验证码短信 *****************/
        // 必须纯数字4-6位
//        String yyContent = "1234";
        // 发短信调用示例
//        System.out.println(DingdongCloudApis.sendYyYzm(apikey, mobile, yyContent));
        /**************** 发送通知短信 *****************/
        // 设置您要发送的内容
//        InsectDensityLimit obj = InsectDensityLimitDao.FindAll();
//        String tzContent = "【竹林环境监测平台】当前虫口密度:" + obj.getValue() + ",虫口密度过高,请及时处理!退订回T";
 
        // 发短信调用示例
//        System.out.println(DingdongCloudApis.sendTz(apikey, mobile, tzContent));
        /**************** 获取营销短信审核状态 *****************/
//        String yxserno = "**********";
        // 获取审核状态调用示例
//        System.out.println(DingdongCloudApis.getYxStatus(apikey, yxserno));
        /**************** 添加模板 *****************/
//        String tplContent = "当前虫口密度:"+ "123" +",虫口密度过高,请及时处理!退订回T";
//        // type=1 添加通知模板，type=3 添加验证码模板
//        // 添加模板调用示例
//        System.out.println(DingdongCloudApis.addTpl(apikey, tplContent,type));
        /**************** 获取模板 *****************/
        // type=1 添加通知模板，type=3 添加验证码模板
//        String tpl_id = "13487";
//        String type = "1";
//        // 获取模板调用示例
//        System.out.println(DingdongCloudApis.getTpl(apikey, tpl_id,type));
        /**************** 发送营销短信 *****************/
        // 设置您要发送的内容，短信末尾必须带有“退订回T”
//        int temp = 1010;
//        String yxContent = "【竹林环境监测平台】当前虫口密度:" + temp + ",虫口密度过高,请及时处理!退订回T";
//        System.out.println(DingdongCloudApis.sendYx(apikey, mobile, yxContent));
    }
 
    /**
     * 查询账户信息接口
     * 
     * @param apikey
     * @return
     */
    public static String getUserInfo(String apikey) {
 
        NameValuePair[] data = { new NameValuePair("apikey", apikey) };
        return doPost(URL_GET_USER_INFO, data);
    }
 
    /**
     * 查询账户余额接口
     * @param apikey
     * @return
     */
    public static String getBalance(String apikey) {
 
        NameValuePair[] data = { new NameValuePair("apikey", apikey) };
        return doPost(URL_GET_BALANCE, data);
    }
 
    /**
     * 发送验证码短信
     * @param apikey
     *            apikey
     * @param mobile
     *            手机号码(唯一，不许多个)
     * @param content
     *            短信发送内容（必须经过utf-8格式编码)
     * @return json格式字符串
     */
    public static String sendYzm(String apikey, String mobile, String content) {
 
        if (StringUtils.isNotBlank(content)) {
            try {
                content = URLEncoder.encode(content, ENCODING);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
 
        NameValuePair[] data = { new NameValuePair("apikey", apikey),
 
        new NameValuePair("mobile", mobile),
 
        new NameValuePair("content", content) };
 
        return doPost(URL_SEND_YZM, data);
    }
 
    /**
     * 发送语音验证码
     * @param apikey
     * @param mobile
     *            手机号码(唯一，不许多个)
     * @param content
     *            短信发送内容(必须纯数字4-6位)
     * @return
     */
    public static String sendYyYzm(String apikey, String mobile, String content) {
        NameValuePair[] data = { new NameValuePair("apikey", apikey),
        new NameValuePair("mobile", mobile),
        new NameValuePair("content", content) };
        return doPost(URL_SEND_YYYZM, data);
    }
    /**
     * 发送通知短信
     * @param apikey
     *            apikey
     * @param mobile
     *            手机号码（多个号码用英文半角逗号分开，最多可提交1000个）
     * @param content
     *            短信发送内容（必须经过utf-8格式编码)
     * @return json格式字符串
     */
    public static String sendTz(String apikey, String mobile, String content) {
 
        if (StringUtils.isNotBlank(content)) {
            try {
                content = URLEncoder.encode(content, ENCODING);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
 
        NameValuePair[] data = { new NameValuePair("apikey", apikey),
 
        new NameValuePair("mobile", mobile),
 
        new NameValuePair("content", content) };
 
        return doPost(URL_SEND_TZ, data);
    }
 
    /**
     * 发送营销短信
     * @param apikey
     *            apikey
     * @param mobile
     *            手机号码（多个号码用英文半角逗号分开，最多可提交1000个）
     * @param content
     *            短信发送内容（必须经过utf-8格式编码，短信末尾必须带有“退订回T”）
     * @return json格式字符串
     */
    public static String sendYx(String apikey, String mobile, String content) {
 
        if (StringUtils.isNotBlank(content)) {
            try {
                content = URLEncoder.encode(content, ENCODING);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
 
        NameValuePair[] data = { new NameValuePair("apikey", apikey),
 
        new NameValuePair("mobile", mobile),
 
        new NameValuePair("content", content) };
 
        return doPost(URL_SEND_YX, data);
    }
 
    /**
     * 获取营销短信审核状态
     * @param apikey
     * @param ser_no
     *              短信批次号
     * @return
     */
     public static String getYxStatus(String apikey, String ser_no){
        NameValuePair[] data = {new NameValuePair("apikey", apikey),
                new NameValuePair("ser_no",ser_no),};
        return doPost(URL_VERIFY_STATUS_YX, data);
    }
 
    /**
     * 获取模板
     * @param apikey
     * @param tpl_id  
     *              模板ID
     * @param type
     *              模板类型
     * @return
     */
    public static String getTpl(String apikey,String tpl_id, String type){
        NameValuePair[] data = {new NameValuePair("apikey", apikey),
                new NameValuePair("tpl_id",tpl_id),
                new NameValuePair("type",type)};
        return doPost(URL_GET_TPL, data);
    }
 
    /**
     * 模板添加
     * @param apikey
     * @param tplContent
     *              模板内容
     * @param type
     *              模板类型
     * @return
     */
    public static String addTpl(String apikey,String tplContent,String type){
 
        NameValuePair[] data = {new NameValuePair("apikey", apikey),
                new NameValuePair("content",tplContent),
                new NameValuePair("type",type)};
        return doPost(URL_ADD_TPL, data);
    }
 
    /**
     * 基于HttpClient的post函数
     * 
     * @param url
     *            提交的URL
     * 
     * @param data
     *            提交NameValuePair参数
     * @return 提交响应
     */
    private static String doPost(String url, NameValuePair[] data) {
 
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(url);
        // method.setRequestHeader("ContentType",
        // "application/x-www-form-urlencoded;charset=UTF-8");
        method.setRequestBody(data);
       // client.getParams().setContentCharset("UTF-8");
        client.getParams().setConnectionManagerTimeout(10000);
        try {
            client.executeMethod(method);
            return method.getResponseBodyAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
package com.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
public class JsUtil {
	public static final String JQUERY_AJAX_HEADER_NAME = "x-requested-with";

	public static final String JQUERY_AJAX_HEADER_VALUE = "XMLHttpRequest";

	public static final String JS_TYPE_VALUE = "application/x-javascript";

	/**
	 * 是否为jquery ajax异步请求验证
	 * 
	 * @return
	 */
	public static boolean validateJqueryAjax(HttpServletRequest request) {
		if (null == request) {
			return false;
		}
		return JsUtil.JQUERY_AJAX_HEADER_VALUE.equals(request
				.getHeader(JsUtil.JQUERY_AJAX_HEADER_NAME));
	}

	/**
	 * 将js脚本作为response数据响应
	 * 
	 * @param response
	 * @param jsScriptStr
	 * @return
	 */
	public static boolean printJsScript(HttpServletResponse response,
			String jsScriptStr) {
		if (null == response || StringUtil.isNullStr(jsScriptStr)) {
			return false;
		}
		// 设置响应数据类型为JS脚本
		response.setContentType(JsUtil.JS_TYPE_VALUE);
		PrintWriter printWriter;
		try {
			printWriter = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		printWriter.print(jsScriptStr);
		return true;
	}
}

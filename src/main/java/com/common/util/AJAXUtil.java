package com.common.util;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.common.system.SysRuntimeException;

/**
 *  @author wangzi
 */
public class AJAXUtil {
	
	/**
	 * ������ajax瀛�绗�涓�
	 * @param response
	 * @param msg
	 */
	public static void setAjaxMsg(HttpServletResponse response,String msg){
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=null;
		try {
			out = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.print(msg);
		out.flush();   
		out.close();
	}
	
	/**
	 * ������ajax瀵硅薄  ��� ��扮��
	 * @param response
	 * @param object
	 */
	public static void setAjaxObject(HttpServletResponse response,Object object){ 
		String msg = JSONUtil.Object2JsonString(object); 
		setAjaxMsg(response,msg);
	}
	
	/**
	 * ������ajax琛ㄦ�兼�版��
	 * @param response
	 * @param total
	 * @param listData
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void setAjaxGridData(HttpServletResponse response,PageUtil pageUtil,List listData){
		String msg="{\"isLogined\":true,\"isHaveRight\":true,\"total\":0,\"rows\":[]}";		
		if(listData!=null && listData.size()>0){
			Map map=new HashMap();
			map.put("isLogined", true);
			map.put("isHaveRight", true);
			map.put("total", pageUtil.getTotal());
			map.put("rows", listData);
			msg=JSON.toJSONString(map,SerializerFeature.SortField);
		}
		setAjaxMsg(response,msg);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void setAjaxTreeGridData(HttpServletResponse response,PageUtil pageUtil,List listData){
		String msg="{\"isLogined\":true,\"isHaveRight\":true,\"total\":0,\"rows\":[]}";				
		if(listData!=null && listData.size()>0){
			Map map=new HashMap();
			map.put("isLogined", true);
			map.put("isHaveRight", true);
			map.put("total", pageUtil.getTotal());
			map.put("rows", listData);
			msg=JSON.toJSONString(map,SerializerFeature.SortField);
		}
		/**
		 * 瀹�涔�treegrid蹇�椤荤����惰����瑰��娈碉��榛�璁ゅ����х��涓�瀹�涔�涓衡��treeParentId���锛�ajax杩����json��版����堕��璁ゅ�ㄩ�ㄦ�挎��涓衡��_parentId���
		 */
		msg = msg.replaceAll("treeParentId", "_parentId");
		setAjaxMsg(response,msg);
	}
	
	/**
	 * 
	 * @param response
	 * @param total
	 * @param listData
	 * @param footData
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void setAjaxGridFooter(HttpServletResponse response,PageUtil pageUtil,List listData,List footData){
		String msg="{\"isLogined\":true,\"isHaveRight\":true,\"total\":0,\"rows\":[]}";			
		if(listData!=null && listData.size()>0){
			Map map=new HashMap();
			map.put("isLogined", true);
			map.put("isHaveRight", true);
			map.put("total", pageUtil.getTotal());
			map.put("rows", listData);
			map.put("footer", footData);
			msg=JSON.toJSONString(map,SerializerFeature.SortField);
		}
		setAjaxMsg(response,msg);
	}
	
	
	/**
	 * ������ajax浜や��������淇℃��
	 * @param msg
	 */
	public static void setSuccess(HttpServletResponse response,String msg){
		ResultUtil resultUtil = new ResultUtil(true, true, true, msg==null?"":msg);
		setAjaxObject(response, resultUtil);
	}
	
	
	/**
	 * ������ajax浜や��澶辫触淇℃��  
	 * @param msg
	 */
	public static void setFailure(HttpServletResponse response,String msg){
		ResultUtil resultUtil = new ResultUtil(true, true, false, msg==null?"":msg.replace(SysRuntimeException.class.getName()+":", ""));
		setAjaxObject(response, resultUtil); 
	} 
	
	/**
	 * ������ajax浜や��寮�甯镐俊��� 
	 * @param msg
	 */
	public static void setError(HttpServletResponse response){
		ResultUtil resultUtil = new ResultUtil(true, true, false, "涓������颁氦浜���版��寮�甯革��");
		setAjaxObject(response, resultUtil);  
	}
}

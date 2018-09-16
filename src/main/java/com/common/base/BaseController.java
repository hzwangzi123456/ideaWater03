package com.common.base;

import com.common.util.AJAXUtil;
import com.common.util.PageUtil;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
public class BaseController {
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    protected PageUtil pageUtil;

    //请求参数到命令对象的绑定
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response, PageUtil pageUtil) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
        this.pageUtil = pageUtil;
//        this.response.setHeader("Access-Control-Allow-Origin", "*"); 
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public Map getSchMap() {
        Map schMap = new HashMap();
        schMap.put("pageStart", this.pageUtil.getPageStart());
        schMap.put("pageEnd", this.pageUtil.getPageEnd());
        schMap.put("page", this.pageUtil.getPage());
        schMap.put("sortName", this.pageUtil.getSort());
        schMap.put("orderName", this.pageUtil.getOrder());
        schMap.put("rows", this.pageUtil.getRows());
        return schMap;
    }


    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    public PageUtil getPageUtil() {
        return pageUtil;
    }

    /*********************AJAX辅助***************************/
    /**
     * 发送ajax字符串信息
     *
     * @param msg
     */
    public void setAjaxMsg(String msg) {
        AJAXUtil.setAjaxMsg(response, msg);
    }

    /**
     * 发送ajax对象 或 数组 信息
     *
     * @param object
     */
    public void setAjaxObject(Object object) {
        AJAXUtil.setAjaxObject(response, object);
    }

    /**
     * 发送ajax表格结果集
     *
     * @param listData
     */
    public void setAjaxGridData(List listData) {
        AJAXUtil.setAjaxGridData(response, this.pageUtil, listData);
    }

    public void setAjaxTreeGridData(List listData) {
        AJAXUtil.setAjaxTreeGridData(response, this.pageUtil, listData);
    }

    /**
     * 发送ajax带汇总信息表格结果集
     *
     * @param listData
     * @param footList
     */
    public void setAjaxGridFooter(List listData, List footList) {
        AJAXUtil.setAjaxGridFooter(response, this.pageUtil, listData, footList);
    }

    /**
     * 发送ajax交互成功信息
     *
     * @param msg
     */
    public void setSuccess(String msg) {
        AJAXUtil.setSuccess(response, msg);
    }

    /**
     * 发送ajax交互失败信息
     *
     * @param msg
     */
    public void setFailure(String msg) {
        AJAXUtil.setFailure(response, msg);
    }

    /**
     * 发送ajax交互异常信息
     */
    public void setError() {
        AJAXUtil.setError(response);
    }

}

package com.yasi.web;

import com.alibaba.fastjson.JSON;
import com.common.base.BaseController;
import com.yasi.bo.AuthorityAreasBo;
import com.yasi.vo.AuthorityAreas;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

/**
 * @author wangzi
 */
@Controller
@Scope("prototype")
@RequestMapping("AuthorityAreasController/")
public class AuthorityAreasController extends BaseController {
    private static Logger logger = Logger
            .getLogger(AreasInstrumentsController.class);

    /**
     * 注入业务接口层
     **/
    @Autowired
    private AuthorityAreasBo authorityAreasBo;

    /**
     * 定义参数
     **/
    private AuthorityAreas authorityAreas;

    public AuthorityAreasBo getAuthorityAreasBo() {
        return authorityAreasBo;
    }

    @ModelAttribute
    public void setParaVal(AuthorityAreas authorityAreas) {
        this.authorityAreas = authorityAreas;
        System.out.println(JSON.toJSONString(authorityAreas).toString());
    }

    @RequestMapping("findAuthorityAreasByPojo.do")
    public void findAuthorityAreasByPojo() {
        ArrayList<AuthorityAreas> arr = (ArrayList<AuthorityAreas>) authorityAreasBo.findAuthorityAreasByPojo(authorityAreas);
        if (arr == null || arr.size() == 0) {
            setFailure("未找到数据");
            return;
        }
        setAjaxObject(arr);
    }

    @RequestMapping("findAuthorityAreasProvinceByPojo.do")
    public void findAuthorityAreasProvinceByPojo() {
        ArrayList<String> arr = (ArrayList<String>) authorityAreasBo.findAuthorityAreasProvinceByPojo(authorityAreas);
        if (arr == null || arr.size() == 0) {
            setFailure("未找到数据");
            return;
        }
        setAjaxObject(arr);
    }
}

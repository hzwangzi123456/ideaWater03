package com.yasi.web;

import com.alibaba.fastjson.JSON;
import com.common.base.BaseController;
import com.yasi.bo.MengxinTableBo;
import com.yasi.vo.MengxinTable;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wangzi
 * @date 18/8/25 下午9:09.
 */
@Controller
@Scope("prototype")
@RequestMapping("MengxinTableController/")
public class MengxinTableController extends BaseController {
    private static Logger logger = Logger
            .getLogger(MengxinTableController.class);

    /**
     * 注入业务接口层
     **/
    @Autowired
    private MengxinTableBo mengxinTableBo;

    /**
     * 定义参数
     **/
    private MengxinTable mengxinTable;

    @ModelAttribute
    public void setParaVal(MengxinTable mengxinTable) {
        this.mengxinTable = mengxinTable;
        System.out.println(JSON.toJSONString(mengxinTable).toString());
    }

    @RequestMapping("insert.do")
    public void insert() {
        if(mengxinTableBo.insert(mengxinTable) > 0) {
            setSuccess("插入成功");
        }
        setFailure("插入失败");
    }
}

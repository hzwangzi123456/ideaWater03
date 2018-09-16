package com.yasi.web;

import com.alibaba.fastjson.JSON;
import com.common.base.BaseController;
import com.yasi.bo.AreasInstrumentsBo;
import com.yasi.vo.AreasInstruments;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author wangzi
 */
@Controller
@Scope("prototype")
@RequestMapping("AreasInstrumentsController/")
public class AreasInstrumentsController extends BaseController {
    private static Logger logger = Logger
            .getLogger(AreasInstrumentsController.class);

    /**
     * 注入业务接口层
     **/
    @Autowired
    private AreasInstrumentsBo areasInstrumentsBo;

    /**
     * 定义参数
     **/
    private AreasInstruments areasInstruments;

    public AreasInstrumentsBo getAreasInstrumentsBo() {
        return areasInstrumentsBo;
    }

    @ModelAttribute
    public void setParaVal(AreasInstruments areasInstruments) {
        this.areasInstruments = areasInstruments;
        System.out.println(JSON.toJSONString(areasInstruments).toString());
    }

    @RequestMapping("findAreasInstrumentsByPojo.do")
    public void findAreasInstrumentsByPojo() {
        List<AreasInstruments> resultArr = areasInstrumentsBo
                .findAreasInstrumentsByPojo(areasInstruments);
        setAjaxObject(resultArr);

    }
}

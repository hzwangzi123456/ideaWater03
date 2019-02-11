package com.yasi.web;

import com.alibaba.fastjson.JSON;
import com.common.base.BaseController;
import com.yasi.bo.AreasInstrumentsBo;
import com.yasi.dto.InstrumentsResDto;
import com.yasi.vo.AreasInstruments;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangzi
 */
@RestController
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

    /**
     * 地区设备查询接口
     */
    @RequestMapping("findAreasInstrumentsByPojo.do")
    public void findAreasInstrumentsByPojo() {
        List<AreasInstruments> resultArr = areasInstrumentsBo
                .findAreasInstrumentsByPojo(areasInstruments);
        setAjaxObject(resultArr);

    }

    /**
     * 查询所有设备接口
     */
    @RequestMapping("getInstruments.do")
    public Map getInstruments() {
        HashMap<String, Object> map = new HashMap<>();
        try {
            InstrumentsResDto resDto = areasInstrumentsBo.getInstruments();
            map = resDto.dto2map();
        } catch (Exception e) {
            map.put("result", "1");
            map.put("msg", "内部错误");
            map.put("list", null);
            logger.error("getInstruments[]调用服务层出错:" + e.getMessage());
        }
        return map;
    }
}

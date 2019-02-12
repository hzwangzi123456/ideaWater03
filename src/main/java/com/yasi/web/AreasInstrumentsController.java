package com.yasi.web;

import com.alibaba.fastjson.JSON;
import com.common.base.BaseController;
import com.yasi.bean.InstrumentLocation;
import com.yasi.bo.AreasInstrumentsBo;
import com.yasi.dto.InstrumentsResDto;
import com.yasi.enums.InstrumentLocationType;
import com.yasi.vo.AreasInstruments;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

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
    public Object getInstruments(String callback) {
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
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(map);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
    }

    /**
     * 查询设备详细信息
     */
    @RequestMapping("getInstrumentLocation.do")
    public Object getInstrumentLocation(String callback) {
        //获取设备id
        String instrumentId = request.getParameter("instrumentId");

        HashMap<String, Object> map = new HashMap<>();
        if (StringUtils.isEmpty(instrumentId)) {
            map.put("result", 1);
            map.put("msg", "设备id为空");
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(map);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }

        InstrumentLocationType[] values = InstrumentLocationType.values();
        InstrumentLocation bean = new InstrumentLocation();

        for (InstrumentLocationType i : values) {
            if (i.getInstrumentId().equals(instrumentId)) {
                bean.setId(i.getId());
                bean.setInstrumentId(i.getInstrumentId());
                bean.setCity(i.getCity());
                bean.setCountry(i.getCountry());
                bean.setArea(i.getArea());
                HashMap<String, Object> map1 = (HashMap<String, Object>) bean.bean2Map();
                map.put("result", 0);
                map.put("msg", "查询成功");
                map.putAll(map1);
                MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(map);
                mappingJacksonValue.setJsonpFunction(callback);
                return mappingJacksonValue;
            }
        }

        //未找到设备
        map.put("result", 1);
        map.put("msg", "未找到该设备");
        logger.error("getInstrumentLocation[]设备id:" + instrumentId);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(map);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
    }
}

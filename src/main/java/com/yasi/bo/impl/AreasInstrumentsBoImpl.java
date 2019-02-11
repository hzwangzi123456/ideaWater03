package com.yasi.bo.impl;

import com.common.system.SysRuntimeException;
import com.yasi.bean.Instruments;
import com.yasi.bo.AreasInstrumentsBo;
import com.yasi.dao.AreasInstrumentsDao;
import com.yasi.dto.InstrumentsResDto;
import com.yasi.enums.InstrumentType;
import com.yasi.vo.AreasInstruments;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzi
 */
@Service
public class AreasInstrumentsBoImpl implements AreasInstrumentsBo {

    private static Logger logger = Logger
            .getLogger(AreasInstrumentsBoImpl.class);

    @Autowired
    private AreasInstrumentsDao areasInstrumentsDao;

    @Override
    public List<AreasInstruments> findAreasInstrumentsByPojo(AreasInstruments vo)
            throws SysRuntimeException {
        return areasInstrumentsDao.findAreasInstrumentsByPojo(vo);
    }


    @Override
    public InstrumentsResDto getInstruments() {
        InstrumentsResDto resDto = new InstrumentsResDto();

        //查询枚举类
        List<Instruments> list = enum2list();

        resDto.setResult(0);
        resDto.setMsg("查询成功");
        resDto.setInstrumentsList(list);

        return resDto;
    }

    /**
     * 将枚举类转换成List
     *
     * @return
     */
    private List<Instruments> enum2list() {
        List<Instruments> list = new ArrayList<Instruments>();
        InstrumentType[] values = InstrumentType.values();
        for (InstrumentType v : values) {
            Instruments model = new Instruments();
            model.setId(v.getId());
            model.setInstrumentId(v.getInstrumentId());
            model.setArea(v.getArea());
            model.setX(v.getX());
            model.setY(v.getY());
            list.add(model);
        }
        return list;
    }
}

package com.yasi.service;

import com.yasi.dto.NineSkyDataGetDto;
import com.yasi.dto.NineSkyDataGetResDto;
import com.yasi.dto.NineSkyResData;
import com.yasi.model.NineSkyData;

/**
 * @author wangzi
 * @date 18/12/18 下午4:13.
 */
public interface NineSkyDataService {
    /**
     * 插入久天数据
     * @param module
     * @return
     */
    NineSkyResData insert(NineSkyData module);

    /**
     * 根据日期和设备id查询久天数据
     * @param dto
     * @return
     */
    NineSkyDataGetResDto findByTime(NineSkyDataGetDto dto);
}

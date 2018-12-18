package com.yasi.service;

import com.yasi.model.NineSkyData;
import com.yasi.vo.NineSkyResData;

/**
 * @author wangzi
 * @date 18/12/18 下午4:13.
 */
public interface NineSkyDataService {
    /**
     * 插入久天数据
     * @param vo 数据
     * @return
     */
    NineSkyResData insert(NineSkyData module);
}

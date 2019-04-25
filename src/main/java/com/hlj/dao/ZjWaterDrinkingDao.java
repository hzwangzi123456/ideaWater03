package com.hlj.dao;

import com.hlj.bean.ZjWaterDrinking;

import java.util.List;

/**
 * @author wangzi
 * @date 19/4/22 下午9:49.
 * 持久层
 */
public interface ZjWaterDrinkingDao {
    /**
     * 插入饮用水数据
     * @param model 实体类
     * @return
     */
    int add(ZjWaterDrinking model);

    /**
     * 插入饮用水数据list
     * @param list 实体list
     * @return
     */
    int insertForeach(List<ZjWaterDrinking> list);
}

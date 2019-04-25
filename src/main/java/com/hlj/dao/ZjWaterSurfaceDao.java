package com.hlj.dao;

import com.hlj.bean.ZjWaterSurface;

import java.util.List;

/**
 * @author wangzi
 * @date 19/4/13 上午11:34.
 * 持久层
 */
public interface ZjWaterSurfaceDao {
    /**
     * 插入地表水数据
     * @param model 实体类
     * @return
     */
    int addWaterSurface(ZjWaterSurface model);

    /**
     * 插入地表水数据list
     * @param list 实体list
     * @return
     */
    int insertForeach(List<ZjWaterSurface> list);
}

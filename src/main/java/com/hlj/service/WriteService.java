package com.hlj.service;

/**
 * @author wangzi
 * @date 19/4/10 上午9:27.
 */
public interface WriteService {
    /**
     * 插入当天地表水数据
     * @return
     */
    Boolean addWaterSurface(String sql) throws Exception;

    /**
     * 插入当天饮用水数据
     * @return
     * @throws Exception
     */
    Boolean addWaterDrinking(String sql) throws Exception;
}

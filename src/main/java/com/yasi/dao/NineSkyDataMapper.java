package com.yasi.dao;

import com.yasi.model.NineSkyData;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author wangzi
 * @date 18/12/18 下午3:18.
 */
public interface NineSkyDataMapper extends GenericMapper<NineSkyData> {
    @Override
    Integer insert(NineSkyData vo);

    @Override
    NineSkyData findByKey(NineSkyData vo);

    @Override
    Integer findCountByVo(NineSkyData vo);

    @Override
    List<NineSkyData> findByPojo(NineSkyData vo);

    @Override
    List<NineSkyData> findByPage(Map<String, Object> map);

    @Override
    List<NineSkyData> findByWhere(Map<String, Object> map);

    @Override
    List<NineSkyData> findByQueryStr(String queryStr);

    @Override
    Integer update(NineSkyData vo);
}

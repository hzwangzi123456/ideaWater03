package com.jt.dao.mybatis;

import com.jt.entity.GranaryDataDO;

/**
 * @Author wangzi
 * @Date 2020-06-23 16:04
 */
public interface GranaryDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GranaryDataDO record);

    int insertSelective(GranaryDataDO record);

    GranaryDataDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GranaryDataDO record);

    int updateByPrimaryKey(GranaryDataDO record);
}
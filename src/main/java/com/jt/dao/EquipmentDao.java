package com.jt.dao;

import com.jt.entity.EquipmentDO;
import com.jt.query.EquipmentQuery;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface EquipmentDao {
    int countByExample(EquipmentQuery example);

    int deleteByExample(EquipmentQuery example);

    int deleteByPrimaryKey(Integer id);

    int insert(EquipmentDO record);

    int insertSelective(EquipmentDO record);

    List<EquipmentDO> selectByExample(EquipmentQuery example);

    EquipmentDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EquipmentDO record, @Param("example") EquipmentQuery example);

    int updateByExample(@Param("record") EquipmentDO record, @Param("example") EquipmentQuery example);

    int updateByPrimaryKeySelective(EquipmentDO record);

    int updateByPrimaryKey(EquipmentDO record);
}
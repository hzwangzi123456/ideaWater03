package com.jt.dao.mybatis;
import org.apache.ibatis.annotations.Param;
import java.util.List;


import com.jt.entity.EquipmentDO;

public interface EquipmentDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EquipmentDO record);

    int insertSelective(EquipmentDO record);

    EquipmentDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EquipmentDO record);

    int updateByPrimaryKey(EquipmentDO record);

    List<EquipmentDO> findByStatusAndIsDeleteAndEquipmentId(@Param("status")Integer status,@Param("isDelete")Integer isDelete,@Param("equipmentId")Long equipmentId);

    List<EquipmentDO> findByStatusAndIsDeleteAndEquipmentIdAndEquipmentType(@Param("status")Integer status,@Param("isDelete")Integer isDelete,@Param("equipmentId")Long equipmentId,@Param("equipmentType")Integer equipmentType);


}
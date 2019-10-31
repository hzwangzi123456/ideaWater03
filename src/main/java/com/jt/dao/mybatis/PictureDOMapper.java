package com.jt.dao.mybatis;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.jt.entity.PictureDO;

public interface PictureDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PictureDO record);

    int insertSelective(PictureDO record);

    PictureDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PictureDO record);

    int updateByPrimaryKey(PictureDO record);

    List<PictureDO> findByStatusAndIsDeleteAndEquipmentType(@Param("status")Integer status,
                                                            @Param("isDelete")Integer isDelete,
                                                            @Param("equipmentType")Integer equipmentType);

    List<PictureDO> findByStatusAndIsDelete(@Param("status")Integer status,
                                            @Param("isDelete")Integer isDelete);


}
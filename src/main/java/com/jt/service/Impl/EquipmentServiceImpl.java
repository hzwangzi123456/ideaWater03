package com.jt.service.Impl;

import com.common.util.CollectionUtil;
import com.constant.EquipmentTypeEnum;
import com.constant.IsDeleteEnum;
import com.constant.StatusEnum;
import com.jt.dao.mybatis.EquipmentDOMapper;
import com.jt.entity.EquipmentDO;
import com.jt.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentDOMapper equipmentDaoMapper;


    @Override
    public EquipmentDO getByEquIdandType ( long equipId , Integer equipmentType ) {

        List < EquipmentDO > equipmentDOS = equipmentDaoMapper.findByStatusAndIsDeleteAndEquipmentIdAndEquipmentType (
                StatusEnum.VALID.getCode () ,
                IsDeleteEnum.NO.getCode () ,
                equipId ,
                equipmentType );

        if ( CollectionUtil.isEmpty ( equipmentDOS ) ) {
            return null;
        } else {
            return equipmentDOS.get ( 0 );
        }
    }
}

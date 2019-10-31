package com.jt.service;

import com.constant.EquipmentTypeEnum;
import com.jt.entity.EquipmentDO;

public interface EquipmentService {

    /**
     * 根据id查询
     * @return
     */
    EquipmentDO getByEquIdandType ( long equipId , Integer integer );
}

package com.jt.service;

import com.jt.entity.EquipmentDO;

public interface EquipmentService {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    EquipmentDO getByid(long id);
}

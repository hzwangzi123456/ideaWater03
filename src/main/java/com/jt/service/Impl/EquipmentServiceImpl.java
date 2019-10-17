package com.jt.service.Impl;

import com.common.util.CollectionUtil;
import com.jt.dao.EquipmentDao;
import com.jt.entity.EquipmentDO;
import com.jt.query.EquipmentQuery;
import com.jt.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentDao equipmentDao;

    @Override
    public EquipmentDO getByid(long id) {
        EquipmentQuery equipmentQuery = new EquipmentQuery();
        EquipmentQuery.Criteria criteria = equipmentQuery.createCriteria();
        criteria.andEquipmentIdEqualTo((long)id);

        List<EquipmentDO> equipmentDOS = equipmentDao.selectByExample(equipmentQuery);
        if (CollectionUtil.isEmpty(equipmentDOS)) {
            return null;
        } else {
            return equipmentDOS.get(0);
        }
    }
}

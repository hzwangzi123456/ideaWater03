package com.yasi.bo.impl;

import com.yasi.bo.EmployeeBo;
import com.yasi.dao.EmployeeDao;
import com.yasi.po.Employee;
import com.yasi.vo.EmployeeRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangzi
 * @date 18/1/3 下午5:05.
 */
@Service
public class EmployeeBoImpl implements EmployeeBo {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public List<Employee> findEmployeeByPojo(Employee po) {
        return employeeDao.findEmployeeByPojo(po);
    }

    @Override
    public List<EmployeeRecord> findEmployeeRecordByVo(EmployeeRecord vo) {
        return employeeDao.findEmployeeRecordByVo(vo);
    }
}

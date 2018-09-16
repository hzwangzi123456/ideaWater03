package com.yasi.bo;

import com.yasi.po.Employee;
import com.yasi.vo.EmployeeRecord;

import java.util.List;

/**
 * @author wangzi
 * @date 18/1/3 下午5:04.
 */
public interface EmployeeBo {
    public List<Employee> findEmployeeByPojo(Employee po);
    public List<EmployeeRecord> findEmployeeRecordByVo(EmployeeRecord vo);

}

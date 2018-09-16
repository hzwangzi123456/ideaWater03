package com.yasi.dao;

import com.yasi.po.Employee;
import com.yasi.vo.EmployeeRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangzi
 * @date 18/1/3 下午3:43.
 */
@Repository
public interface EmployeeDao {
    public List<Employee> findEmployeeByPojo(Employee po);
    public List<EmployeeRecord> findEmployeeRecordByVo(EmployeeRecord vo);
}

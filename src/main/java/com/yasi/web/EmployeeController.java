package com.yasi.web;

import com.common.base.BaseController;
import com.yasi.bo.EmployeeBo;
import com.yasi.vo.EmployeeRecord;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author wangzi
 * @date 18/1/8 下午5:19.
 */
@Controller
@Scope("prototype")
@RequestMapping("EmployeeController/")
public class EmployeeController extends BaseController {
    private static Logger logger = Logger
            .getLogger(EmployeeController.class);

    /**
     * 注入业务接口层
     **/
    @Autowired
    private EmployeeBo employeeBo;

    private EmployeeRecord employeeRecord;

    @ModelAttribute
    public void setParaVal(EmployeeRecord employeeRecord) {
        this.employeeRecord = employeeRecord;
        System.out.println(employeeRecord);
    }

//    @RequestMapping("findEmployeeRecordByVo.do")
//    public void findEmployeeRecordByVo() {
//        List<EmployeeRecord> list = null;
//        list = employeeBo.findEmployeeRecordByVo(employeeRecord);
//        if (list != null) {
//            setAjaxObject(list);
//            System.out.println(list);
//        }else {
//            setFailure("未找到数据");
//        }
//    }

    @RequestMapping("findEmployeeRecordByVo.do")
    public @ResponseBody List<EmployeeRecord> findEmployeeRecordByVo() {
        List<EmployeeRecord> list = null;
        list = employeeBo.findEmployeeRecordByVo(employeeRecord);
        if (list != null) {
            return list;
        }
        return null;
    }
}

package com.yasi.web;

import com.common.base.BaseController;
import com.yasi.model.AttendanceRecord02;
import com.yasi.service.AttendanceRecord02Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/**
 * @author wangzi
 * @date 18/9/25 下午4:00.
 */
@RestController
@RequestMapping("/AttendanceRecord02Controller")
public class AttendanceRecord02Controller extends BaseController {

    @Autowired
    private AttendanceRecord02Service attendanceRecord02Service;

    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(AttendanceRecord02 attendanceRecord02) throws ParseException {
        attendanceRecord02Service.create(attendanceRecord02);
    }

    @RequestMapping(value = "/load", method = RequestMethod.POST)
    public AttendanceRecord02 load(long id) throws Exception {
        AttendanceRecord02 load = attendanceRecord02Service.load(id);
        return load;
    }
}

package com.yasi.service;

import com.yasi.dto.AttendanceRecord02Dto;
import com.yasi.model.AttendanceRecord02;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @author wangzi
 * @date 18/9/25 下午3:54.
 */
public interface AttendanceRecord02Service {
    void create(AttendanceRecord02 attendanceRecord02) throws ParseException;

    AttendanceRecord02 load(long id) throws Exception;

    List<AttendanceRecord02> loadByCriteria(Map<String,Object> criteria) throws Exception;
}

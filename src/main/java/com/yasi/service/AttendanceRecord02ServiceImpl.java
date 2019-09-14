package com.yasi.service;

import com.common.util.DateUtil;
import com.common.util.StringUtil;
import com.yasi.dao.AttendanceRecord02Dao;
import com.yasi.model.AttendanceRecord02;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wangzi
 * @date 18/9/25 下午3:55.
 */
@Service
public class AttendanceRecord02ServiceImpl implements AttendanceRecord02Service {

    @Autowired
    private AttendanceRecord02Dao attendanceRecord02Dao;

    @Override
    public void create(AttendanceRecord02 attendanceRecord02) throws ParseException {
        if(StringUtil.isNotNullStr(attendanceRecord02.getStartTime()) && StringUtil.isNotNullStr(attendanceRecord02.getEndTime())) {
            long startLong = DateUtil.date2TimeStamp(attendanceRecord02.getStartTime());
            long endLong = DateUtil.date2TimeStamp(attendanceRecord02.getEndTime());
            double hour = (endLong - startLong) * 1.0 / DateUtil.SecondsPerHour;
            attendanceRecord02.setHours(hour);
        }
        attendanceRecord02Dao.create(attendanceRecord02);
    }

    @Override
    public AttendanceRecord02 load(long id) throws Exception {
        return attendanceRecord02Dao.load(id);
    }

    @Override
    public List<AttendanceRecord02> loadByCriteria(Map<String,Object> criteria) throws Exception {
        List<AttendanceRecord02> list = new ArrayList<>();
        
        return list;
    }
}

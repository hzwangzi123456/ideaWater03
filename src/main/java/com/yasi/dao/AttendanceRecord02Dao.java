package com.yasi.dao;

import com.yasi.model.AttendanceRecord02;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AttendanceRecord02Dao {
    void create(AttendanceRecord02 attendanceRecord02);

    AttendanceRecord02 load(long id);

    List<AttendanceRecord02> loadByCriteria(Map<String, Object> criteria);
}
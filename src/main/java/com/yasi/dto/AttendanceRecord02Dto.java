package com.yasi.dto;

import com.yasi.model.AttendanceRecord02;

import java.io.Serializable;

/**
 * @author wangzi
 * @date 18/9/26 下午1:13.
 */
public class AttendanceRecord02Dto  implements Serializable {
    /**
     * 查询信息
     */
    private AttendanceRecord02 attendanceRecord02;

    /**
     * 查询开始时间
     */
    private String startSearchTime;

    /**
     * 查询结束时间
     */
    private String endSearchTime;

    public AttendanceRecord02Dto() {
    }

    public AttendanceRecord02Dto(AttendanceRecord02 attendanceRecord02) {
        this.attendanceRecord02 = attendanceRecord02;
    }

    public void setStartSearchTime(String startSearchTime) {
        this.startSearchTime = startSearchTime;
    }

    public void setEndSearchTime(String endSearchTime) {
        this.endSearchTime = endSearchTime;
    }

    public AttendanceRecord02 getAttendanceRecord02() {
        return attendanceRecord02;
    }

    public String getStartSearchTime() {
        return startSearchTime;
    }

    public String getEndSearchTime() {
        return endSearchTime;
    }
}

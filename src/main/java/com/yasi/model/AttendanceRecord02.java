package com.yasi.model;

import com.yasi.enums.RecordType;

public class AttendanceRecord02 {
    /**
     * 考勤记录id
     */
    private Integer id;

    /**
     * 河长工号
     */
    private Integer workNumber;

    /**
     * 考勤记录类型{@link RecordType}
     */
    private Integer type;

    /**
     * 上班打卡时间
     */
    private String startTime;

    /**
     * 下班打卡时间
     */
    private String endTime;

    /**
     * 上班时间小时数
     */
    private Double hours;

    /**
     * 上班打卡位置
     */
    private String startPosition;

    /**
     * 下班打卡位置
     */
    private String endPosition;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(Integer workNumber) {
        this.workNumber = workNumber;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    public String getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(String startPosition) {
        this.startPosition = startPosition == null ? null : startPosition.trim();
    }

    public String getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(String endPosition) {
        this.endPosition = endPosition == null ? null : endPosition.trim();
    }


    @Override
    public String toString() {
        return "AttendanceRecord02{" +
                "id=" + id +
                ", workNumber=" + workNumber +
                ", type=" + type +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", hours=" + hours +
                ", startPosition='" + startPosition + '\'' +
                ", endPosition='" + endPosition + '\'' +
                '}';
    }
}
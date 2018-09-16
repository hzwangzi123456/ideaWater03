package com.yasi.vo;

public class AttandenceRecord {
    private String workNumber;

    private String date;

    private String day;

    private String time;

    private Integer state;

    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber == null ? null : workNumber.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day == null ? null : day.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }


    @Override
    public String toString() {
        return "AttandenceRecord{" +
                "workNumber='" + workNumber + '\'' +
                ", date='" + date + '\'' +
                ", day='" + day + '\'' +
                ", time='" + time + '\'' +
                ", state=" + state +
                '}';
    }
}
package com.yasi.vo;

/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
public class YascmfTcplogs {
    private String date;
    private String log;

    //0表示非法数据，1表示合法数据
    private Integer state;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}

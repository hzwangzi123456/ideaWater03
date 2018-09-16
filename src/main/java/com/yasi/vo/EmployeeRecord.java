package com.yasi.vo;

import java.util.List;

/**
 * @author wangzi
 * @date 18/1/3 下午5:57.
 */
public class EmployeeRecord {
    /**
     * 河长姓名
     */
    private String name;

    /**
     * 河长账号
     */
    private String username;

    /**
     * 河长密码
     */
    private String password;

    /**
     * 河长工号
     */
    private String workNumber;

    /**
     * 河长所属部门
     */
    private String department;

    /**
     * 打卡记录
     */
    private List<AttandenceRecord> recordList;

    /**
     * 起始时间
     * 举例:2017-09-01 00:00:00
     */
    private String startTimeTIMESTAMP;

    /**
     * 起始时间
     * 举例:2017-09-01 00:00:00
     */
    private String endTimeTIMESTAMP;

    public EmployeeRecord() {
    }

    public String getStartTimeTIMESTAMP() {
        return startTimeTIMESTAMP;
    }

    public void setStartTimeTIMESTAMP(String startTimeTIMESTAMP) {
        this.startTimeTIMESTAMP = startTimeTIMESTAMP;
    }

    public String getEndTimeTIMESTAMP() {
        return endTimeTIMESTAMP;
    }

    public void setEndTimeTIMESTAMP(String endTimeTIMESTAMP) {
        this.endTimeTIMESTAMP = endTimeTIMESTAMP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<AttandenceRecord> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<AttandenceRecord> recordList) {
        this.recordList = recordList;
    }


    @Override
    public String toString() {
        return "EmployeeRecord{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", workNumber='" + workNumber + '\'' +
                ", department='" + department + '\'' +
                ", recordList=" + recordList +
                ", startTimeTIMESTAMP='" + startTimeTIMESTAMP + '\'' +
                ", endTimeTIMESTAMP='" + endTimeTIMESTAMP + '\'' +
                '}';
    }
}

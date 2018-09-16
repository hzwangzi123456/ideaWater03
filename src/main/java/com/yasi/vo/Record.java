package com.yasi.vo;
/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
public class Record {
    private String username;

    private String workHour;

    private String offHour;

    private Integer status;

    private String dateTime;
    
    private Integer recordDay;
    
    private String id;
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getRecordDay() {
		return recordDay;
	}

	public void setRecordDay(Integer recordDay) {
		this.recordDay = recordDay;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getWorkHour() {
        return workHour;
    }

    public void setWorkHour(String workHour) {
        this.workHour = workHour == null ? null : workHour.trim();
    }

    public String getOffHour() {
        return offHour;
    }

    public void setOffHour(String offHour) {
        this.offHour = offHour == null ? null : offHour.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime == null ? null : dateTime.trim();
    }
}
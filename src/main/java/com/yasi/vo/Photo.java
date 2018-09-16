package com.yasi.vo;

import java.util.Arrays;

/**
 * @author ziwang
 *
 */
public class Photo {
	private String id;
	
	private String name;

    private String instrumentId;

    private String dateTime;

	private byte[] byteArray;
    
    private String base64String;
    
    private String introduce;
    
    private String position;

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
    public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getBase64String() {
		return base64String;
	}

	public void setBase64String(String base64String) {
		this.base64String = base64String;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId == null ? null : instrumentId.trim();
    }

    public byte[] getByteArray() {
		return byteArray;
	}

	public void setByteArray(byte[] byteArray) {
		this.byteArray = byteArray;
	}

	public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

	@Override
	public String toString() {
		return "Photo{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", instrumentId='" + instrumentId + '\'' +
				", dateTime='" + dateTime + '\'' +
				", byteArray=" + Arrays.toString(byteArray) +
				", base64String='" + base64String + '\'' +
				", introduce='" + introduce + '\'' +
				", position='" + position + '\'' +
				'}';
	}
}
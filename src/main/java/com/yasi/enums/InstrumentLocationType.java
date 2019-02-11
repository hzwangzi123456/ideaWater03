package com.yasi.enums;

/**
 * @author wangzi
 * @date 19/2/11 下午4:40.
 * 设备详细地址类型
 */
public enum InstrumentLocationType {
    DONGHU(0, "D01", "杭州市", "临安区", "东湖"),
    XINAN(1, "X01", "湖州市", "德清县", "新安镇"),
    WUZHEN(2, "Z01", "嘉兴市", "桐乡市", "乌镇"),
    WENLING(3, "L01", "温岭市", "石桥头镇", "沙角村");

    /**
     * 序号
     */
    private int id;

    /**
     * 设备id
     */
    private String instrumentId;

    /**
     * 城市
     */
    private String city;

    /**
     * 城区
     */
    private String country;

    /**
     * 乡镇、村庄
     */
    private String area;

    InstrumentLocationType(int id, String instrumentId, String city, String country, String area) {
        this.id = id;
        this.instrumentId = instrumentId;
        this.city = city;
        this.country = country;
        this.area = area;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}

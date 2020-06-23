package com.jt.entity;

/**
 * @Author wangzi
 * @Date 2020-06-23 16:04
 */
public class GranaryDataDO {
    /**
    * id
    */
    private Integer id;

    /**
    * 设备id
    */
    private String instrumentId;

    /**
    * 硫化氢
    */
    private Integer hepaticGas;

    /**
    * 一氧化碳
    */
    private Integer coGas;

    /**
    * 二氧化碳
    */
    private Integer co2Gas;

    /**
    * 含氧量
    */
    private Integer oxygenContent;

    /**
    * 温度
    */
    private Integer temperature;

    /**
    * 湿度
    */
    private Integer humidity;

    /**
    * 电量
    */
    private Integer battery;

    /**
    * 状态
    */
    private Integer status;

    /**
    * 删除状态
    */
    private Integer isDelete;

    /**
    * 创建时间
    */
    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    public Integer getHepaticGas() {
        return hepaticGas;
    }

    public void setHepaticGas(Integer hepaticGas) {
        this.hepaticGas = hepaticGas;
    }

    public Integer getCoGas() {
        return coGas;
    }

    public void setCoGas(Integer coGas) {
        this.coGas = coGas;
    }

    public Integer getCo2Gas() {
        return co2Gas;
    }

    public void setCo2Gas(Integer co2Gas) {
        this.co2Gas = co2Gas;
    }

    public Integer getOxygenContent() {
        return oxygenContent;
    }

    public void setOxygenContent(Integer oxygenContent) {
        this.oxygenContent = oxygenContent;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getBattery() {
        return battery;
    }

    public void setBattery(Integer battery) {
        this.battery = battery;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
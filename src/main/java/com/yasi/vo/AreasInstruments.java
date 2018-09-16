package com.yasi.vo;

import java.util.Date;

/**
 * @author wangzi
 */
public class AreasInstruments {
    private String prefix;
    private String province;
    private String city;
    private String county;
    private String extra;
    private String createdAt;
    private String updatedAt;
    private Integer num;

    private String instrumentId;
    private String license;
    private String instrumentModel;
    private String manufacturer;
    private Date dateOfManufacture;
    private Date dateOfIntoUsed;
    private Integer dayOfUnusual;
    private String coordinate;


    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getInstrumentModel() {
        return instrumentModel;
    }

    public void setInstrumentModel(String instrumentModel) {
        this.instrumentModel = instrumentModel;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Date getDateOfManufacture() {
        return dateOfManufacture;
    }

    public void setDateOfManufacture(Date dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
    }

    public Date getDateOfIntoUsed() {
        return dateOfIntoUsed;
    }

    public void setDateOfIntoUsed(Date dateOfIntoUsed) {
        this.dateOfIntoUsed = dateOfIntoUsed;
    }

    public Integer getDayOfUnusual() {
        return dayOfUnusual;
    }

    public void setDayOfUnusual(Integer dayOfUnusual) {
        this.dayOfUnusual = dayOfUnusual;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }


}

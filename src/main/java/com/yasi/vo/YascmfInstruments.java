package com.yasi.vo;

import java.util.Date;
/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
public class YascmfInstruments {
    private String instrumentId;

    private String license;

    private String instrumentModel;

    private String manufacturer;

    private Date dateOfManufacture;

    private Date dateOfIntoUsed;

    private Integer dayOfUnusual;

    private String coordinate;

    public String getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId == null ? null : instrumentId.trim();
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license == null ? null : license.trim();
    }

    public String getInstrumentModel() {
        return instrumentModel;
    }

    public void setInstrumentModel(String instrumentModel) {
        this.instrumentModel = instrumentModel == null ? null : instrumentModel.trim();
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer == null ? null : manufacturer.trim();
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
        this.coordinate = coordinate == null ? null : coordinate.trim();
    }
}
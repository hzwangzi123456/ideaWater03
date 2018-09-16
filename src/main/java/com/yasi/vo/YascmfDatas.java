package com.yasi.vo;

/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
public class YascmfDatas {
    private Integer id;

    private Integer model;

    private String instrumentId;

    private String dateTime;

	private String coordinate;

    private Double ph;

    private Double conductivity;

    private Double waterTemperature;

    private Double ammoniaNitrogen;

    private Double dissolvedOxygen;

    private Double level;

    private Boolean exception;

    private Double ntu;

    private Double p;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public String getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId == null ? null : instrumentId.trim();
    }

    public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate == null ? null : coordinate.trim();
    }

    public Double getPh() {
        return ph;
    }

    public void setPh(Double ph) {
        this.ph = ph;
    }

    public Double getConductivity() {
        return conductivity;
    }

    public void setConductivity(Double conductivity) {
        this.conductivity = conductivity;
    }

    public Double getWaterTemperature() {
        return waterTemperature;
    }

    public void setWaterTemperature(Double waterTemperature) {
        this.waterTemperature = waterTemperature;
    }

    public Double getAmmoniaNitrogen() {
        return ammoniaNitrogen;
    }

    public void setAmmoniaNitrogen(Double ammoniaNitrogen) {
        this.ammoniaNitrogen = ammoniaNitrogen;
    }

    public Double getDissolvedOxygen() {
        return dissolvedOxygen;
    }

    public void setDissolvedOxygen(Double dissolvedOxygen) {
        this.dissolvedOxygen = dissolvedOxygen;
    }

    public Double getLevel() {
        return level;
    }

    public void setLevel(Double level) {
        this.level = level;
    }

    public Boolean getException() {
        return exception;
    }

    public void setException(Boolean exception) {
        this.exception = exception;
    }

    public Double getNtu() {
        return ntu;
    }

    public void setNtu(Double ntu) {
        this.ntu = ntu;
    }

    public Double getP() {
        return p;
    }

    public void setP(Double p) {
        this.p = p;
    }
}
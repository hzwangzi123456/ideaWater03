package com.yasi.bean;

import java.math.BigDecimal;

/**
 * 配送地址Bean
 */
public class GeoCoordBean {

    private String name;
    private BigDecimal xaxis;
    private BigDecimal yaxis;
    private BigDecimal value;

    public GeoCoordBean() {
    }

    public GeoCoordBean(String name, BigDecimal xaxis, BigDecimal yaxis, BigDecimal value) {
        this.name = name;
        this.xaxis = xaxis;
        this.yaxis = yaxis;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getXaxis() {
        return xaxis;
    }

    public void setXaxis(BigDecimal xaxis) {
        this.xaxis = xaxis;
    }

    public BigDecimal getYaxis() {
        return yaxis;
    }

    public void setYaxis(BigDecimal yaxis) {
        this.yaxis = yaxis;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}

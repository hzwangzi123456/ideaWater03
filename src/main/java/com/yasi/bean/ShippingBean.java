package com.yasi.bean;

import java.math.BigDecimal;

/**
 * 配送路线Bean
 */
public class ShippingBean {

    private String fromName;
    private String destName;
    private BigDecimal fromX;
    private BigDecimal fromY;
    private BigDecimal destX;
    private BigDecimal destY;
    private BigDecimal value;

    public ShippingBean() {
    }

    public ShippingBean(String fromName, String destName, BigDecimal value) {
        this.fromName = fromName;
        this.destName = destName;
        this.value = value;
    }

    public ShippingBean(String fromName, String destName, BigDecimal fromX, BigDecimal fromY, BigDecimal destX, BigDecimal destY) {
        this.fromName = fromName;
        this.destName = destName;
        this.fromX = fromX;
        this.fromY = fromY;
        this.destX = destX;
        this.destY = destY;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getDestName() {
        return destName;
    }

    public void setDestName(String destName) {
        this.destName = destName;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getFromX() {
        return fromX;
    }

    public void setFromX(BigDecimal fromX) {
        this.fromX = fromX;
    }

    public BigDecimal getFromY() {
        return fromY;
    }

    public void setFromY(BigDecimal fromY) {
        this.fromY = fromY;
    }

    public BigDecimal getDestX() {
        return destX;
    }

    public void setDestX(BigDecimal destX) {
        this.destX = destX;
    }

    public BigDecimal getDestY() {
        return destY;
    }

    public void setDestY(BigDecimal destY) {
        this.destY = destY;
    }
}

package com.yasi.vo;
/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
public class User {
    private Integer id;

    private String password;

    private String username;

    private Integer vip;

    private Integer hasPh;

    private Integer hasConductivity;

    private Integer hasWaterTemperature;

    private Integer hasAmmonianitrogen;

    private Integer hasDissolvedOxygen;

    private Integer hasNtu;

    private Integer hasP;

    public Integer getHasPh() {
        return hasPh;
    }

    public void setHasPh(Integer hasPh) {
        this.hasPh = hasPh;
    }

    public Integer getHasConductivity() {
        return hasConductivity;
    }

    public void setHasConductivity(Integer hasConductivity) {
        this.hasConductivity = hasConductivity;
    }

    public Integer getHasWaterTemperature() {
        return hasWaterTemperature;
    }

    public void setHasWaterTemperature(Integer hasWaterTemperature) {
        this.hasWaterTemperature = hasWaterTemperature;
    }

    public Integer getHasAmmonianitrogen() {
        return hasAmmonianitrogen;
    }

    public void setHasAmmonianitrogen(Integer hasAmmonianitrogen) {
        this.hasAmmonianitrogen = hasAmmonianitrogen;
    }

    public Integer getHasDissolvedOxygen() {
        return hasDissolvedOxygen;
    }

    public void setHasDissolvedOxygen(Integer hasDissolvedOxygen) {
        this.hasDissolvedOxygen = hasDissolvedOxygen;
    }

    public Integer getHasNtu() {
        return hasNtu;
    }

    public void setHasNtu(Integer hasNtu) {
        this.hasNtu = hasNtu;
    }

    public Integer getHasP() {
        return hasP;
    }

    public void setHasP(Integer hasP) {
        this.hasP = hasP;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getVip() {
        return vip;
    }

    public void setVip(Integer vip) {
        this.vip = vip;
    }
}
package com.yasi.enums;

/**
 * @author wangzi
 * @date 19/2/11 上午9:15.
 * 设备枚举类
 */
public enum InstrumentType {
    DONGHU(0,"D01","东湖","119.736695","30.261491"),//杭州市临安区东湖
    XINAN(1,"X01","新安","120.208892","30.569692"),//湖州市德清县新安镇
    WUZHEN(2,"Z01","乌镇","120.499794","30.749693"),//嘉兴市桐乡市乌镇
    WENLING(3,"L01","沙角村","121.468318","28.345012");//温岭市石桥头镇沙角村

    /**
     * 序号
     */
    private int id;

    /**
     * 设备id
     */
    private String instrumentId;

    /**
     * 区域
     */
    private String area;

    /**
     * 经度
     */
    private String x;

    /**
     * 维度
     */
    private String y;

    InstrumentType(int id, String instrumentId, String area, String x, String y) {
        this.id = id;
        this.instrumentId = instrumentId;
        this.area = area;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public String getInstrumentId() {
        return instrumentId;
    }

    public String getArea() {
        return area;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }
}

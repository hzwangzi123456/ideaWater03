package com.yasi.vo;

import lombok.Data;

/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
@Data
public class YascmfDatas {
    private Integer id;

    private Integer model;
    /**
     * 设备id
     */
    private String instrumentId;

    /**
     * 插入时间
     */
    private String dateTime;

    /**
     * 坐标
     */
	private String coordinate;

    /**
     * ph
     */
    private Double ph;

    /**
     * 导电性
     */
    private Double conductivity;

    /**
     * 水温
     */
    private Double waterTemperature;

    /**
     * 氨氮
     */
    private Double ammoniaNitrogen;

    /**
     * 溶解氧
     */
    private Double dissolvedOxygen;

    private Double level;

    private Boolean exception;

    /**
     * 浊度
     */
    private Double ntu;

    /**
     * 磷
     */
    private Double p;
}
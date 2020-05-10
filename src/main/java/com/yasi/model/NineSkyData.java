package com.yasi.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangzi
 * @date 18/12/17 下午7:31.
 */
@Data
public class NineSkyData implements Serializable {
    private static final long serialVersionUID = -7835343999882866451L;

    /**
     * id
     */
    private Integer id;

    /**
     * 导电性
     */
    private Integer conductivity;

    /**
     * 温度
     */
    private Integer temperature;

    /**
     * ph
     */
    private Integer ph;

    /**
     * 浊度
     */
    private Integer ntu;

    /**
     * 溶解氧
     */
    private Integer dissolvedOxygen;

    /**
     * 巴歇尔槽明渠流量流量
     */
    private Integer flow;

    /**
     * 电导率、温度传感器电源状态位
     */
    private Integer ctState;

    /**
     * ph传感器电源状态位
     */
    private Integer phState;

    /**
     * 浊度传感器电源状态位
     */
    private Integer ntuState;

    /**
     * 溶解氧传感器电源状态位
     */
    private Integer doState;

    /**
     * 流量传感器电源状态位
     */
    private Integer flState;

    /**
     * 时间 举例：2018-01-01 23:59:59
     */
    private String dateTime;

    /**
     * 设备id
     */
    private String instrumentId;

    /**
     * 位置
     */
    private String location;

    /**
     * 水位
     */
    private Integer waterLevel;

    /**
     * 氨氮
     */
    private Integer ammonia;
}

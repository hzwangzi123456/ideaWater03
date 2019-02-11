package com.yasi.bean;

import lombok.Data;

/**
 * @author wangzi
 * @date 19/2/11 上午10:18.
 * 设备bean
 */
@Data
public class Instruments {
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
}

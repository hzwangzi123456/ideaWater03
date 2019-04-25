package com.hlj.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangzi
 * @date 19/4/10 上午10:31.
 * 地表水
 */
@Data
public class ZjWaterSurface implements Serializable {
    private static final long serialVersionUID = -2392994091717058010L;

    /**
     * id
     */
    private int id;

    /**
     * 抓取时间 yyyy-MM-dd HH:mm:ss
     */
    private String grabTime;

    /**
     * 地名
     */
    private String mtName;

    /**
     * 监控时间
     */
    private String monitorTime;

    /**
     * ph
     */
    private String pH;

    /**
     * 溶解氧
     */
    private String DO;

    /**
     * 化学需氧量
     */
    private String CODmn;

    /**
     * TP
     */
    private String TP;

    /**
     * 氨氮
     */
    private String NH3N;
}

package com.hlj.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangzi
 * @date 19/4/22 下午9:46.
 * 饮用水数据实体类
 */
@Data
public class ZjWaterDrinking implements Serializable {
    private static final long serialVersionUID = 5124686285344710940L;

    /**
     * id 主键
     */
    private int id;

    /**
     * 抓取时间
     */
    private String grabTime;

    /**
     * 站点名称
     */
    private String stationname;

    /**
     * 监测时间
     */
    private String monitortime;

    /**
     * ph值
     */
    private String ph;

    /**
     * 溶解氧
     */
    private String Do;

    /**
     * CODmn
     */
    private String codmn;

    /**
     * TP
     */
    private String tp;

    /**
     * NH4
     */
    private String nh4;

}

package com.jt.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PictureDO {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 设备id
     */
    private String equipmentId;

    /**
     * 设备类型
     */
    private Integer equipmentType;

    /**
     * 老文件名
     */
    private String oldFileName;

    /**
     * 电压
     */
    private String voltage;

    /**
     * 温度
     */
    private String temp;

    /**
     * 湿度
     */
    private String humi;

    /**
     * 有效状态
     */
    private Integer status;

    /**
     * 删除状态
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    private Timestamp createTime;

}
package com.jt.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class EquipmentDO {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 设备id
     */
    private String equipmentId;

    /**
     * 设备类型1：粮仓，2：天牛
     */
    private Integer equipmentType;

    /**
     * 上传图片周期（秒）
     */
    private Long uploadPeriod;

    /**
     * 是否开启翻转，1：不开启，2：开启
     */
    private Integer inversionSwitch;

    /**
     * 翻转周期时间（秒），预留字段
     */
    private Long inversionPeriod;

    /**
     * 图片存储目录
     */
    private String picDir;

    /**
     * 有效状态
     */
    private Integer status;

    /**
     * 删除状态
     */
    private Integer isDelete;
}
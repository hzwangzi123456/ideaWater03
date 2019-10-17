package com.jt.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class EquipmentDO implements Serializable {
    private static final long serialVersionUID = 4262399328138567719L;

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 设备id
     */
    private Long equipmentId;

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
     * 状态1-显示 0-隐藏
     */
    private Integer status;

    /**
     * 是否逻辑删除, 1已经删除 0未删除
     */
    private Integer isDelete;
}

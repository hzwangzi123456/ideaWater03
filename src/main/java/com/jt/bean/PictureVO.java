package com.jt.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangzi
 * @date 19/5/11 上午11:43.
 */
@Data
public class PictureVO {

    /**
     * 图片url地址
     */
    private String url;

    /**
     * 设备id
     */
    private String equipmentId;

    /**
     * 设备类型
     */
    private Integer equipmentType;

    /**
     * 图片上传时间 举例:yyyy-MM-dd HH:mm:ss
     */
    private String date;

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
}

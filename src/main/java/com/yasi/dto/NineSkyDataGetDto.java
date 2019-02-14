package com.yasi.dto;

import lombok.Data;

/**
 * @author wangzi
 * @date 19/2/13 下午2:12.
 * 久天数据查询dto
 */
@Data
public class NineSkyDataGetDto {
    /**
     * 设备id
     */
    private String instrumentId;

    /**
     * 开始时间 yyyy-MM-dd HH:mm:ss 19位
     */
    private String start;

    /**
     * 结束时间 yyyy-MM-dd HH:mm:ss 19位
     */
    private String end;
}

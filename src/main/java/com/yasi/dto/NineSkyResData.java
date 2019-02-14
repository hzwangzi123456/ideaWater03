package com.yasi.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangzi
 * @date 18/12/18 上午11:11.
 */
@Data
public class NineSkyResData implements Serializable{
    private static final long serialVersionUID = -2108152333902033171L;

    /**
     * 返回参数 0：成功，1：失败
     */
    private int result;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 上传周期（秒）默认10分钟
     */
    private int uploadPeriod = 10 * 60;


    /**
     * 返回时间 举例：2018-01-01 23:59:59
     */
    private String resultTime;
}

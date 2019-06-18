package com.xs.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangzi
 * @date 19/5/29 下午4:00.
 */
@Data
public class ExcelRow implements Serializable {

    private static final long serialVersionUID = -3102721001382930901L;
    /**
     * 序号
     */
    private int index;

    /**
     * 真实值
     */
    private double trueValue;

    /**
     * 克里金预测值
     */
    private double kriValue;

    /**
     * IDW预测值
     */
    private double idwValue;

    /**
     * TR预测值
     */
    private double TRvalue;
}

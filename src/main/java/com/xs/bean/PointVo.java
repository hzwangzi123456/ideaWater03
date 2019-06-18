package com.xs.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangzi
 * @date 19/5/28 下午1:03.
 */
@Data
public class PointVo implements Serializable {
    private static final long serialVersionUID = 163579047773967462L;
    /**
     * x坐标
     */
    private double x;

    /**
     * y坐标
     */
    private double y;

    /**
     * 颜色值 16进制
     */
    private String value;
}

package com.yasi.dto;

import com.yasi.model.NineSkyData;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangzi
 * @date 19/2/13 下午2:19.
 * 久天数据查询返回dto
 */
@Data
public class NineSkyDataGetResDto implements Serializable {

    private static final long serialVersionUID = 7962964951584586318L;

    /**
     * 返回参数 0：成功，1：失败
     */
    private int result;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 数据集
     */
    private List<NineSkyData> list;
}

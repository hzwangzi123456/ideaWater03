package com.yasi.bean;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangzi
 * @date 19/2/11 下午4:48.
 * 设备详细地址
 */
@Data
public class InstrumentLocation {
    /**
     * 序号
     */
    private int id;

    /**
     * 设备id
     */
    private String instrumentId;

    /**
     * 省
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 城区
     */
    private String country;

    /**
     * 乡镇、村庄
     */
    private String area;

    /**
     * 将bean转为map
     *
     * @return
     */
    public Map bean2Map() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("instrumentId", instrumentId);
        map.put("province",province);
        map.put("city", city);
        map.put("country", country);
        map.put("area", area);
        return map;
    }
}

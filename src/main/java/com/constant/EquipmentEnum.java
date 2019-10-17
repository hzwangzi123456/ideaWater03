package com.constant;

/**
 * 设备枚举类
 */
public enum  EquipmentEnum {


    /**
     * 粮仓设备
     */
    LC(1),

    /**
     * 天牛设备
     */
    TN(2);

    private int code;

    EquipmentEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}

package com.constant;

public enum InversionEnum {

    /**
     * 不开启
     */
    NO(1),

    /**
     * 开启
     */
    YES(2);

    private int code;

    InversionEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}

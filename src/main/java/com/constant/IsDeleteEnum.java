package com.constant;

/**
 * 是否已经删除（0：否；1:是）
 * <p>
 * created by jiangyf on 2019-03-21 16:55
 */
public enum IsDeleteEnum {

    NO(0), YES(1);

    private int code;

    IsDeleteEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}

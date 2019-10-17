package com.constant;

import java.util.HashMap;
import java.util.Map;

public enum StatusEnum {

    /**
     * 统一状态枚举
     */
    INVALID(0, "失效"),
    VALID(1, "有效"),
    DELETED(-1, "已删除");

    private String message;
    private int code;
    private static Map<Integer, StatusEnum> map = new HashMap<>(values().length);

    StatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    static {
        for (StatusEnum e : values()) {
            map.put(e.getCode(), e);
        }
    }

    public static boolean isValid(Integer code) {
        return VALID.code == code;
    }

    public static boolean isInValid(Integer code) {
        return INVALID.code == code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public static Map<Integer, StatusEnum> getMap() {
        return map;
    }

    public static StatusEnum convert(Integer status) {
        return map.get(status);
    }

    public static boolean contains(Integer status) {
        return convert(status) != null;
    }
}

package com.yasi.enums;

/**
 * @author wangzi
 * @date 18/9/25 下午3:34.
 */
public enum RecordType {
    PUNCHCARD(1, "打卡"),
    ABSENT(2,"旷工"),
    LEAVE(3,"请假");

    private final int value;
    private final String typeStr;

    RecordType(int value, String typeStr) {
        this.value = value;
        this.typeStr = typeStr;
    }

    public int getValue() {
        return value;
    }


    @Override
    public String toString() {
        return typeStr;
    }
}

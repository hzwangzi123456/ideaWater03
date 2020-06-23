package com.hlj.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author wangzi
 * @Date 2020-06-19 11:48
 */
@Data
public class TestDto {

    private Integer id;

    private String name;

    private Timestamp createTime;

    @Override
    public String toString() {
        return "TestDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public TestDto(Integer id, String name, Timestamp createTime) {
        this.id = id;
        this.name = name;
        this.createTime = createTime;
    }

    public TestDto() {
    }
}

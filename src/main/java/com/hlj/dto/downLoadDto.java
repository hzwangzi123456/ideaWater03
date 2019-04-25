package com.hlj.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangzi
 * @date 19/4/23 上午7:22.
 * 下载dto
 */
@Data
public class DownLoadDto implements Serializable {

    private static final long serialVersionUID = -8088103459132702456L;

    /**
     * 下载url
     */
    private String url;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件存储的目录
     */
    private String downDir;

    /**
     * 表名
     */
    private String tableName;
}

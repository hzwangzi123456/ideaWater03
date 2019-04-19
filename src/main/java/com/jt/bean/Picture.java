package com.jt.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangzi
 * @date 19/4/11 上午10:53.
 * 照片实体类
 */
@Data
public class Picture implements Serializable {

    private static final long serialVersionUID = 2310326755273850224L;

    /**
     * 业务id
     *
     */
    private int id;

    /**
     * 原文件名
     */
    private String oldFilename;

    /**
     * 照片路径
     */
    private String filePath;

    /**
     * 上传id参数
     */
    private String picId;

    /**
     * 图片上传时间 举例:yyyy-MM-dd HH:mm:ss
     */
    private String date;
}

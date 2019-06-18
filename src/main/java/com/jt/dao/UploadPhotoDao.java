package com.jt.dao;

import com.jt.bean.Picture;

import java.util.List;

/**
 * @author wangzi
 * @date 19/4/11 上午11:05.
 *
 */
public interface UploadPhotoDao {
    /**
     * 插入数据库
     * @param model
     * @return
     */
    int addPhoto(Picture model);

    /**
     * 获取所有图片
     * @return
     */
    List<Picture> getPhoto();
}

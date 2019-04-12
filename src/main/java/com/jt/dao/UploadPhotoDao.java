package com.jt.dao;

import com.jt.bean.Picture;

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
}

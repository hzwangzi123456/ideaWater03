package com.jt.service;

import com.jt.bean.Picture;

/**
 * @author wangzi
 * @date 19/4/11 上午10:58.
 * 上传图片service层
 */
public interface UploadPhotoService {

    /**
     * 插入上传照片
     * @param model
     * @return
     */
    Boolean addPhoto(Picture model);
}

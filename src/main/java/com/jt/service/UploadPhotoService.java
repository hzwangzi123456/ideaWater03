package com.jt.service;

import com.constant.EquipmentTypeEnum;
import com.jt.bean.PictureVO;
import com.jt.entity.PictureDO;

import java.util.List;

/**
 * @author wangzi
 * @date 19/4/11 上午10:58.
 * 上传图片service层
 */
public interface UploadPhotoService {

    /**
     * 插入上传照片
     *
     * @param model
     * @return
     */
    Boolean addPhoto ( PictureDO model );

    /**
     * 得到所有照片
     *
     * @return
     */
    List < PictureVO > getPhoto ( EquipmentTypeEnum equipMentType);
}

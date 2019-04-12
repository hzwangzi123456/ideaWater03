package com.jt.service.Impl;

import com.jt.bean.Picture;
import com.jt.dao.UploadPhotoDao;
import com.jt.service.UploadPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangzi
 * @date 19/4/11 上午10:59.
 */
@Service
public class UploadPhotoServiceImpl implements UploadPhotoService {

    @Autowired
    private UploadPhotoDao dao;

    @Override
    public Boolean addPhoto(Picture model) {
        int i = dao.addPhoto(model);
        if (i == 1) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}

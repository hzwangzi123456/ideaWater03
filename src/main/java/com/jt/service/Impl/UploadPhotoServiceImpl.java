package com.jt.service.Impl;

import com.jt.bean.Picture;
import com.jt.bean.PictureVo;
import com.jt.dao.UploadPhotoDao;
import com.jt.service.UploadPhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzi
 * @date 19/4/11 上午10:59.
 */
@Service
@Slf4j
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

    @Override
    public List<PictureVo> getPhoto() {
        ArrayList<PictureVo> pictureVos = new ArrayList<>();
        List<Picture> lists = dao.getPhoto();
        if ( CollectionUtils.isEmpty(lists)) {
            log.error("图片集合为空");
            return null;
        }

        for (Picture p:lists) {
            PictureVo vo = new PictureVo();
            vo.setUrl("");
            vo.setDate("");
            vo.setVoltage("");
            vo.setTemp("");
            vo.setHumi("");

            String filePath = p.getFilePath();
            String[] split = filePath.split(File.separator);
            if (split.length != 0) {
                vo.setUrl("http://116.62.78.62:8060/pictures/" + split[split.length-1]);
                vo.setDate(p.getDate());
                vo.setHumi(p.getHumi());
                vo.setTemp(p.getTemp());
                vo.setVoltage(p.getVoltage());
                pictureVos.add(vo);
            }
        }
        return pictureVos;
    }
}

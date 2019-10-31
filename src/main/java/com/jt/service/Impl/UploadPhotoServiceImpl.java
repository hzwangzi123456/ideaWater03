package com.jt.service.Impl;

import com.common.util.BeanMapper;
import com.constant.IsDeleteEnum;
import com.constant.StatusEnum;
import com.jt.bean.PictureVO;
import com.jt.dao.mybatis.PictureDOMapper;
import com.jt.entity.PictureDO;
import com.jt.service.UploadPhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.testng.collections.Lists;

import java.util.List;

/**
 * @author wangzi
 * @date 19/4/11 上午10:59.
 */
@Service
@Slf4j
public class UploadPhotoServiceImpl implements UploadPhotoService {

    @Autowired
    private PictureDOMapper pictureDOMapper;

    @Override
    public Boolean addPhoto ( PictureDO model ) {
        model.setStatus ( StatusEnum.VALID.getCode () );
        model.setIsDelete ( IsDeleteEnum.NO.getCode () );
        int insert = pictureDOMapper.insert ( model );

        if ( insert == 1 ) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public List < PictureVO > getPhoto () {

        List < PictureDO > pictureDOS = pictureDOMapper.findByStatusAndIsDelete (
                StatusEnum.VALID.getCode () ,
                IsDeleteEnum.NO.getCode ()
        );

        if ( CollectionUtils.isEmpty ( pictureDOS ) ) {
            log.error ( "图片集合为空" );
            return null;
        }

        List < PictureVO > pictureVos = Lists.newArrayList ();
        for ( PictureDO p : pictureDOS ) {
            PictureVO vo = BeanMapper.map ( p , PictureVO.class );
            vo.setDate ( p.getCreateTime ().toString () );

            String filePath = p.getFilePath ();
            String[] split = filePath.split ( "\\" );
            if ( split.length != 0 ) {
                vo.setUrl ( "http://116.62.78.62:8060/pictures/" + split[ split.length - 1 ] );
                pictureVos.add ( vo );
            }
        }
        return pictureVos;
    }
}

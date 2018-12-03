package com.yasi.dao;

import com.yasi.vo.Photo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
@Repository
public interface PhotoDao {
    /**
     * insert Photo
     * @param vo Photo
     */
    int insertPhoto(Photo vo);

    /**
     * find Photo by pojo
     * @param vo Photo
     * @return
     */
    List<Photo> findPhotoByPojo(Photo vo);

    /**
     * find Photo by 自定义查询条件
     * @param map 查询条件
     * @return
     */
    List<Photo> findPhotoByWhere(Map<String,Objects> map);

    /**
     * delete Photo by key
     * @param vo Photo
     */
    void deletePhotoByKey(Photo vo);
}

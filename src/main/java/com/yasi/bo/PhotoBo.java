package com.yasi.bo;

import com.common.system.SysRuntimeException;
import com.yasi.vo.Photo;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
public interface PhotoBo {
    /**
     * insert Photo
     * @param vo Photo
     * @throws SysRuntimeException
     */
    boolean insertPhoto(Photo vo) throws SysRuntimeException;

    /**
     * find Photo by pojo
     * @param vo Photo
     * @return
     * @throws SysRuntimeException
     */
    List<Photo> findPhotoByPojo(Photo vo) throws SysRuntimeException;

    /**
     * find Photo by 自定义查询条件
     * @param map 查询条件
     * @return
     */
    List<Photo> findPhotoByWhere(Map<String,Objects> map) throws SysRuntimeException;

    /**
     * delete Photo by key
     * @param vo Photo
     * @throws SysRuntimeException
     */
    void deletePhotoByKey(Photo vo) throws SysRuntimeException;
}

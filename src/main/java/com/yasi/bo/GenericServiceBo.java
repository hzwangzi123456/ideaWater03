package com.yasi.bo;

import com.common.system.SysRuntimeException;

import java.util.List;
import java.util.Map;

/**
 * @author wangzi
 * @date 18/2/3 上午10:38.
 */
public interface GenericServiceBo<T> {
    Integer insert(T vo) throws SysRuntimeException;

    T findByKey(T vo) throws SysRuntimeException;

    Integer findCountByVo(T vo) throws SysRuntimeException;

    List<T> findByPojo(T vo) throws SysRuntimeException;

    List<T> findByPage(Map<String, Object> map) throws SysRuntimeException;

    List<T> findByWhere(Map<String, Object> map) throws SysRuntimeException;

    List<T> findByQueryStr(String queryStr) throws SysRuntimeException;

    Integer update(T vo) throws SysRuntimeException;
}

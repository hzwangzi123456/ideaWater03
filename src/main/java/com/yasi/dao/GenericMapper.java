package com.yasi.dao;

import java.util.List;
import java.util.Map;

/**
 * @author wangzi
 * @date 18/2/2 上午6:05.
 */
public interface GenericMapper<T> {
    Integer insert(T vo);

    T findByKey(T vo);

    Integer findCountByVo(T vo);

    List<T> findByPojo(T vo);

    List<T> findByPage(Map<String, Object> map);

    List<T> findByWhere(Map<String, Object> map);

    List<T> findByQueryStr(String queryStr);

    Integer update(T vo);
}

package com.yasi.dao;

import com.yasi.vo.YascmfTcplogs;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
@Repository
public interface YascmfTcplogsDao {
    /**
     * insert YascmfTcplogs
     *
     * @param vo YascmfTcplogs
     */
    void insertYascmfTcplogs(YascmfTcplogs vo);

    /**
     * find YascmfTcplogs by pojo
     *
     * @param vo YascmfTcplogs
     * @return YascmfTcplogs List
     */
    List<YascmfTcplogs> findYascmfTcplogsByPojo(YascmfTcplogs vo);
}

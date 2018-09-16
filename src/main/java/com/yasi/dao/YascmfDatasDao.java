package com.yasi.dao;

import com.yasi.vo.YascmfDatas;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
@Repository
public interface YascmfDatasDao {

    /**
     * find YascmfDatas by pojo
     *
     * @param vo YascmfDatas
     * @return YascmfDatas List
     */
    List<YascmfDatas> findYascmfDatasByPojo(YascmfDatas vo);

    /**
     * find YascmfDatas by Times
     *
     * @param instrumentId
     * @param start
     * @param end
     * @return YascmfDatas List
     */
    List<YascmfDatas> findYascmfDatasByTime(String instrumentId, String start, String end);

    /**
     * find YascmfDatasCounts by pojo
     *
     * @param vo YascmfDatas
     * @return Counts
     */
    int findYascmfDatasCountByPojo(YascmfDatas vo);

    /**
     * insert YascmfDatas
     *
     * @param vo YascmfDatas
     */
    void insertYascmfDatas(YascmfDatas vo);
}

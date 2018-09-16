package com.yasi.dao;

import com.yasi.vo.Record;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
@Repository
public interface RecordDao {
    /**
     * insert Record
     *
     * @param vo pojo
     */
    void insertRecord(Record vo);

    /**
     * find Record by Where
     * @param map 查询map条件
     * @return Record List
     */
    List<Record> findRecordByWhere(Map<String, Objects> map);

    /**
     * find Record by page
     *
     * @param map page map
     * @return Record List
     */
    List<Record> findRecordByPage(Map<String, Object> map);

    /**
     * find Record by pojo
     *
     * @param vo pojo
     * @return Record List
     */
    List<Record> findRecordByPojo(Record vo);

    /**
     * find Record Count by pojo
     *
     * @param vo pojo
     * @return Record Count
     */
    int findRecordCountByPojo(Record vo);

    /**
     * update Record by key
     *
     * @param vo pojo
     */
    void updateRecord(Record vo);
}

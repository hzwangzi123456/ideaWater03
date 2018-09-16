package com.yasi.bo;

import com.common.system.SysRuntimeException;
import com.yasi.vo.Record;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
public interface RecordBo {
    /**
     * insert Record
     * @param vo Record
     * @throws SysRuntimeException
     */
    void insertRecord(Record vo) throws SysRuntimeException;


    /**
     * find Record By map where
     * @param map map查询条件
     * @return record list
     * @throws SysRuntimeException
     */
    List<Record> findRecordByWhere(Map<String, Objects> map) throws SysRuntimeException;
    /**
     * find Record by page
     * @param map page
     * @return Record List
     * @throws SysRuntimeException
     */
    List<Record> findRecordByPage(Map<String, Object> map)
            throws SysRuntimeException;

    /**
     * find Record by pojo
     * @param vo pojo
     * @return Record List
     * @throws SysRuntimeException
     */
    List<Record> findRecordByPojo(Record vo) throws SysRuntimeException;

    /**
     * find Record Count by pojo
     * @param vo pojo
     * @return Record Count
     * @throws SysRuntimeException
     */
    int findRecordCountByPojo(Record vo) throws SysRuntimeException;

    /**
     * update Record by key
     * @param vo pojo
     * @throws SysRuntimeException
     */
    void updateRecord(Record vo) throws SysRuntimeException;
}

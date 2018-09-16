package com.yasi.dao;

import com.yasi.vo.AttandenceRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangzi
 * @date 17/12/30 上午1:54.
 */
@Repository
public interface AttandenceRecordDao {
    /**
     * pojo查询考勤
     *
     * @param attandenceRecord pojo
     * @return
     */
    public List<AttandenceRecord> findAttandenceRecordByPojo(AttandenceRecord attandenceRecord);

    /**
     * 插入pojo
     *
     * @param attandenceRecord pojo
     */
    public void insertAttandenceRecord(AttandenceRecord attandenceRecord);
}

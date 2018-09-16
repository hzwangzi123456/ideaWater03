package com.yasi.bo;

import com.common.system.SysRuntimeException;
import com.yasi.vo.AttandenceRecord;

import java.util.List;

/**
 * @author wangzi
 * @date 17/12/30 上午1:58.
 */
public interface AttandenceRecordBo {
    /**
     * pojo查询考勤
     *
     * @param attandenceRecord pojo
     * @return
     */
    public List<AttandenceRecord> findAttandenceRecordByPojo(AttandenceRecord attandenceRecord) throws SysRuntimeException;

    /**
     * 插入pojo
     *
     * @param attandenceRecord pojo
     */
    public void insertAttandenceRecord(AttandenceRecord attandenceRecord) throws SysRuntimeException;
}

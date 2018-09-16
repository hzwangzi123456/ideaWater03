package com.yasi.bo.impl;

import com.common.system.SysRuntimeException;
import com.yasi.bo.AttandenceRecordBo;
import com.yasi.dao.AttandenceRecordDao;
import com.yasi.vo.AttandenceRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangzi
 * @date 17/12/30 上午1:59.
 */
@Service
public class AttandenceRecordBoImpl implements AttandenceRecordBo {

    @Autowired
    private AttandenceRecordDao attandenceRecordDao;

    @Override
    public List<AttandenceRecord> findAttandenceRecordByPojo(AttandenceRecord attandenceRecord) throws SysRuntimeException {
        return attandenceRecordDao.findAttandenceRecordByPojo(attandenceRecord);
    }

    @Override
    public void insertAttandenceRecord(AttandenceRecord attandenceRecord) throws SysRuntimeException {
        attandenceRecordDao.insertAttandenceRecord(attandenceRecord);
    }
}

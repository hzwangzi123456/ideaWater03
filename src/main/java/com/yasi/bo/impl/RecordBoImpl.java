package com.yasi.bo.impl;

import com.common.system.SysRuntimeException;
import com.yasi.bo.RecordBo;
import com.yasi.dao.RecordDao;
import com.yasi.vo.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
@Service
public class RecordBoImpl implements RecordBo {

	@Autowired
	private RecordDao recordDao;

	@Override
	public void insertRecord(Record vo) throws SysRuntimeException {
		recordDao.insertRecord(vo);
	}

	@Override
	public List<Record> findRecordByWhere(Map<String, Objects> map) throws SysRuntimeException {
		return recordDao.findRecordByWhere(map);
	}

	@Override
	public List<Record> findRecordByPage(Map<String, Object> map)
			throws SysRuntimeException {
		return recordDao.findRecordByPage(map);
	}

	@Override
	public int findRecordCountByPojo(Record vo) throws SysRuntimeException {
		return recordDao.findRecordCountByPojo(vo);
	}

	@Override
	public void updateRecord(Record vo) throws SysRuntimeException {
		recordDao.updateRecord(vo);
	}

	@Override
	public List<Record> findRecordByPojo(Record vo) throws SysRuntimeException {
		return recordDao.findRecordByPojo(vo);
	}
}

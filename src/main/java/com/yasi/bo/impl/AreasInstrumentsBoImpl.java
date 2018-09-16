package com.yasi.bo.impl;

import com.common.system.SysRuntimeException;
import com.yasi.bo.AreasInstrumentsBo;
import com.yasi.dao.AreasInstrumentsDao;
import com.yasi.vo.AreasInstruments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  @author wangzi
 */
@Service
public class AreasInstrumentsBoImpl implements AreasInstrumentsBo{

	@Autowired
	private AreasInstrumentsDao areasInstrumentsDao;

	@Override
	public List<AreasInstruments> findAreasInstrumentsByPojo(AreasInstruments vo)
			throws SysRuntimeException {
		return areasInstrumentsDao.findAreasInstrumentsByPojo(vo);
	}

}

package com.yasi.bo.impl;

import com.common.system.SysRuntimeException;
import com.yasi.bo.YascmfInstrumentsBo;
import com.yasi.dao.YascmfInstrumentsDao;
import com.yasi.vo.YascmfInstruments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
@Service
public class YascmfInstrumentsBoImpl implements YascmfInstrumentsBo{

	@Autowired
	private YascmfInstrumentsDao yascmfInstrumentsDao;
	
	@Override
	public List<YascmfInstruments> findYascmfInstrumentsByPojo(
			YascmfInstruments vo) throws SysRuntimeException {
		return yascmfInstrumentsDao.findYascmfInstrumentsByPojo(vo);
	}

}

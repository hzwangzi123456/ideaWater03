package com.yasi.bo.impl;

import com.common.system.SysRuntimeException;
import com.yasi.bo.YascmfAreasBo;
import com.yasi.dao.YascmfAreasDao;
import com.yasi.vo.YascmfAreas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
@Service
public class YascmfAreasBoImpl implements YascmfAreasBo{

	@Autowired
	private YascmfAreasDao yascmfAreasDao;
	
	@Override
	public List<YascmfAreas> findYascmfAreasByPojo(YascmfAreas vo)
			throws SysRuntimeException {
		return yascmfAreasDao.findYascmfAreasByPojo(vo);
	}

	@Override
	public List<String> findYascmfAreasCity() throws SysRuntimeException {
		return yascmfAreasDao.findYascmfAreasCity();
	}

}

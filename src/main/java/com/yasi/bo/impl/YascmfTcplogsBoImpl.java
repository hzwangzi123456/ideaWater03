package com.yasi.bo.impl;

import com.common.system.SysRuntimeException;
import com.yasi.bo.YascmfTcplogsBo;
import com.yasi.dao.YascmfTcplogsDao;
import com.yasi.vo.YascmfTcplogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
@Service
public class YascmfTcplogsBoImpl implements YascmfTcplogsBo{

	@Autowired
	private YascmfTcplogsDao yascmfTcplogsDao;
	
	@Override
	public void insertYascmfTcplogs(YascmfTcplogs vo)
			throws SysRuntimeException {
		yascmfTcplogsDao.insertYascmfTcplogs(vo);
	}
	
	@Override
	public List<YascmfTcplogs> findYascmfTcplogsByPojo(YascmfTcplogs vo)
			throws SysRuntimeException {
		return yascmfTcplogsDao.findYascmfTcplogsByPojo(vo);
	}

}

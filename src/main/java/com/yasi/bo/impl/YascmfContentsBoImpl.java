package com.yasi.bo.impl;

import com.common.system.SysRuntimeException;
import com.yasi.bo.YascmfContentsBo;
import com.yasi.dao.YascmfContentsDao;
import com.yasi.vo.YascmfContents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
@Service
public class YascmfContentsBoImpl implements YascmfContentsBo{
	
	@Autowired
	private YascmfContentsDao yascmfContentsDao ;
	
	@Override
	public List<YascmfContents> findYascmfContentsByPojo(YascmfContents vo)
			throws SysRuntimeException {
		return yascmfContentsDao.findYascmfContentsByPojo(vo);
	}
	
}

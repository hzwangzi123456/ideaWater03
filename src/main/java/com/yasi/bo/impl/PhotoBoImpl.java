package com.yasi.bo.impl;

import com.common.system.SysRuntimeException;
import com.yasi.bo.PhotoBo;
import com.yasi.dao.PhotoDao;
import com.yasi.vo.Photo;
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
public class PhotoBoImpl implements PhotoBo{

	@Autowired
	private PhotoDao photoDao;
	
	@Override
	public void insertPhoto(Photo vo) throws SysRuntimeException {
		photoDao.insertPhoto(vo);
	}

	@Override
	public List<Photo> findPhotoByPojo(Photo vo) throws SysRuntimeException {
		return photoDao.findPhotoByPojo(vo);
	}

	@Override
	public List<Photo> findPhotoByWhere(Map<String, Objects> map) throws SysRuntimeException {
		return photoDao.findPhotoByWhere(map);
	}

	@Override
	public void deletePhotoByKey(Photo vo) throws SysRuntimeException {
		 photoDao.deletePhotoByKey(vo);
		 return;
	}
}

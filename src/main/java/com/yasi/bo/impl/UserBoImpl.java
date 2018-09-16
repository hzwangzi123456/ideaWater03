package com.yasi.bo.impl;

import com.common.system.SysRuntimeException;
import com.yasi.bo.UserBo;
import com.yasi.dao.UserDao;
import com.yasi.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
@Service
public class UserBoImpl implements UserBo{

	@Autowired
	private UserDao userDao;
	
	@Override
	public List<User> findUserByPojo(User vo) throws SysRuntimeException {
		return userDao.findUserByPojo(vo);
	}

}

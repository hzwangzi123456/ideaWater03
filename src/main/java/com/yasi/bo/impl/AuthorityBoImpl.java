package com.yasi.bo.impl;

import com.yasi.bo.AuthorityBo;
import com.yasi.dao.AuthorityDao;
import com.yasi.vo.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
@Service
public class AuthorityBoImpl implements AuthorityBo{

	@Autowired
	private AuthorityDao authorityDao;
	
	@Override
	public List<Authority> findAuthorityByPojo(Authority vo) {
		return authorityDao.findAuthorityByPojo(vo);
	}

}

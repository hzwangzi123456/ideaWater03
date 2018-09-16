package com.yasi.bo.impl;

import com.common.system.SysRuntimeException;
import com.yasi.bo.AuthorityAreasBo;
import com.yasi.dao.AuthorityAreasDao;
import com.yasi.vo.AuthorityAreas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangzi
 */
@Service
public class AuthorityAreasBoImpl implements AuthorityAreasBo {

    @Autowired
    private AuthorityAreasDao authorityAreasDao;

    @Override
    public List<AuthorityAreas> findAuthorityAreasByPojo(AuthorityAreas vo)
            throws SysRuntimeException {
        return authorityAreasDao.findAuthorityAreasByPojo(vo);
    }

    @Override
    public List<String> findAuthorityAreasProvinceByPojo(AuthorityAreas vo)
            throws SysRuntimeException {
        return authorityAreasDao.findAuthorityAreasProvinceByPojo(vo);
    }

}

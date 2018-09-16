package com.yasi.bo;

import com.common.system.SysRuntimeException;
import com.yasi.vo.AuthorityAreas;

import java.util.List;

/**
 * @author wangzi
 */
public interface AuthorityAreasBo {
    /**
     * find AuthorityAreas by pojo
     *
     * @param vo AuthorityAreas
     * @return
     * @throws SysRuntimeException
     */
    List<AuthorityAreas> findAuthorityAreasByPojo(AuthorityAreas vo) throws SysRuntimeException;

    /**
     * find AuthorityAreasProvince by pojo
     *
     * @param vo AuthorityAreas
     * @return
     * @throws SysRuntimeException
     */
    List<String> findAuthorityAreasProvinceByPojo(AuthorityAreas vo) throws SysRuntimeException;
}

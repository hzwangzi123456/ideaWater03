package com.yasi.bo;

import com.yasi.vo.Authority;

import java.util.List;
/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
public interface AuthorityBo {
    /**
     * find Authority by pojo
     * @param vo Authority
     * @return
     */
    List<Authority> findAuthorityByPojo(Authority vo);
}

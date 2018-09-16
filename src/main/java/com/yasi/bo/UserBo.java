package com.yasi.bo;

import com.common.system.SysRuntimeException;
import com.yasi.vo.User;

import java.util.List;
/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
public interface UserBo {
    /**
     * find User by pojo
     * @param vo pojo
     * @return User List
     * @throws SysRuntimeException
     */
    List<User> findUserByPojo(User vo) throws SysRuntimeException;
}

package com.yasi.dao;

import com.yasi.vo.User;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
@Repository
public interface UserDao {
    /**
     * find User by pojo
     * @param vo pojo
     * @return User List
     */
    List<User> findUserByPojo(User vo);
}

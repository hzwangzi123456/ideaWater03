package com.yasi.dao;

import com.yasi.vo.AuthorityAreas;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 *  @author wangzi
 */
@Repository
public interface AuthorityAreasDao {
    /**
     * find AuthorityAreas by pojo
     * @param vo AuthorityAreas
     * @return
     */
    List<AuthorityAreas> findAuthorityAreasByPojo(AuthorityAreas vo);

    /**
     * find AuthorityAreasProvince by pojo
     * @param vo AuthorityAreas
     * @return
     */
    List<String> findAuthorityAreasProvinceByPojo(AuthorityAreas vo);
}

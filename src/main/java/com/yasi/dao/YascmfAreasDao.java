package com.yasi.dao;

import com.yasi.vo.YascmfAreas;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
@Repository
public interface YascmfAreasDao {

    /**
     * find YascmfAreas by pojo
     * @param vo pojo
     * @return
     */
    List<YascmfAreas> findYascmfAreasByPojo(YascmfAreas vo);

    /**
     * find YascmfAreasCity
     * @return YascmfAreasCity List
     */
    List<String> findYascmfAreasCity();
}

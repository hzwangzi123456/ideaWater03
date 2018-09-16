package com.yasi.dao;

import com.yasi.vo.YascmfContents;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
@Repository
public interface YascmfContentsDao {
	/**
	 * find YascmfContents by pojo
	 * @param vo pojo
	 * @return YascmfContents List
     */
	List<YascmfContents> findYascmfContentsByPojo(YascmfContents vo);
}
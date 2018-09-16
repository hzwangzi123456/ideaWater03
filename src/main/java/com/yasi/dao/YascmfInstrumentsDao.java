package com.yasi.dao;

import com.yasi.vo.YascmfInstruments;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
@Repository
public interface YascmfInstrumentsDao {
	/**
	 * find YascmfInstruments by pojo
	 * @param vo pojo
	 * @return YascmfInstruments List
     */
	List<YascmfInstruments> findYascmfInstrumentsByPojo(YascmfInstruments vo);
}

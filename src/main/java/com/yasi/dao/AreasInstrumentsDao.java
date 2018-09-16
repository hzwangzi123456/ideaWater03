package com.yasi.dao;

import com.yasi.vo.AreasInstruments;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  @author wangzi
 */
@Repository
public interface AreasInstrumentsDao {
	/**
	 * find AreasInstruments by pojo
	 * @param vo
	 * @return
     */
	List<AreasInstruments> findAreasInstrumentsByPojo(AreasInstruments vo);
}

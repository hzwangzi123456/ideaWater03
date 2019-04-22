package com.yasi.dao;

import com.yasi.vo.AreasInstruments;

import java.util.List;

/**
 *  @author wangzi
 */
public interface AreasInstrumentsDao {
	/**
	 * find AreasInstruments by pojo
	 * @param vo
	 * @return
     */
	List<AreasInstruments> findAreasInstrumentsByPojo(AreasInstruments vo);
}

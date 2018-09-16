package com.yasi.bo;

import com.common.system.SysRuntimeException;
import com.yasi.vo.AreasInstruments;

import java.util.List;
/**
 *  @author wangzi
 */
public interface AreasInstrumentsBo {
	/**find AreasInstruments by vo
	 * @param vo
	 * @return
	 * @throws SysRuntimeException
     */
	List<AreasInstruments> findAreasInstrumentsByPojo(AreasInstruments vo) throws SysRuntimeException;
}

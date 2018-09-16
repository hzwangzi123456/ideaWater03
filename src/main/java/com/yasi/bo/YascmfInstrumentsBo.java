package com.yasi.bo;

import com.common.system.SysRuntimeException;
import com.yasi.vo.YascmfInstruments;

import java.util.List;
/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
public interface YascmfInstrumentsBo {
	/**
	 * find YascmfInstruments by pojo
	 * @param vo pojo
	 * @return YascmfInstruments List
	 * @throws SysRuntimeException
     */
	List<YascmfInstruments> findYascmfInstrumentsByPojo(YascmfInstruments vo) throws SysRuntimeException;
}

package com.yasi.bo;

import com.common.system.SysRuntimeException;
import com.yasi.vo.YascmfAreas;

import java.util.List;
/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
public interface YascmfAreasBo {
	/**
	 * find YascmfAreas by pojo
	 * @param vo pojo
	 * @return YascmfAreas List
	 * @throws SysRuntimeException
     */
	List<YascmfAreas> findYascmfAreasByPojo(YascmfAreas vo) throws SysRuntimeException;

	/**
	 * find YascmfAreasCity
	 * @return YascmfAreasCity List
	 * @throws SysRuntimeException
     */
	List<String> findYascmfAreasCity() throws SysRuntimeException;
}

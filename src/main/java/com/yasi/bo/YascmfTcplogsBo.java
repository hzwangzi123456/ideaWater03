package com.yasi.bo;

import com.common.system.SysRuntimeException;
import com.yasi.vo.YascmfTcplogs;

import java.util.List;
/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
public interface YascmfTcplogsBo {
	/**
	 * insert YascmfTcplogs
	 * @param vo YascmfTcplogs
	 * @throws SysRuntimeException
     */
	void insertYascmfTcplogs(YascmfTcplogs vo) throws SysRuntimeException;

	/**
	 * find YascmfTcplogs by pojo
	 * @param vo pojo
	 * @return YascmfTcplogs List
	 * @throws SysRuntimeException
     */
	List<YascmfTcplogs> findYascmfTcplogsByPojo(YascmfTcplogs vo) throws SysRuntimeException;
}

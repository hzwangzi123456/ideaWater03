package com.yasi.bo;

import com.common.system.SysRuntimeException;
import com.yasi.vo.YascmfDatas;

import java.util.List;
/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
public interface YascmfDatasBo {

    /**
     * yascmfDatasBoTest
     * @throws SysRuntimeException
     */
    void yascmfDatasBoTest() throws SysRuntimeException;

    /**
     * find YascmfDatas by pojo
     * @param vo pojo
     * @return YascmfDatas List
     * @throws SysRuntimeException
     */
    List<YascmfDatas> findYascmfDatasByPojo(YascmfDatas vo) throws SysRuntimeException;

    /**
     * find YascmfDatas by times
     * @param instrumentId
     * @param start
     * @param end
     * @return YascmfDatas List
     * @throws SysRuntimeException
     */
    List<YascmfDatas> findYascmfDatasByTime(String instrumentId, String start, String end) throws SysRuntimeException;

    /**
     * find YascmfDatasCount by pojo
     * @param vo pojo
     * @return Count
     * @throws SysRuntimeException
     */
    int findYascmfDatasCountByPojo(YascmfDatas vo) throws SysRuntimeException;

    /**
     * insert YascmfDatas
     * @param vo pojo
     * @throws SysRuntimeException
     */
    void insertYascmfDatas(YascmfDatas vo) throws SysRuntimeException;

    /**
     * process TCPLog
     * @param log TCPLog
     * @return true legal false illegal
     * @throws SysRuntimeException
     */
    boolean processTCPDatas(String log) throws SysRuntimeException;
}

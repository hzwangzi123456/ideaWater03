package com.yasi.bo;

import com.common.system.SysRuntimeException;
import com.yasi.vo.YascmfContents;

import java.util.List;
/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
public interface YascmfContentsBo {
    /**
     * find YascmfContents by pojo
     * @param vo pojo
     * @return YascmfContents List
     * @throws SysRuntimeException
     */
    List<YascmfContents> findYascmfContentsByPojo(YascmfContents vo) throws SysRuntimeException;
}

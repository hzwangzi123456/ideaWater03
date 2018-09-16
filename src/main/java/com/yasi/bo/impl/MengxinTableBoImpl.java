package com.yasi.bo.impl;

import com.common.system.SysRuntimeException;
import com.yasi.bo.GenericServiceBo;
import com.yasi.bo.MengxinTableBo;
import com.yasi.dao.MengxinTableMapper;
import com.yasi.vo.MengxinTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author wangzi
 * @date 18/8/25 下午9:00.
 */
@Service
public class MengxinTableBoImpl implements MengxinTableBo {

    @Autowired
    private MengxinTableMapper mengxinTableMapper;

    @Override
    public Integer insert(MengxinTable vo) throws SysRuntimeException {
        return mengxinTableMapper.insert(vo);
    }

    @Override
    public MengxinTable findByKey(MengxinTable vo) throws SysRuntimeException {
        return mengxinTableMapper.findByKey(vo);
    }

    @Override
    public Integer findCountByVo(MengxinTable vo) throws SysRuntimeException {
        return mengxinTableMapper.findCountByVo(vo);
    }

    @Override
    public List<MengxinTable> findByPojo(MengxinTable vo) throws SysRuntimeException {
        return mengxinTableMapper.findByPojo(vo);
    }

    @Override
    public List<MengxinTable> findByPage(Map<String, Object> map) throws SysRuntimeException {
        return mengxinTableMapper.findByPage(map);
    }

    @Override
    public List<MengxinTable> findByWhere(Map<String, Object> map) throws SysRuntimeException {
        return mengxinTableMapper.findByWhere(map);
    }

    @Override
    public List<MengxinTable> findByQueryStr(String queryStr) throws SysRuntimeException {
        return mengxinTableMapper.findByQueryStr(queryStr);
    }

    @Override
    public Integer update(MengxinTable vo) throws SysRuntimeException {
        return null;
    }
}

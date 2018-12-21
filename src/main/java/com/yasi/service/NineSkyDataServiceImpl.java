package com.yasi.service;

import com.common.system.SysRuntimeException;
import com.common.util.DateUtil;
import com.yasi.dao.NineSkyDataMapper;
import com.yasi.model.NineSkyData;
import com.yasi.vo.NineSkyResData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangzi
 * @date 18/12/18 下午3:57.
 */
@Service
public class NineSkyDataServiceImpl implements NineSkyDataService{

    @Autowired
    private NineSkyDataMapper mapper;

    private static Logger logger = Logger.getLogger(NineSkyDataServiceImpl.class);

    @Override
    public NineSkyResData insert(NineSkyData module) throws SysRuntimeException {
        logger.info("NineSkyDataService[]insert[]进入方法");
        NineSkyResData nineSkyResData = new NineSkyResData();
        nineSkyResData.setResult(0);
        nineSkyResData.setMessage("");
        nineSkyResData.setUploadPeriod(30);
        nineSkyResData.setResultTime("");

        try {
            Integer insert = mapper.insert(module);
            if (insert == 1) {
                nineSkyResData.setMessage("success");
                nineSkyResData.setUploadPeriod(30);
                //获取返回时间,举例如2013-06-20 12:34:07
                nineSkyResData.setResultTime(DateUtil.getCurDateStrMiao_());
                return nineSkyResData;
            } else {
                logger.error("NineSkyDataService[]insert[]插入数据出错:");
                //上传数据失败
                nineSkyResData.setResult(1);
                nineSkyResData.setMessage("insert data fail");
                nineSkyResData.setUploadPeriod(30);
                //获取返回时间,举例如2013-06-20 12:34:07
                nineSkyResData.setResultTime(DateUtil.getCurDateStrMiao_());
            }
        } catch (Exception e) {
            logger.error("NineSkyDataService[]insert[]插入数据出错:" + e.getMessage());
            //上传数据失败
            nineSkyResData.setResult(1);
            nineSkyResData.setMessage("insert data fail:" + e.getMessage());
            nineSkyResData.setUploadPeriod(30);
            //获取返回时间,举例如2013-06-20 12:34:07
            nineSkyResData.setResultTime(DateUtil.getCurDateStrMiao_());
            return nineSkyResData;
        }
        return null;
    }
}

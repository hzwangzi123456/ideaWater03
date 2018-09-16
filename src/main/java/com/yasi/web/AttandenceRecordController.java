package com.yasi.web;

import com.common.base.BaseController;
import com.common.system.SysRuntimeException;
import com.common.util.StringUtil;
import com.yasi.bo.AttandenceRecordBo;
import com.yasi.vo.AttandenceRecord;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangzi
 * @date 17/12/30 上午1:55.
 */
@Controller
@Scope("prototype")
@RequestMapping("AttandenceRecordController/")
public class AttandenceRecordController extends BaseController {
    private static Logger logger = Logger
            .getLogger(AttandenceRecordController.class);

    /**
     * 注入业务接口层
     **/
    @Autowired
    private AttandenceRecordBo attandenceRecordBo;


    /**
     * 定义参数
     **/
    private AttandenceRecord attandenceRecord;

    public AttandenceRecordBo getAttandenceRecordBo() {
        return attandenceRecordBo;
    }

    public void setAttandenceRecord(AttandenceRecord attandenceRecord) {
        this.attandenceRecord = attandenceRecord;
    }

    @ModelAttribute
    public void setParaVal(AttandenceRecord attandenceRecord) {
        this.attandenceRecord = attandenceRecord;
    }

    private Map getPojoMap() {
        Map map = new HashMap<String, Object>();
        map.put("workNumber", attandenceRecord.getWorkNumber());
        map.put("date", attandenceRecord.getDate());
        map.put("day", attandenceRecord.getDay());
        map.put("time", attandenceRecord.getTime());
        map.put("time", attandenceRecord.getState());
        return map;
    }

    /**
     * 根据pojo查询数据
     */
    @RequestMapping("findAttandenceRecordByPojo.do")
    public void findAttandenceRecordByPojo() {
        List<AttandenceRecord> attandenceRecordList = null;
        try {
            attandenceRecordList = attandenceRecordBo.findAttandenceRecordByPojo(attandenceRecord);
//            System.out.println(attandenceRecordList);
            if (attandenceRecordList == null || attandenceRecordList.size() == 0) {
                setFailure("未找到考勤数据");
                return;
            }
            setAjaxObject(attandenceRecordList);
        } catch (SysRuntimeException e) {
            e.printStackTrace();
            logger.error(e.toString());
        }

    }

    /**
     * 插入pojo数据
     */
    @RequestMapping("insertAttandenceRecordByPojo.do")
    public void insertAttandenceRecordByPojo() {
        if (StringUtil.isEmpty(attandenceRecord.getTime())) {
            setFailure("未上传打卡时间");
            return;
        }
        if (StringUtil.isEmpty(attandenceRecord.getDate())) {
            setFailure("未上传打卡日期");
            return;
        }
        if (StringUtil.isEmpty(attandenceRecord.getDay())) {
            setFailure("未上传打卡星期几");
            return;
        }
        if (StringUtil.isEmpty(attandenceRecord.getWorkNumber())) {
            setFailure("未上传河长工号");
            return;
        }
        //河长出勤
        attandenceRecord.setState(1);
        try {
            attandenceRecordBo.insertAttandenceRecord(attandenceRecord);
        } catch (SysRuntimeException e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
    }

}

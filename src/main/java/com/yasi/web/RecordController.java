package com.yasi.web;

import com.common.base.BaseController;
import com.common.util.StringUtil;
import com.yasi.bo.RecordBo;
import com.yasi.vo.Record;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
@Controller
@Scope("prototype")
@RequestMapping("RecordController/")
public class RecordController extends BaseController {
    private static Logger logger = Logger.getLogger(RecordController.class);
    private static InputStream in = PhotoController.class.getClassLoader()
            .getResourceAsStream("worktime.properties");
    private static Properties prop = new Properties();

    private Record record;

    @Autowired
    private RecordBo recordBo;

    @ModelAttribute
    public void setParaVal(Record record) {
        this.record = record;
        if (record == null) {
            record = new Record();
        }
    }

    /**
     * 将实体对象转化成Map对象查询条件
     **/
    private Map getPojoMap() {
        Map map = new HashMap<String, String>();
        //map.put("optTimeEndString", barConfig.getOptTimeEndString());//操作时间--结束时间
        map.put("username", record.getUsername());
        map.put("workHour", record.getWorkHour());
        map.put("offHour", record.getOffHour());
        map.put("status", record.getStatus());
        map.put("dateTime", record.getDateTime());
        map.put("recordDay", record.getRecordDay());
        map.put("id", record.getId());
        return map;
    }

    @RequestMapping("test")
    public void test() {
    }

    @RequestMapping("findRecordByPojo")
    public void findRecordByPojo() {
        List<Record> listData = recordBo.findRecordByPojo(record);
        if (listData == null || listData.size() == 0) {
            setFailure("未找到数据");
        }
        setAjaxGridData(listData);
    }

    @RequestMapping("findRecordCountByPojo")
    public void findRecordCountByPojo() {
        try {
            Integer cnt = recordBo.findRecordCountByPojo(record);
            setAjaxMsg(cnt.toString());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
    }

    @RequestMapping("findRecordByTimeMap")
    public void findRecordByTimeMap(String startDateTime,String endDateTime) {
        if(StringUtil.isNotNullStr(startDateTime) && StringUtil.isNotNullStr(endDateTime)) {
            if(StringUtil.strAIsBiggerThanstrB(startDateTime,endDateTime)) {
                setFailure("起始时间不能大于结束时间");
                return;
            }
        }
        Map schMap = getPojoMap();
        schMap.put("startDateTime",startDateTime);
        schMap.put("endDateTime",endDateTime);
        List<Record> listData = recordBo.findRecordByWhere(schMap);
        if (listData != null && listData.size() != 0) {
            setAjaxGridData(listData);
        } else {
            setFailure("未找到数据");
        }
    }

    @RequestMapping("findRecordByTimePageMap")
    public void findRecordByTimePageMap(String startDateTime,String endDateTime) {
        if(StringUtil.isNotNullStr(startDateTime) && StringUtil.isNotNullStr(endDateTime)) {
            if(StringUtil.strAIsBiggerThanstrB(startDateTime,endDateTime)) {
                setFailure("起始时间不能大于结束时间");
                return;
            }
        }
        Map schMap = getPojoMap();
        schMap.put("startDateTime",startDateTime);
        schMap.put("endDateTime",endDateTime);
        schMap.putAll(getSchMap());
        List<Record> listData = recordBo.findRecordByWhere(schMap);
        if (listData != null && listData.size() != 0) {
            setAjaxGridData(listData);
        } else {
            setFailure("未找到数据");
        }
    }

    @RequestMapping("findRecordByPage")
    public void findRecordByPage() {
        try {
            Integer cnt = recordBo.findRecordCountByPojo(record);
            this.getPageUtil().setTotal(cnt);
            List<Record> listData = null;
            if (cnt > 0) {
                Map schMap = getSchMap();
                schMap.putAll(getPojoMap());
                listData = recordBo.findRecordByPage(schMap);
            }
            if (listData != null && listData.size() != 0) {
                setAjaxGridData(listData);
            } else {
                setFailure("未找到数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
    }

    @RequestMapping("insertRecord")
    public void insertRecord() throws IOException {
        prop.load(in);
        Integer standardWorkTime = Integer.parseInt(prop.getProperty("standardWorkTime"));
        Integer standardOffTime = Integer.parseInt(prop.getProperty("standardOffTime"));
        if (record.getDateTime() == null || "".equals(record.getDateTime())) {
            setFailure("打卡时间不能为空");
            return;
        }
        if (record.getOffHour() == null || "".equals(record.getOffHour())) {
            setFailure("下班时间不能为空");
            return;
        }
        if (record.getWorkHour() == null || "".equals(record.getWorkHour())) {
            setFailure("上班时间不能为空");
            return;
        }
        if (record.getUsername() == null || "".equals(record.getUsername())) {
            setFailure("用户名不能为空");
            return;
        }
        //xx:xx转换成minutes
        Integer workHour = Integer.parseInt(record.getWorkHour().split(":")[0]) * 60 + Integer.parseInt(record.getWorkHour().split(":")[1]);
        Integer offHour = Integer.parseInt(record.getOffHour().split(":")[0]) * 60 + Integer.parseInt(record.getOffHour().split(":")[1]);
        if (workHour > offHour) {
            setFailure("上班打卡时间要小于下班打卡时间");
            return;
        }
        if (workHour <= standardWorkTime && offHour >= standardOffTime) {

            //正常
            record.setStatus(1);
        } else if (workHour > standardWorkTime) {

            //迟到
            record.setStatus(2);
        } else if (offHour < standardOffTime) {

            //早退
            record.setStatus(3);
        }
        recordBo.insertRecord(record);
        setSuccess("insert record success");
    }
}

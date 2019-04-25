package com.hlj.web;

import com.hlj.dto.DownLoadDto;
import com.hlj.service.WriteService;
import com.hlj.timer.Download;
import com.hlj.timer.Timer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.common.util.DateUtil.getCurDateStrY_m_d;


/**
 * @author wangzi
 * @date 19/4/13 下午12:00.
 */
@RestController
@RequestMapping(value = "/ZjWaterController")
@Slf4j
public class ZjWaterController {

    @Autowired
    private WriteService writeService;

    @Autowired
    private Download downloadService;

    @Autowired
    private Timer timer;

    @RequestMapping(value = "/addWaterSurface.do")
    public void addWaterSurface() {
        try {
            String curDateStrY_m_d = getCurDateStrY_m_d();
            //todo test
            curDateStrY_m_d = "2019-04-24";
            String sql = "select * from zj_water_surface where grab_time LIKE '" + curDateStrY_m_d + "%'";
            Boolean aBoolean = writeService.addWaterSurface(sql);
            if (aBoolean) {
                log.info("downLoadWs[]插入数据库成功");
            } else {
                log.error("downLoadWs[]插入数据库失败");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 开始地表水定时任务
     */
    @RequestMapping(value = "/startSwTimeTask.do")
    public void startSwTimeTask() {
        log.info("startSwTimeTask[]进入方法");
        timer.start();
        log.info("startSwTimeTask[]离开方法");
    }


    /**
     * test下载文件
     */
    @RequestMapping(value = "/testDown.do")
    public void testDown() {
        DownLoadDto dto = new DownLoadDto();
        dto.setUrl("http://112.124.7.188/projects/zj_water/zj_water_surface.sqlite3");
        dto.setFileName("zj_water_surface.sqlite3");
        dto.setDownDir("/Users/ziwang/Desktop/test/sqliteFile/");
        dto.setTableName("zj_water_surface");
        downloadService.downLoad(dto);
    }

}

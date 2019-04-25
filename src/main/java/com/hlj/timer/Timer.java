package com.hlj.timer;

import com.hlj.dto.DownLoadDto;
import com.hlj.service.WriteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.common.util.DateUtil.MillisecondsPerDay;
import static com.common.util.DateUtil.getCurDateStrY_m_d;

/**
 * @author wangzi
 * @date 19/4/22 下午4:44.
 * 使用ScheduledThreadPoolExecutor来实现定时任务
 */
@Slf4j
@Component
public class Timer {

    /**
     * 5分钟的毫秒数
     */
    public static final long MillisecondsPer5 = 5 * 60 * 1000;

    /**
     * 定时任务池
     */
    private ScheduledExecutorService scheduExec;

    @Autowired
    public Download download;

    @Autowired
    public WriteService writeService;

    public Timer() {
        //线程池中有十个线程
        this.scheduExec = Executors.newScheduledThreadPool(10);
        log.info("SEtimer[]创建好线程池");
    }

    /**
     * 获得开始时间的Date
     *
     * @return
     */
    private Date getTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 01);
        Date time = calendar.getTime();
        return time;
    }

    public void start() {
        //启动地表水任务`
        init(new WsTask01());
        //启动饮用水任务
        init(new WdTask());
    }

    /**
     * 启动任务
     */
    private void init(TimerTask timerTask) {
        //正式
        this.scheduExec.scheduleAtFixedRate(timerTask, getTime().getTime() - System.currentTimeMillis(), MillisecondsPerDay, TimeUnit.MILLISECONDS);
        //测试
//        this.scheduExec.scheduleAtFixedRate(timerTask, 1000, MillisecondsPer5, TimeUnit.MILLISECONDS);
    }

    /**
     * 地表水文件下载,插入数据
     */
    private class WsTask01 extends TimerTask {
        @Override
        public void run() {
            log.info("地表水任务[]开始执行");
            DownLoadDto dto = new DownLoadDto();
            dto.setUrl("http://112.124.7.188/projects/zj_water/zj_water_surface.sqlite3");
            dto.setFileName("zj_water_surface.sqlite3");
            dto.setDownDir("/Users/ziwang/Desktop/test/sqliteFile/");
            dto.setTableName("zj_water_surface");
            Boolean aBoolean = download.downLoad(dto);
            if (!aBoolean) {
                log.info("地表水任务[]结束任务");
                return;
            }
            try {
                String curDateStrY_m_d = getCurDateStrY_m_d();
                String sql = "select * from zj_water_surface where grab_time LIKE '" + curDateStrY_m_d + "%'";
                Boolean result = writeService.addWaterSurface(sql);
                if (result) {
                    log.info("地表水任务 run[]插入数据库成功");
                } else {
                    log.error("地表水任务 run[]插入数据库失败");
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            log.info("地表水任务[]结束");
        }
    }

    /**
     * 饮用水文件下载,插入数据
     */
    private class WdTask extends TimerTask {

        @Override
        public void run() {
            log.info("饮用水任务开始");
            DownLoadDto dto = new DownLoadDto();
            dto.setUrl("http://112.124.7.188/projects/zj_water/zj_water_drinking.sqlite3");
            dto.setFileName("zj_water_drinking.sqlite3");
            dto.setDownDir("/Users/ziwang/Desktop/test/sqliteFile/");
            dto.setTableName("zj_water_drinking");
            Boolean aBoolean = download.downLoad(dto);
            if (!aBoolean) {
                log.info("饮用水任务结束");
                return;
            }
            try {
                String curDateStrY_m_d = getCurDateStrY_m_d();
                String sql = "select * from zj_water_drinking where grab_time LIKE '" + curDateStrY_m_d + "%'";
                Boolean result = writeService.addWaterDrinking(sql);
                if (result) {
                    log.info("饮用水任务 run[]插入数据库成功");
                } else {
                    log.error("饮用水任务 run[]插入数据库失败");
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            log.info("饮用水任务结束");
        }
    }
}

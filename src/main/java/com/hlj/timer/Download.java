package com.hlj.timer;

import com.hlj.dto.DownLoadDto;
import com.hlj.service.WriteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author wangzi
 * @date 19/4/19 下午10:08.
 * 下载数据库文件
 */
@Slf4j
@Component
public class Download {

    @Autowired
    private WriteService writeService;

    /**
     * 从网络Url中下载文件
     *
     * @param urlStr
     * @param fileName
     * @param savePath
     * @throws IOException
     */
    private void downLoadFromUrl(String urlStr, String fileName, String savePath) throws IOException {
        log.info("downLoadFromUrl[]进入方法");
        log.info("downLoadFromUrl[]参数  urlStr:{},fileName:{},savePath:{}", urlStr, fileName, savePath);
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(3 * 1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //得到输入流
        InputStream inputStream = conn.getInputStream();
        //获取自己数组
        byte[] getData = readInputStream(inputStream);

        //文件保存位置
        File saveDir = new File(savePath);
        if (!saveDir.exists()) {
            saveDir.mkdir();
        }

        log.info("downLoadFromUrl[]文件保存路径:{}", saveDir + File.separator + fileName);
        File file = new File(saveDir + File.separator + fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if (fos != null) {
            fos.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }
        log.info("downLoadFromUrl[]url:{} 下载成功", url);
        log.info("downLoadFromUrl[]离开方法");
    }

    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    private byte[] readInputStream(InputStream inputStream) throws IOException {
        log.info("readInputStream[]进入方法");
        log.info("readInputStream[]参数  inputStream:{}", inputStream);
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while (((len = inputStream.read(buffer)) != -1)) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        log.info("readInputStream[]离开方法");
        return bos.toByteArray();
    }

    /**
     * 下载文件
     * @param dto
     */
    public Boolean downLoad(DownLoadDto dto) {
        log.info("downLoad[]进入方法");
        String url = dto.getUrl();
        try {
            downLoadFromUrl(url,
                    dto.getFileName(), dto.getDownDir());
            log.info("downLoad[]下载文件完毕");
        } catch (Exception e) {
            log.error("downLoad[]下载{}出错:{}", url, e.getMessage());
            log.info("downLoad[]离开方法");
            return false;
        }
        log.info("downLoad[]离开方法");
        return true;
    }

    /**
     *
     * 下载地表水的sqlite3
     */
    @Deprecated
    private void downLoadWs() {
        log.info("downLoadWs[]进入方法");
        String url = "http://112.124.7.188/projects/zj_water/zj_water_surface.sqlite3";
        try {
            downLoadFromUrl(url,
                    "zj_water_surface.sqlite3", "/Users/ziwang/Desktop/test/sqliteFile/");
            log.info("downLoadWs[]下载文件完毕");
        } catch (Exception e) {
            log.error("downLoadWs[]下载{}出错:{}", url, e.getMessage());
        }
        log.info("downLoadWs[]离开方法");
    }

    /**
     * 下载饮用水的sqlite3
     */
    @Deprecated
    private void downLoadWd() {
        log.info("downLoadWd[]进入方法");
        String url = "http://112.124.7.188/projects/zj_water/zj_water_drinking.sqlite3";
        try {
            downLoadFromUrl(url,
                    "zj_water_drinking.sqlite3", "/Users/ziwang/Desktop/test/sqliteFile/");
            log.info("downLoadWs[]下载文件完毕");
        } catch (Exception e) {
            log.error("downLoadWd[]下载{}出错:{}", url, e.getMessage());
        }
        log.info("downLoadWd[]离开方法");
    }
}

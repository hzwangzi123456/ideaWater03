package com.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzi
 * @date 19/4/10 下午8:48.
 */
@Slf4j
public class FileUtils {

    private static Logger logger = Logger
            .getLogger(FileUtils.class);

    /**
     * JPEG格式拓展名
     */
    public static final String JPEG = ".jpeg";

    /**
     *
     * @param file 文件
     * @param path 文件存放路径
     * @param fileName 源文件名
     * @return
     */
    public static boolean upload(MultipartFile file, String path, String fileName){
        // 生成新的文件名
        //String realPath = path + "/" + FileNameUtils.getFileName(fileName);

        //使用原文件名
        String realPath = path + File.separator + fileName + JPEG;

        File dest = new File(realPath);

        //判断文件父目录是否存在
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }

        try {
            //保存文件
            file.transferTo(dest);
            return true;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            logger.error("保存文件出错:" + e.getMessage());
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("保存文件出错:" + e.getMessage());
            return false;
        }
    }

    /**
     * 读取TEXT文件
     * @param pathname 文件绝对路径
     * @return 每一行的字符串生成的List
     */
    public static List<String> readText(String pathname) {
        List<String> strs = new ArrayList<>();
        try {
            FileReader reader = new FileReader(pathname);
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            while ((line = br.readLine()) != null) {
                strs.add(line);
            }
        } catch (Exception e) {
            log.error("读取文件出错:{}", e.getMessage());
        }
        return strs;
    }

}

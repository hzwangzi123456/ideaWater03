package com.jt.web;

import com.common.util.DateUtil;
import com.common.util.FileUtils;
import com.common.util.StringUtil;
import com.common.util.UuidUtil;
import com.jt.bean.Picture;
import com.jt.dto.UploadPhotoResDto;
import com.jt.service.UploadPhotoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

/**
 * @author wangzi
 * @date 19/4/10 下午8:46.
 * 图片上传Controller
 */
@RestController
@Scope("prototype")
@RequestMapping(value = "/UploadPhotoController")
public class UploadPhotoController {
    private static Logger logger = Logger
            .getLogger(UploadPhotoController.class);

    //本地调试路径
    private static final String local = File.separator + "Users" + File.separator + "ziwang" + File.separator + "Desktop" + File.separator + "test";

    @Autowired
    private UploadPhotoService service;

    /**
     * 上传文件接口
     *
     * @param file 要上传的文件
     * @param id   携带的参数
     * @return
     */
    @RequestMapping("/fileUpload.do")
    public Map upload(@RequestParam("fileName") MultipartFile file, String id) {
        // 要上传的目标文件存放路径
        String localPath = local;

        UploadPhotoResDto resDto = new UploadPhotoResDto();

        if (StringUtil.isEmpty(id)) {
            resDto.setMsg("id参数不合法");
            resDto.setResult(1);
            resDto.setResultTime(DateUtil.getCurDateStrMiao_());
            return resDto.dto2map();
        }

        String fileName = UuidUtil.get32UUID();
        try {
            // 上传成功或者失败的提示
            if (FileUtils.upload(file, localPath, fileName)) {
                logger.info(id + ":上传成功");
                resDto.setMsg(id + ":上传成功");
                resDto.setResult(0);
                resDto.setResultTime(DateUtil.getCurDateStrMiao_());
                Picture picture = new Picture();
                picture.setFilePath(localPath + File.separator + fileName + ".jpeg");
                picture.setPicId(id);
                picture.setDate(DateUtil.getCurDateStrMiao_());
                Boolean result = service.addPhoto(picture);
                if (result) {
                    logger.info(fileName + "入库成功");
                } else {
                    logger.error(fileName + "入库失败");
                }
            } else {
                logger.error(fileName + "上传失败");
                resDto.setMsg(id + ":上传失败");
                resDto.setResult(1);
                resDto.setResultTime(DateUtil.getCurDateStrMiao_());
            }
        } catch (Exception e) {
            logger.error("内部错误:" + e.getMessage());
            resDto.setMsg("内部错误");
            resDto.setResult(1);
            resDto.setResultTime(DateUtil.getCurDateStrMiao_());
        }
        return resDto.dto2map();
    }
}

package com.jt.web;

import com.common.util.DateUtil;
import com.common.util.FileUtils;
import com.common.util.StringUtil;
import com.common.util.UuidUtil;
import com.jt.bean.Picture;
import com.jt.dto.UploadPhotoResDto;
import com.jt.service.UploadPhotoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author wangzi
 * @date 19/4/10 下午8:46.
 * 图片上传Controller
 * todo 访问可以通过 http://120.55.47.216:8060/pictures/e64d10129f0f4dd2a7a29ea2cef51a4a.jpeg
 * todo 现在图片上传完成,下载到本地或者展示要和冯老师确认,暴露给外部是写在tomcat的conf下service.xml最后一句
 * todo 参考 <Context docBase ="/usr/local/EasyFit/picture/" path ="/pictures" debug ="0" reloadable ="true"/>
 */
@RestController
@Scope("prototype")
@RequestMapping(value = "/UploadPhotoController")
@Slf4j
public class UploadPhotoController {

    //本地调试路径
    private static final String local = File.separator + "Users" + File.separator + "ziwang" + File.separator + "Desktop" + File.separator + "test";

    @Autowired
    private UploadPhotoService service;

    @RequestMapping("/test.do")
    public void test() {
        log.info("进入test方法");
    }

    /**
     * 上传文件接口
     *
     * @param file 要上传的文件
     * @param id   携带的参数
     * @return
     */
    @Deprecated
    @RequestMapping("/fileUpload.do")
    public Map upload(@RequestParam("fileName") MultipartFile file, String id) {
        log.info("upload[]进入upload方法[]file:{},id:{}", file, id);
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
            log.info("upload[]调用FileUtils.upload方法");
            // 上传成功或者失败的提示
            if (FileUtils.upload(file, localPath, fileName)) {
                log.info(id + ":上传成功");
                resDto.setMsg(id + ":上传成功");
                resDto.setResult(0);
                resDto.setResultTime(DateUtil.getCurDateStrMiao_());
                Picture picture = new Picture();
                picture.setFilePath(localPath + File.separator + fileName + ".jpeg");
                picture.setPicId(id);
                picture.setDate(DateUtil.getCurDateStrMiao_());
                Boolean result = service.addPhoto(picture);
                if (result) {
                    log.info("upload[]{}入库成功", fileName);
                } else {
                    log.error("upload[]{}入库失败", fileName);
                }
            } else {
                log.error("upload[]{}上传失败", fileName);
                resDto.setMsg(id + ":上传失败");
                resDto.setResult(1);
                resDto.setResultTime(DateUtil.getCurDateStrMiao_());
            }
        } catch (Exception e) {
            log.error("upload[]内部错误:{}", e.getMessage());
            resDto.setMsg("内部错误");
            resDto.setResult(1);
            resDto.setResultTime(DateUtil.getCurDateStrMiao_());
        }
        log.info("upload[]返回upload方法:{}", resDto.dto2map());
        return resDto.dto2map();
    }

    @RequestMapping(value = "/fileUpload02.do")
    public Map fileUpload02(HttpServletRequest request, HttpServletResponse response) {
        log.info("fileUpload02[]进入fileUpload02方法");

        String savePath = local;
        UploadPhotoResDto resDto = new UploadPhotoResDto();
        resDto.setResult(0);
        resDto.setMsg("上传成功");
        Picture model = new Picture();
        model.setFilePath("");
        model.setPicId("");
        model.setDate("");
        //文件全路径
        String path = "";
        try {
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            log.debug("fileUpload02[]创建一个DiskFileItemFactory工厂");
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //2、创建一个文件上传解析器
            log.debug("fileUpload02[]创建一个文件上传解析器");
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(-1);//设置上传文件最大大小
            //解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8");
//            3、判断提交上来的数据是否是上传表单的数据
            if (!ServletFileUpload.isMultipartContent(request)) {
                //按照传统方式获取数据
                log.error("fileUpload02[]没有文件上传");
                resDto.setResult(0);
                resDto.setMsg("没有文件上传");
                resDto.setResultTime(DateUtil.getCurDateStrMiao_());
                return resDto.dto2map();
            }
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            log.debug("fileUpload02[]使用ServletFileUpload解析器解析上传数据");
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem item : list) {
                //如果fileitem中封装的是普通输入项的数据
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    //解决普通输入项的数据的中文乱码问题
                    String value = item.getString("UTF-8");
                    //value = new String(value.getBytes("iso8859-1"),"UTF-8");
                    log.info("fileUpload02[]fileitem中封装的是普通输入项的数据 name:{},value:{}", name, value);
                    model.setPicId(value);
                } else {//如果fileitem中封装的是上传文件
                    //得到上传的文件名称，
                    String filename = item.getName();
                    model.setOldFilename(filename);
                    log.info("fileUpload02[]上传的文件名称:" + filename);
                    if (filename == null || filename.trim().equals("")) {
                        continue;
                    }
                    String[] strs = filename.split("\\.");
                    String extendName = strs[strs.length - 1];
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = filename.substring(filename.lastIndexOf("\\") + 1);
                    //获取item中的上传文件的输入流
                    InputStream in = item.getInputStream();
                    //文件路径
                    path = savePath + File.separator + UuidUtil.get32UUID() + "." + extendName;
                    log.info("fileUpload02[]上传的文件路径:" + path);
                    model.setFilePath(path);
                    //创建一个文件输出流
                    FileOutputStream out = new FileOutputStream(path);
                    //创建一个缓冲区
                    byte buffer[] = new byte[1024];
                    //判断输入流中的数据是否已经读完的标识
                    int len = 0;
                    //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                    while ((len = in.read(buffer)) > 0) {
                        //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                        out.write(buffer, 0, len);
                    }
                    //关闭输入流
                    in.close();
                    //关闭输出流
                    out.close();
                    //删除处理文件上传时生成的临时文件
                    item.delete();
                    log.info("fileUpload02[]文件上传成功！");
                }
            }
            model.setDate(DateUtil.getCurDateStrMiao_());
            //入库
            Boolean aBoolean = service.addPhoto(model);
            if (!aBoolean) {
                log.error("fileUpload02[]内部错误:" + "入库失败");
            } else {
                log.info("fileUpload02[]入库成功");

            }
        } catch (Exception e) {
            log.error("fileUpload02[]内部错误:" + e.getMessage());
            resDto.setMsg("内部错误");
            resDto.setResult(1);
            resDto.setResultTime(DateUtil.getCurDateStrMiao_());
        }
        resDto.setResultTime(DateUtil.getCurDateStrMiao_());
        log.info("fileUpload02[]离开fileUpload02方法");
        return resDto.dto2map();
    }

}

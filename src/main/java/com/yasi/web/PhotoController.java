package com.yasi.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.common.base.BaseController;
import com.common.util.StringUtil;
import com.common.util.Uuid;
import com.yasi.bo.PhotoBo;
import com.yasi.vo.Photo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
@Controller
@Scope("prototype")
@RequestMapping("PhotoController/")
public class PhotoController extends BaseController {
    private static Logger logger = Logger.getLogger(PhotoController.class);
    private static InputStream in = PhotoController.class.getClassLoader()
            .getResourceAsStream("DingDongconfig.properties");
    private static Properties prop = new Properties();

    @Autowired
    private PhotoBo photoBo;

    private Photo photo;

    private String startDateTime;

    private String endDateTime;

    @ModelAttribute
    public void setParaVal(Photo photo, String startDateTime, String endDateTime) {
        this.photo = photo;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * 将实体对象转化成Map对象查询条件
     **/
    private Map getPojoMap() {
        Map map = new HashMap<String, String>();
        //map.put("optTimeEndString", barConfig.getOptTimeEndString());//操作时间--结束时间
        map.put("id", photo.getId());
        map.put("name", photo.getName());
        map.put("instrumentId", photo.getInstrumentId());
        map.put("dateTime", photo.getDateTime());
        map.put("byteArray", photo.getByteArray());
        map.put("base64String", photo.getBase64String());
        map.put("introduce", photo.getIntroduce());
        map.put("position", photo.getPosition());
        return map;
    }

    /**
     * 将byteArray转换为base64String
     *
     * @param arr 包含byteArray的PhotoList
     * @return
     */
    private List<Photo> byteToStr(List<Photo> arr) {
        for (int i = 0; i < arr.size(); i++) {
            String str = "";
            try {
                str = new String(arr.get(i).getByteArray(), "UTF8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            arr.get(i).setBase64String(str);
        }
        return arr;
    }

    @RequestMapping("fildUpload")
    private String fildUpload(
            @RequestParam(value = "file") CommonsMultipartFile mf)
            throws IllegalStateException, IOException {
        prop.load(in);
        String phonenumber = prop.getProperty("phonenumber");
        String DingdongKey = prop.getProperty("DingdongKey");
        String temp = "";
        String tzContent = "【竹林环境监测平台】当前PH值" + temp + "不正确,请及时处理!退订回T";
        System.out.println(phonenumber + " " + DingdongKey + " " + tzContent);
        String pathRoot = request.getSession().getServletContext()
                .getRealPath("");
        // System.out.println("pathRoot" + pathRoot);
        String path = "";
        // System.out.println(JSON.toJSONString(mf).toString());
        // List<String> listImagePath=new ArrayList<String>();
        // for (MultipartFile mf : file) {
        if (!mf.isEmpty()) {
            // 生成uuid作为文件名称
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            System.out.println(uuid);
            // 获得文件类型（可以判断如果不是图片，禁止上传）
            String contentType = mf.getContentType();
            // 获得文件后缀名称
            String imageName = contentType
                    .substring(contentType.indexOf("/") + 1);
            path = "/static/images/" + uuid + "." + imageName;
            mf.transferTo(new File(pathRoot + path));
            // listImagePath.add(path);
        }
        // String tzContent = "【水质环境监测平台】"
        // + "当前PH值不正确,请及时处理!退订回T";
        // DingdongCloudApis.sendTz(DingdongKey,
        // phonenumber, tzContent);
        // }
        // @RequestParam(value="file",required=false) CommonsMultipartFile
        return null;
    }

    @RequestMapping("testPhoto")
    public void testPhoto(String str) {
        System.out.println("str:" + str);
        setSuccess("123");
    }


    @RequestMapping("insertPhoto")
    public void insertPhoto(String base64Array, String dateTime, String position, String introduce) {
        logger.info("base64Array:" + base64Array);
        logger.info("dateTime:" + dateTime);
        logger.info("position:" + position);
        logger.info("introduce:" + introduce);
        JSONArray array = JSON.parseArray(base64Array);
        if (introduce == null || introduce == "") {
            logger.error("未收到introduce");
            setFailure("未收到introduce");
            return;
        }
        if (position == null || position == "") {
            logger.error("未收到postion");
            setFailure("未收到postion");
            return;
        }
        if (dateTime == null || dateTime == "") {
            logger.error("未收到dateTime");
            setFailure("未收到dateTime");
            return;
        }
        if (base64Array == null || base64Array == "") {
            logger.error("未收到base64Array");
            setFailure("未收到base64Array");
            return;
        }
        for (int i = 0; i < array.size(); i++) {
            Photo vo = new Photo();
            vo.setDateTime(dateTime);
            vo.setPosition(position);
            vo.setIntroduce(introduce);
            vo.setId(Uuid.get32UUID());
            byte[] b02 = null;
            try {
                //将字符串转换成为byte数组
                b02 = array.getString(i).getBytes("UTF8");
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("上传失败:" + e.getMessage());
                setFailure("上传失败");
            }
            vo.setByteArray(b02);
            boolean b = photoBo.insertPhoto(vo);
            if (b) {
                logger.info("上传成功");
                setSuccess("上传成功");
            } else {
                logger.error("上传失败");
                setFailure("上传失败");
            }
        }
    }

    @RequestMapping("findPhotoByTimeMap")
    public void findPhotoByTimeMap() {
        if (StringUtil.isNotNullStr(startDateTime) && StringUtil.isNotNullStr(endDateTime)) {
            if (StringUtil.strAIsBiggerThanstrB(startDateTime, endDateTime)) {
                setFailure("起始时间不能大于结束时间");
                return;
            }
        }
        Map map = getPojoMap();
        map.put("startDateTime", startDateTime);
        map.put("endDateTime", endDateTime);
        map.put("sortName", "date_time");
        map.put("orderName", "ASC");
        List<Photo> listData = photoBo.findPhotoByWhere(map);
        if (listData != null && listData.size() != 0) {
            listData = byteToStr(listData);
            setAjaxGridData(listData);
        } else {
            setFailure("未找到数据");
        }
    }

    @RequestMapping("findPhotoByPojo")
    public void findPhotoByPojo() {
        Photo vo = new Photo();
        List<Photo> res = photoBo.findPhotoByPojo(vo);
        if (res == null || res.size() == 0) {
            setFailure("未找到");
            return;
        }
        res = byteToStr(res);
//        for (int i = 0; i < res.size(); i++) {
//            String str = "";
//            try {
//                str = new String(res.get(i).getByteArray(), "UTF8");
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//            res.get(i).setBase64String(str);
//        }
        setAjaxObject(res);
    }

    @RequestMapping("deletePhotoByKey")
    public void deletePhotoByKey(String idArray) {
        JSONArray array = JSON.parseArray(idArray);
        for (int i = 0; i < array.size(); i++) {
            Photo vo = new Photo();
            vo.setId(array.getString(i));
            photoBo.deletePhotoByKey(vo);
        }
        setSuccess("删除成功");
    }
}

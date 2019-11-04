package com.jt.web;

import com.alibaba.fastjson.JSONObject;
import com.common.SysConf;
import com.common.util.DateUtil;
import com.common.util.UuidUtil;
import com.constant.EquipmentTypeEnum;
import com.jt.bean.PictureVO;
import com.jt.dto.GetPhotoResDto;
import com.jt.dto.UploadPhotoResDto;
import com.jt.entity.EquipmentDO;
import com.jt.entity.PictureDO;
import com.jt.service.EquipmentService;
import com.jt.service.UploadPhotoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author wangzi
 * @date 19/4/10 下午8:46.
 * 图片上传Controller
 * todo 访问可以通过 http://120.55.47.216:8060/pictures/e64d10129f0f4dd2a7a29ea2cef51a4a.jpeg
 * todo 现在图片上传完成,下载到本地或者展示要和冯老师确认,暴露给外部是写在tomcat的conf下service.xml最后一句
 * todo 参考 <Context docBase ="/usr/local/EasyFit/picture/" path ="/pictures" debug ="0" reloadable ="true"/>
 */
@RestController
@Scope ( "prototype" )
@RequestMapping ( value = "/UploadPhotoController" )
@Slf4j
public class UploadPhotoController {

    //本地调试路径
//    private static final String local = File.separator + "Users" + File.separator + "ziwang" + File.separator + "Desktop" + File.separator + "test";

    @Autowired
    private UploadPhotoService service;

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private SysConf sysConf;


    @RequestMapping ( value = "/fileUpload02.do" )
    public Map fileUpload02 ( HttpServletRequest request , HttpServletResponse response ) {
        log.info ( "fileUpload02[]进入fileUpload02方法" );

        UploadPhotoResDto resDto = new UploadPhotoResDto ();
        PictureDO model = new PictureDO ();
        //文件全路径
        String path = "";
        EquipmentDO equipmentDO = null;
        try {
            //使用Apache文件上传组件处理文件上传步骤：
            log.debug ( "fileUpload02[]创建一个DiskFileItemFactory工厂" );
            DiskFileItemFactory factory = new DiskFileItemFactory ();
            log.debug ( "fileUpload02[]创建一个文件上传解析器" );
            ServletFileUpload upload = new ServletFileUpload ( factory );
            upload.setFileSizeMax ( - 1 );//设置上传文件最大大小
            //解决上传文件名的中文乱码
            upload.setHeaderEncoding ( "UTF-8" );
            //3、判断提交上来的数据是否是上传表单的数据
            if ( ! ServletFileUpload.isMultipartContent ( request ) ) {
                //按照传统方式获取数据
                log.error ( "fileUpload02[]没有文件上传" );
                resDto.setResult ( 0 );
                resDto.setMsg ( "没有文件上传" );
                resDto.setResultTime ( DateUtil.getCurDateStrMiao_ () );
                return resDto.dto2map ();
            }
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            log.debug ( "fileUpload02[]使用ServletFileUpload解析器解析上传数据" );
            List < FileItem > list = upload.parseRequest ( request );
            InputStream in = null;

            //上传数据日志整理
            JSONObject jsonObject = new JSONObject ();
            int flagId = 0;
            int flagequipmentId = 0;
            for ( FileItem item : list ) {
                //如果fileitem中封装的是普通输入项的数据
                if ( item.isFormField () ) {
                    String name = item.getFieldName ();
                    //解决普通输入项的数据的中文乱码问题
                    String value = item.getString ( "UTF-8" );
                    //value = new String(value.getBytes("iso8859-1"),"UTF-8");
                    jsonObject.put ( name.trim () , value.trim () );
                    if ( "id".equals ( name ) ) {//设备id
                        if ( StringUtils.isBlank ( value ) ) {
                            log.error ( "上传id错误,id为空" );
                            resDto.setMsg ( "上传id错误,id为空" );
                            resDto.setResult ( 1 );
                            resDto.setResultTime ( DateUtil.getCurDateStrMiao_ () );
                            return resDto.dto2map ();
                        }

                        model.setEquipmentId ( value.trim () );

                        flagId = 1;
                    } else if ( "voltage".equals ( name ) ) {
                        model.setVoltage ( value );
                    } else if ( "temp".equals ( name ) ) {
                        model.setTemp ( value );
                    } else if ( "humi".equals ( name ) ) {
                        model.setHumi ( value );
                    } else if ( "equipmentId".equals ( name ) ) {//设备类型
                        flagequipmentId = 1;
                        if ( StringUtils.isEmpty ( value ) ) {
                            log.error ( "上传equipmentId错误,equipmentId为null" );
                            resDto.setMsg ( "上传equipmentId错误,equipmentId为null" );
                            resDto.setResult ( 1 );
                            resDto.setResultTime ( DateUtil.getCurDateStrMiao_ () );
                            return resDto.dto2map ();
                        }

                        if ( StringUtils.isBlank ( value.trim () ) ) {
                            log.error ( "上传equipmentId错误,equipmentId为空字符串" );
                            resDto.setMsg ( "上传equipmentId错误,equipmentId为空字符串" );
                            resDto.setResult ( 1 );
                            resDto.setResultTime ( DateUtil.getCurDateStrMiao_ () );
                            return resDto.dto2map ();
                        }

                        if ( ! StringUtils.isNumeric ( value.trim () ) ) {
                            log.error ( "上传equipmentId错误,equipmentId不为数字" );
                            resDto.setMsg ( "上传equipmentId错误,equipmentId不为数字" );
                            resDto.setResult ( 1 );
                            resDto.setResultTime ( DateUtil.getCurDateStrMiao_ () );
                            return resDto.dto2map ();
                        }

                        Integer equipmentType = Integer.valueOf ( value.trim () );
                        if ( EquipmentTypeEnum.isNotInEnum ( equipmentType ) ) {
                            log.error ( "上传equipmentId错误,equipmentId不存在" );
                            resDto.setMsg ( "上传equipmentId错误,equipmentId不存在" );
                            resDto.setResult ( 1 );
                            resDto.setResultTime ( DateUtil.getCurDateStrMiao_ () );
                            return resDto.dto2map ();
                        }

                        model.setEquipmentType ( equipmentType );
                    }
                } else {//如果fileitem中封装的是上传文件
                    //得到上传的文件名称
                    jsonObject.put ( "文件名" , item.getName ().trim () );
                    if ( StringUtils.isBlank ( item.getName () ) ) {
                        log.error ( "fileUpload02[]上传的文件名称为空" );
                        resDto.setMsg ( "上传的文件名称为空" );
                        resDto.setResult ( 1 );
                        resDto.setResultTime ( DateUtil.getCurDateStrMiao_ () );
                        return resDto.dto2map ();
                    } else {
                        model.setOldFileName ( item.getName ().trim () );
                        log.info ( "fileUpload02[]上传的文件名称:" + item.getName () );

                        in = item.getInputStream ();
                        item.delete ();
                    }
                }
            }

            if ( flagequipmentId == 0 ) {
                log.error ( "未上传equipmentId字段" );
                resDto.setMsg ( "未上传equipmentId字段" );
                resDto.setResult ( 1 );
                resDto.setResultTime ( DateUtil.getCurDateStrMiao_ () );
                return resDto.dto2map ();
            }

            if ( flagId == 0 ) {
                log.error ( "未上传id字段" );
                resDto.setMsg ( "未上传id字段" );
                resDto.setResult ( 1 );
                resDto.setResultTime ( DateUtil.getCurDateStrMiao_ () );
                return resDto.dto2map ();
            }

            equipmentDO = equipmentService.getByEquIdandType ( model.getEquipmentId () , model.getEquipmentType () );
            if ( equipmentDO == null ) {
                log.error ( "UploadPhotoController[]fileUpload02[]该设备不存在[]上传id:{}，上传类型:{}" , model.getEquipmentId () , model.getEquipmentType () );
                resDto.setMsg ( "该设备不存在" );
                resDto.setResult ( 1 );
                resDto.setResultTime ( DateUtil.getCurDateStrMiao_ () );
                return resDto.dto2map ();
            }

            log.info ( "上传数据:{}" , jsonObject.toJSONString () );


            path = equipmentDO.getPicDir ();
            String filename = model.getOldFileName ();
            filename = filename.substring ( filename.lastIndexOf ( "\\" ) + 1 );
            String[] strs = filename.split ( "\\." );
            String extendName = strs[ strs.length - 1 ];

            //设备id-设备类型-时间-5位随机数
            String newfilename = equipmentDO.getEquipmentId () +
                    "-" + equipmentDO.getEquipmentType () +
                    "-" + DateUtil.getCurDateStrHaomiao () +
                    "-" + UuidUtil.get32UUID ().substring ( 0 , 5 ) +
                    "." + extendName;
            model.setFilePath ( ( path + newfilename ).trim () );


            log.info ( "fileUpload02[]上传的文件路径:" + model.getFilePath () );

            //创建一个文件输出流
            FileOutputStream out = new FileOutputStream ( model.getFilePath () );
            //创建一个缓冲区
            byte buffer[] = new byte[ 1024 ];
            //判断输入流中的数据是否已经读完的标识
            int len = 0;
            //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
            while ( ( len = in.read ( buffer ) ) > 0 ) {
                //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                out.write ( buffer , 0 , len );
            }
            //关闭输入流
            in.close ();
            //关闭输出流
            out.close ();
            //删除处理文件上传时生成的临时文件

            log.info ( "fileUpload02[]文件上传成功！" );

            //入库
            Boolean aBoolean = service.addPhoto ( model );
            if ( ! aBoolean ) {
                log.error ( "fileUpload02[]内部错误:" + "入库失败" );
            } else {
                log.info ( "fileUpload02[]入库成功" );

            }
        } catch ( Exception e ) {
            log.error ( "fileUpload02[]内部错误:" + e.getMessage () );
            resDto.setMsg ( "内部错误" );
            resDto.setResult ( 1 );
            resDto.setResultTime ( DateUtil.getCurDateStrMiao_ () );
            return resDto.dto2map ();
        }


        //返回成功结果
        resDto.setResult ( 0 );
        resDto.setMsg ( "上传成功" );
        resDto.setInversionSwitch ( equipmentDO.getInversionSwitch () );
        resDto.setUploadPeriod ( equipmentDO.getUploadPeriod ().intValue () );
        resDto.setResultTime ( DateUtil.getCurDateStrMiao_ () );
        log.info ( "fileUpload02[]离开fileUpload02方法" );
        return resDto.dto2map ();
    }

    @RequestMapping (method = RequestMethod.POST ,value = "/getPhoto.do" )
    public Map getPhoto (Integer equipType) {
        if (equipType == null) {
            GetPhotoResDto resDto = new GetPhotoResDto ();
            resDto.setResult ( 0 );
            resDto.setMsg ( "上传设备类型为空" );
            log.error("UploadPhotoController[]getPhoto[]上传设备类型为空");

            resDto.setLists ( null );
            return resDto.dto2map ();
        }

        if (EquipmentTypeEnum.isNotInEnum ( equipType )) {
            GetPhotoResDto resDto = new GetPhotoResDto ();
            resDto.setResult ( 0 );
            resDto.setMsg ( "上传设备类型不存在" );
            log.error("UploadPhotoController[]getPhoto[]上传设备类型不存在[]equipType:{}",equipType);

            resDto.setLists ( null );
            return resDto.dto2map ();
        }

        GetPhotoResDto resDto = new GetPhotoResDto ();
        resDto.setResult ( 0 );
        resDto.setMsg ( "调用成功" );
        resDto.setLists ( null );

        List < PictureVO > vos = service.getPhoto (EquipmentTypeEnum.fromNumber ( equipType ));

        resDto.setLists ( vos );
        return resDto.dto2map ();
    }
}

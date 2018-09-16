package com.yasi.web;

import com.common.base.BaseController;
import com.yasi.bo.YascmfAreasBo;
import com.yasi.vo.YascmfAreas;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
@Controller
@Scope("prototype")
@RequestMapping("YascmfAreasController/")
public class YascmfAreasController extends BaseController {
    private static Logger logger = Logger.getLogger(YascmfAreasController.class);

    /**
     * 注入业务接口层
     **/
    @Autowired
    private YascmfAreasBo yascmfAreasbo;

    /**
     * 定义参数
     **/
    private YascmfAreas yascmfAreas;

    @ModelAttribute
    public void setParaVal(YascmfAreas yascmfAreas) {
        this.yascmfAreas = yascmfAreas;
//		System.out.println(yascmfAreas);
//		System.out.println(JSON.toJSONString(yascmfAreas).toString());
    }

    @RequestMapping("test.do")
    public void test() {
        System.out.println("YascmfAreasController test!");
        setSuccess("test成功");
    }

    @RequestMapping("findYascmfAreasByPojo.do")
    public void findYascmfAreasByPojo() {
        List<YascmfAreas> resarr = yascmfAreasbo.findYascmfAreasByPojo(yascmfAreas);
        if (resarr == null || resarr.size() == 0) {
            setFailure("未找到YascmfAreas");
            return;
        }
        setAjaxObject(resarr);
    }

    @RequestMapping("findYascmfAreasCity.do")
    public void findYascmfAreasCity() {
        List<String> resarr = yascmfAreasbo.findYascmfAreasCity();
//		System.out.println(JSON.toJSONString(rearr).toString());
//		CheckUtil.Look(resarr);
        if (resarr == null || resarr.size() == 0) {
            setFailure("未找到YascmfAreasCity");
            return;
        }
        setAjaxObject(resarr);
    }

}

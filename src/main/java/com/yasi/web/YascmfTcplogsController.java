package com.yasi.web;

import com.common.base.BaseController;
import com.common.util.DateUtil;
import com.yasi.bo.YascmfDatasBo;
import com.yasi.bo.YascmfTcplogsBo;
import com.yasi.vo.YascmfTcplogs;
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
@RequestMapping("/YascmfTcplogsController")
public class YascmfTcplogsController extends BaseController {

    /**
     * 注入业务接口层
     **/
    @Autowired
    private YascmfTcplogsBo yascmfTcplogsBo;

    @Autowired
    private YascmfDatasBo yascmfDatasBo;

    /**
     * 定义参数
     **/
    private YascmfTcplogs yascmfTcplogs;

    public YascmfTcplogsBo getYascmfTcplogsBo() {
        return yascmfTcplogsBo;
    }

    public YascmfDatasBo getYascmfDatasBo() {
        return yascmfDatasBo;
    }

    @ModelAttribute
    public void setParaVal(YascmfTcplogs yascmfTcplogs) {
        this.yascmfTcplogs = yascmfTcplogs;
    }

    @RequestMapping("/findYascmfTcplogsByPojo.do")
    public void findYascmfTcplogsByPojo() {
        List<YascmfTcplogs> result = yascmfTcplogsBo.findYascmfTcplogsByPojo(yascmfTcplogs);
        if (result == null || result.size() == 0) {
            setFailure("未找到数据");
            return;
        }
        setAjaxObject(result);
    }

    @RequestMapping("/insertYascmfTcplogs.do")
    public void insertYascmfTcplogs() {
        yascmfTcplogs.setDate(DateUtil.getCurDateStrMiao_());
        boolean res = yascmfDatasBo.processTCPDatas(yascmfTcplogs.getLog());
        if (res) {

            //合法数据
            yascmfTcplogs.setState(1);
            yascmfTcplogsBo.insertYascmfTcplogs(yascmfTcplogs);
            setAjaxMsg("legal log");
        } else {

            //非法数据
            yascmfTcplogs.setState(0);
            yascmfTcplogsBo.insertYascmfTcplogs(yascmfTcplogs);
            setAjaxMsg("illegal log");
        }
    }
}

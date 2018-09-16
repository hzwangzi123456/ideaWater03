package com.yasi.web;

import com.alibaba.fastjson.JSON;
import com.common.base.BaseController;
import com.yasi.bean.GeoCoordBean;
import com.yasi.bean.ShippingBean;
import com.yasi.bo.GenericService;
import com.yasi.vo.Customer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangzi
 * @date 18/5/10 下午11:42.
 */
@Controller
@Scope("prototype")
@RequestMapping("CustomerController/")
public class CustomerController extends BaseController {
    private static Logger logger = Logger.getLogger(CustomerController.class);

    /**
     * 注入业务接口层
     **/
    @Autowired
    private GenericService<Customer> genericService;

    private final BigDecimal DEFAULT_X_AXIS = new BigDecimal(119.736695);
    private final BigDecimal DEFAULT_Y_AXIS = new BigDecimal(30.261491);
    /**
     * 定义参数
     **/
    private Customer customer;

    public GenericService<Customer> getGenericService() {
        return genericService;
    }

    @ModelAttribute
    public void setParaVal(Customer customer) {
        this.customer = customer;
        System.out.println(JSON.toJSONString(customer).toString());
    }

    //localhost:8060/ideaWater02/CustomerController/findByPojo.do
    @RequestMapping("/findByPojo.do")
    public void findDatasByPojo() {
        try {
            List<Customer> list = genericService.findByPojo(customer);
            setAjaxObject(list);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
    }

    //localhost:8060/ideaWater02/CustomerController/shippingMap.do
    @RequestMapping("/shippingMap")
    @ResponseBody
    public Map<String, Object> shippingMap() {
        List<GeoCoordBean> geoCoordList = new ArrayList<>();
        List<ShippingBean> shippingLinesList = new ArrayList<>();
        List<Customer> list = genericService.findByPojo(customer);
        Map<String, Object> resultMap = new HashMap<>();
        int i = 0;
        for (Customer customer : list) {
            i++;
            if (i >= 20) {
                break;
            }
            geoCoordList.add(new GeoCoordBean(customer.getShortName(), customer.getXaxis(), customer.getYaxis(), BigDecimal.valueOf(Math.random() * 50)));
            shippingLinesList.add(new ShippingBean("配送站", customer.getShortName(), DEFAULT_X_AXIS, DEFAULT_Y_AXIS,
                    customer.getXaxis(), customer.getYaxis()));
        }
        //官方样例里需要多加一个起始站的数据
        geoCoordList.add(new GeoCoordBean("配送站", DEFAULT_X_AXIS, DEFAULT_Y_AXIS, BigDecimal.valueOf(100)));
        resultMap.put("geoCoordList", geoCoordList);
        resultMap.put("shippingLinesList", shippingLinesList);
        return resultMap;
    }
}

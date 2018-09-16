package com.yasi.web;

import com.alibaba.fastjson.JSON;
import com.common.base.BaseController;
import com.yasi.bo.YascmfInstrumentsBo;
import com.yasi.vo.YascmfInstruments;
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
@RequestMapping("/YascmfInstrumentsController")
public class YascmfInstrumentsController extends BaseController {
	private static Logger logger = Logger.getLogger(YascmfInstrumentsController.class); 
	
	/**注入业务接口层**/
	@Autowired
	private YascmfInstrumentsBo yascmfInstrumentsBo;
	
	/**定义参数**/
	private YascmfInstruments yascmfInstruments;
	
	@ModelAttribute  
    public void setParaVal(YascmfInstruments yascmfInstruments){
		this.yascmfInstruments = yascmfInstruments;
//		System.out.println(yascmfAreas);
		System.out.println(JSON.toJSONString(yascmfInstruments).toString());
    } 
	
	@RequestMapping("/findYascmfInstrumentsByPojo.do")
	public void findYascmfInstrumentsByPojo(){
		List<YascmfInstruments> result = yascmfInstrumentsBo.findYascmfInstrumentsByPojo(yascmfInstruments);
		if(result != null && result.size() != 0) {
//			System.out.println(DateUtil.getCurDateStrHaomiao_());
			setAjaxObject(result);
			return;
		}
		setFailure("未找到YascmfInstruments");
		return;
	}
	
}

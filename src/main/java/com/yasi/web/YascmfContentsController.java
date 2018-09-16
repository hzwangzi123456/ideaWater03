package com.yasi.web;

import com.alibaba.fastjson.JSON;
import com.common.base.BaseController;
import com.yasi.bo.YascmfContentsBo;
import com.yasi.vo.YascmfContents;
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
@RequestMapping("/YascmfContentsController")
public class YascmfContentsController extends BaseController{
	private static Logger logger = Logger.getLogger(YascmfContentsController.class); 
	
	/**注入业务接口层**/
	@Autowired
	private YascmfContentsBo yascmfContentsBo;
	
	/**定义参数**/
	private YascmfContents yascmfContents;
	
	@ModelAttribute  
    public void setParaVal(YascmfContents yascmfContents){
		this.yascmfContents = yascmfContents;
//		System.out.println(yascmfAreas);
		System.out.println(JSON.toJSONString(yascmfContents).toString());
    } 
	
	@RequestMapping("/findYascmfContentsByPojo.do")
	public void findYascmfContentsByPojo(){
		List<YascmfContents> resultarr = null;
		resultarr = yascmfContentsBo.findYascmfContentsByPojo(yascmfContents);
		if(resultarr != null && resultarr.size() != 0){
			System.out.println(JSON.toJSONString(resultarr).toString());
		}
	}
}

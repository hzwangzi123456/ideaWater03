package com.yasi.web;

import com.common.base.BaseController;
import com.yasi.bo.AuthorityBo;
import com.yasi.vo.Authority;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
@Controller
@Scope("prototype")
@RequestMapping("AuthorityController/")
public class AuthorityController extends BaseController {
	private static Logger logger = Logger.getLogger(AuthorityController.class);

	/**注入业务接口层**/
	@Autowired
	private AuthorityBo authorityBo;
	
	/**定义参数**/
	private Authority authority;
	
	@ModelAttribute  
    public void setParaVal(Authority authority){
		this.authority = authority;
//		System.out.println(JSON.toJSONString(authority).toString());
    } 
	
	@RequestMapping("findAuthorityByPojo.do")
	public void findAuthorityByPojo() {
		ArrayList<Authority> arr = (ArrayList<Authority>)authorityBo.findAuthorityByPojo(authority);
		if(arr == null || arr.size() ==0) {
			setFailure("未找到数据");
			return;
		}
		setAjaxObject(arr);
	}
}

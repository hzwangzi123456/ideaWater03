package com.common.util;

import java.io.Serializable;
/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
public class ResultUtil implements Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	private boolean isLogined;
	private boolean isHaveRight; 
	private boolean isSucced; 
	private String msg;
	
	public ResultUtil(){}
	
	public ResultUtil(boolean isLogined,boolean isHaveRight,boolean isSucced,String msg){
		this.isLogined = isLogined;
		this.isHaveRight = isHaveRight;
		this.isSucced = isSucced; 
		this.msg = msg;
	}
	
	public boolean getIsLogined() {
		return isLogined;
	}
	public void setIsLogined(boolean isLogined) {
		this.isLogined = isLogined;
	}
	public boolean getIsHaveRight() {
		return isHaveRight;
	}

	public void setIsHaveRight(boolean isHaveRight) {
		this.isHaveRight = isHaveRight;
	}
	public boolean getIsSucced() {
		return isSucced;
	}
	public void setIsSucced(boolean isSucced) {
		this.isSucced = isSucced;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	} 
	
}

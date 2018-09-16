package com.yasi.vo;

/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
public class YascmfAreas {
	
	private String prefix;
	private String province;
	private String city;
	private String county;
	private String extra;
	private String createdAt;
	private String updatedAt;
	private Integer num;
	
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@Override
	public String toString() {
		String str = "";
		str += "prefix:" + prefix;
		str += " province:" + province;
		str += " city:" + city;
		str += " county:" + county ;
		str += " extra:" + extra ;
		str += " createdAt:" + createdAt;
		str += " updatedAt:" + updatedAt ;
		return str;
	}
	
}

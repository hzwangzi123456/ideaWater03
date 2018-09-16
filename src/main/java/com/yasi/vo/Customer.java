package com.yasi.vo;

import java.math.BigDecimal;
import java.util.Date;

public class Customer {
	/**顾客id*/
	private String id;
	/**编码*/
	private String no;
	/**老客户编码*/
	private String oldNo;
	/**名称*/
	private String name;
	/**客户简称*/
	private String shortName;
	/**客户简码*/
	private String shortNo;
	/**省份名*/
	private String province;
	/**市名*/
	private String city;
	/**区名*/
	private String area;
	/**地址*/
	private String address;
	/**分类*/
	private String customerTypeId;
	/**分拣分组*/
	private String workGroup;
	/**配送分组*/
	private String customerTransGroupId;
	/**X-地图坐标*/
	private BigDecimal xaxis;
	/**Y-地图坐标*/
	private BigDecimal yaxis;
	/**租户编码*/
	private String saasNo;

	private String creator;
	private Date createdAt;
	private String updator;
	private Date updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getNo() { return no; }
	public void setNo(String no) { this.no = no; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getProvince() { return province; }
	public void setProvince(String province) { this.province = province; }
	public String getCity() { return city; }
	public void setCity(String city) { this.city = city; }
	public String getArea() { return area; }
	public void setArea(String area) { this.area = area; }
	public String getAddress() { return address; }
	public void setAddress(String address) { this.address = address; }
	public String getCustomerTypeId() { return customerTypeId; }
	public void setCustomerTypeId(String customerTypeId) { this.customerTypeId = customerTypeId; }
	public String getWorkGroup() { return workGroup; }
	public void setWorkGroup(String workGroup) { this.workGroup = workGroup; }
	public String getCustomerTransGroupId() { return customerTransGroupId; }
	public void setCustomerTransGroupId(String customerTransGroupId) { this.customerTransGroupId = customerTransGroupId; }
	public BigDecimal getXaxis() { return xaxis; }
	public void setXaxis(BigDecimal xaxis) { this.xaxis = xaxis; }
	public BigDecimal getYaxis() { return yaxis; }
	public void setYaxis(BigDecimal yaxis) { this.yaxis = yaxis; }
	public String getSaasNo() { return saasNo; }
	public void setSaasNo(String saasNo) { this.saasNo = saasNo; }

	public String getOldNo() {
		return oldNo;
	}

	public void setOldNo(String oldNo) {
		this.oldNo = oldNo;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getShortNo() {
		return shortNo;
	}

	public void setShortNo(String shortNo) {
		this.shortNo = shortNo;
	}
}

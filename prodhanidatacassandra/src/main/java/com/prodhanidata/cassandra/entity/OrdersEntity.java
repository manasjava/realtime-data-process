package com.prodhanidata.cassandra.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.web.JsonPath;

@Table("orders")
public class OrdersEntity {

	@PrimaryKey
	private OrderKey orderKey;
	private String userId;
    @Column(value="mobile")
	private String mobileNumber;
	private String name;
	private String uiExperienceLabel;
	private List<UserClickURL> clickurl;
	private Date createdOn;
	private Date lastModified;

	public OrderKey getOrderKey() {
		return orderKey;
	}

	public void setOrderKey(OrderKey orderKey) {
		this.orderKey = orderKey;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUiExperienceLabel() {
		return uiExperienceLabel;
	}

	public void setUiExperienceLabel(String uiExperienceLabel) {
		this.uiExperienceLabel = uiExperienceLabel;
	}

	public List<UserClickURL> getClickurl() {
		return clickurl;
	}

	public void setClickurl(List<UserClickURL> clickurl) {
		this.clickurl = clickurl;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	

	@Override
	public String toString() {
		return "OrdersEntity [orderKey=" + orderKey + ", userId=" + userId + ", mobileNumber=" + mobileNumber
				+ ", name=" + name + ", uiExperienceLabel=" + uiExperienceLabel + ", clickurl=" + clickurl
				+ ", createdOn=" + createdOn + ", lastModified=" + lastModified + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderKey == null) ? 0 : orderKey.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdersEntity other = (OrdersEntity) obj;
		if (orderKey == null) {
			if (other.orderKey != null)
				return false;
		} else if (!orderKey.equals(other.orderKey))
			return false;
		return true;
	}
	
	

}

package com.prodhanidata.cassandra.entity;

import java.util.Date;
import java.util.Map;

import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@UserDefinedType(value = "userclickurl")
public class UserClickURL {

	private String url;
	private String responseStatus;
	private Date requestTime;
	private Date responseTime;
	private String requestMethodType;
	private String requestAction;
	private Map<String, String> userClickURLParam;
	private Date createdOn;
	private Date lastmodifed;
	
	
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}
	public Date getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}
	public Date getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}
	public String getRequestMethodType() {
		return requestMethodType;
	}
	public void setRequestMethodType(String requestMethodType) {
		this.requestMethodType = requestMethodType;
	}
	public String getRequestAction() {
		return requestAction;
	}
	public void setRequestAction(String requestAction) {
		this.requestAction = requestAction;
	}
	public Map<String, String> getUserClickURLParam() {
		return userClickURLParam;
	}
	public void setUserClickURLParam(Map<String, String> userClickURLParam) {
		this.userClickURLParam = userClickURLParam;
	}
	
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getLastmodifed() {
		return lastmodifed;
	}
	public void setLastmodifed(Date lastmodifed) {
		this.lastmodifed = lastmodifed;
	}
	@Override
	public String toString() {
		return "UserClickURL [url=" + url + ", responseStatus=" + responseStatus + ", requestTime=" + requestTime
				+ ", responseTime=" + responseTime + ", requestMethodType=" + requestMethodType + ", requestAction="
				+ requestAction + ", userClickURLParam=" + userClickURLParam + ", createdOn=" + createdOn
				+ ", lastmodifed=" + lastmodifed + "]";
	}
	
	
	
	
	
	
}

package com.capitalone.socialApiFb.model;

public class ApiRequestMessage 
{
	String nativeurl;
	String url;
	String platform;
	String acesstoken;
	String requestType;
	String fields;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getAcesstoken() {
		return acesstoken;
	}
	public void setAcesstoken(String acesstoken) {
		this.acesstoken = acesstoken;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
	public ApiRequestMessage() {
		super();
	}
	public String getNativeurl() {
		return nativeurl;
	}
	public void setNativeurl(String nativeurl) {
		this.nativeurl = nativeurl;
	}
	public ApiRequestMessage(String nativeurl, String url, String platform,
			String acesstoken, String requestType, String fields) {
		super();
		this.nativeurl = nativeurl;
		this.url = url;
		this.platform = platform;
		this.acesstoken = acesstoken;
		this.requestType = requestType;
		this.fields = fields;
	}
	

}

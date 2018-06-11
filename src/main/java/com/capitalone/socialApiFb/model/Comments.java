package com.capitalone.socialApiFb.model;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Comments 
{
	ArrayList<Comment>data;
	private JsonNode paging;
	public ArrayList<Comment> getData() {
		return data;
	}
	public void setData(ArrayList<Comment> data) {
		this.data = data;
	}
	public JsonNode getPaging() {
		return paging;
	}
	public void setPaging(JsonNode paging) {
		this.paging = paging;
	}
	@Override
	public String toString() {
		return "Comments [data=" + data + ", paging=" + paging + "]";
	}
	public Comments() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Comments(ArrayList<Comment> data, JsonNode paging) {
		super();
		this.data = data;
		this.paging = paging;
	}
	
	
	
}

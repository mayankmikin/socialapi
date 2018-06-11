package com.capitalone.socialApiFb.model;

import java.util.ArrayList;

public class PostAllData {
	ArrayList<PagePostImpression> allimpressionsData=new ArrayList<>();
	ArrayList<Comment>allcommentsData=new ArrayList<>();
	public ArrayList<PagePostImpression> getAllimpressionsData() {
		return allimpressionsData;
	}
	public void setAllimpressionsData(
			ArrayList<PagePostImpression> allimpressionsData) {
		this.allimpressionsData = allimpressionsData;
	}
	public ArrayList<Comment> getAllcommentsData() {
		return allcommentsData;
	}
	public void setAllcommentsData(ArrayList<Comment> allcommentsData) {
		this.allcommentsData = allcommentsData;
	}
	@Override
	public String toString() {
		return "PostAllData [allimpressionsData=" + allimpressionsData
				+ ", allcommentsData=" + allcommentsData + "]";
	}
	public PostAllData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PostAllData(ArrayList<PagePostImpression> allimpressionsData,
			ArrayList<Comment> allcommentsData) {
		super();
		this.allimpressionsData = allimpressionsData;
		this.allcommentsData = allcommentsData;
	}
	
	
}

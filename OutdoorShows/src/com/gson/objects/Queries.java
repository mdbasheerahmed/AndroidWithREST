package com.gson.objects;

import java.util.List;

import com.google.gson.annotations.SerializedName;



public class Queries {
	
	@SerializedName("nextPage")
	List<NextPage> nextPage;
	
	@SerializedName("request")
	List<Request> request;

	public List<NextPage> getNextPage() {
		return nextPage;
	}

	public void setNextPage(List<NextPage> nextPage) {
		this.nextPage = nextPage;
	}

	public List<Request> getRequest() {
		return request;
	}

	public void setRequest(List<Request> request) {
		this.request = request;
	}
	
	
}

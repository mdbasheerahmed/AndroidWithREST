package com.gson.model;

import com.google.gson.annotations.SerializedName;

public class MainSearchResponse {
	
	@SerializedName("SearchResponse")
	public SearchResponse searchResponse;

	public SearchResponse getSearchResponse() {
		return searchResponse;
	}

	public void setSearchResponse(SearchResponse searchResponse) {
		this.searchResponse = searchResponse;
	}
	

}

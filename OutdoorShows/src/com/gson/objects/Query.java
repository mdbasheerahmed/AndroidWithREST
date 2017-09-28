package com.gson.objects;

import com.google.gson.annotations.SerializedName;

public class Query {

	@SerializedName("SearchTerms")
	public String searchTerms;

	public String getSearchTerms() {
		return searchTerms;
	}

	public void setSearchTerms(String searchTerms) {
		this.searchTerms = searchTerms;
	}
}

package com.gson.objects;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class GoogleMainSearchResult {
	
	
	public String kind;
	
	@SerializedName("url")
	public Url url;
	
	@SerializedName("queries")
	public Queries queries;
	
	public Queries getQueries() {
		return queries;
	}

	public void setQueries(Queries queries) {
		this.queries = queries;
	}
	
	public void setKind(String kind) {
		this.kind = kind;
	}

	@SerializedName("context")
	public Context context;
	
	@SerializedName("searchInformation")
	public SearchInformation searchInformation;
	
	@SerializedName("items")
	List<Items> items;

	public String getKind() {
		return kind;
	}

	public Url getUrl() {
		return url;
	}

	public void setUrl(Url url) {
		this.url = url;
	}

	

	public SearchInformation getSearchInformation() {
		return searchInformation;
	}

	public void setSearchInformation(SearchInformation searchInformation) {
		this.searchInformation = searchInformation;
	}

	public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}
	
	
	
	
	
	

}

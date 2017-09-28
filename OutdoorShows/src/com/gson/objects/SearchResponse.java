package com.gson.objects;

import com.google.gson.annotations.SerializedName;

public class SearchResponse {
	
	@SerializedName("Query")
	public Query query;
	
	@SerializedName("Version")
	public String version;
	
	@SerializedName("Web")
	public Web web;

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Web getWeb() {
		return web;
	}

	public void setWeb(Web web) {
		this.web = web;
	}
	
	
	

}

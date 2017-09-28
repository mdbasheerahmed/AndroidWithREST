package com.gson.model;

import com.google.gson.annotations.SerializedName;

public class Results {
	
	@SerializedName("Title")
	public String title;
	
	@SerializedName("Description")
	public String description;
	
	@SerializedName("Url")
	public String url;
	
	@SerializedName("CacheUrl")
	public String cacheUrl;
	
	@SerializedName("DisplayUrl")
	public String displayUrl;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCacheUrl() {
		return cacheUrl;
	}

	public void setCacheUrl(String cacheUrl) {
		this.cacheUrl = cacheUrl;
	}

	public String getDisplayUrl() {
		return displayUrl;
	}

	public void setDisplayUrl(String displayUrl) {
		this.displayUrl = displayUrl;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	@SerializedName("DateTime")
	public String dateTime;

}

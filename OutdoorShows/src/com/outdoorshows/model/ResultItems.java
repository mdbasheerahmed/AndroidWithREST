package com.outdoorshows.model;

import java.io.Serializable;

public class ResultItems implements Serializable{
	
	private String title;
	private String description;
	private String url;
	private String imageUrl;
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
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
	
	@Override
		public String toString() {
		 StringBuilder builder = new StringBuilder();
		 builder.append(" [Title=");
		 builder.append(title);
		 builder.append("]");
		 return builder.toString();
		}


	

}

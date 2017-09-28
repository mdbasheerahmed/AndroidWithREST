package com.gson.objects;

public class CseThumbnail {
	
	public static final String SIZE_ORIGINAL = "original";
    public static final String SIZE_MID = "mid";
    public static final String SIZE_COVER = "cover";
    public static final String SIZE_THUMB = "thumb";
 
    public static final String TYPE_PROFILE = "profile";
    public static final String TYPE_POSTER = "poster";
	
	public String width;
    public String height;
    public String src;
    public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	

}

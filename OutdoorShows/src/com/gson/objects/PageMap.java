package com.gson.objects;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class PageMap {
	
	public List<CseThumbnail> getCseThumbnail() {
		return cseThumbnail;
	}

	public void setCseThumbnail(List<CseThumbnail> cseThumbnail) {
		this.cseThumbnail = cseThumbnail;
	}

	public List<CseImage> getCseImage() {
		return cseImage;
	}

	public void setCseImage(List<CseImage> cseImage) {
		this.cseImage = cseImage;
	}

	@SerializedName("cse_thumbnail")
	List<CseThumbnail> cseThumbnail;

	@SerializedName("cse_image")
	List<CseImage> cseImage;
}

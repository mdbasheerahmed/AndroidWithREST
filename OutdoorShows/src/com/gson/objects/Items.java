package com.gson.objects;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Items {
	
	public String kind;
	public String title;
	public String htmlTitle;
	public String link;
	public String displayLink;
	public String snippet;
	public String htmlSnippet;
	public String cacheId;
	public String formattedUrl;
	public String htmlFormattedUrl;
	
	public String retrieveThumbnail() {
		if(pagemap!= null && pagemap.getCseThumbnail() != null && pagemap.getCseThumbnail().size() !=0){
			
			List<CseThumbnail> tempThumbnail = pagemap.getCseThumbnail();
			for (CseThumbnail thumbnail : tempThumbnail) {
                
                    return thumbnail.getSrc();
            }
		}
		return null;
	} 

		
	
	@SerializedName("pagemap")
	public PageMap pagemap;
	
	public PageMap getPagemap() {
		return pagemap;
	}

	public void setPagemap(PageMap pagemap) {
		this.pagemap = pagemap;
	}
	
	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHtmlTitle() {
		return htmlTitle;
	}

	public void setHtmlTitle(String htmlTitle) {
		this.htmlTitle = htmlTitle;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDisplayLink() {
		return displayLink;
	}

	public void setDisplayLink(String displayLink) {
		this.displayLink = displayLink;
	}

	public String getSnippet() {
		return snippet;
	}

	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}

	public String getHtmlSnippet() {
		return htmlSnippet;
	}

	public void setHtmlSnippet(String htmlSnippet) {
		this.htmlSnippet = htmlSnippet;
	}

	public String getCacheId() {
		return cacheId;
	}

	public void setCacheId(String cacheId) {
		this.cacheId = cacheId;
	}

	public String getFormattedUrl() {
		return formattedUrl;
	}

	public void setFormattedUrl(String formattedUrl) {
		this.formattedUrl = formattedUrl;
	}

	public String getHtmlFormattedUrl() {
		return htmlFormattedUrl;
	}

	public void setHtmlFormattedUrl(String htmlFormattedUrl) {
		this.htmlFormattedUrl = htmlFormattedUrl;
	}


	

}

package com.redarc;

public abstract class BaseWeb {
	private String url;
	private String content;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public abstract String build();
		
}

package com.redarc;

import org.apache.ecs.html.Body;
import org.apache.ecs.html.Head;
import org.apache.ecs.html.Html;
import org.apache.ecs.html.Meta;

/**
 * 
 * @author EGANYAO
 *
 */
public abstract class BaseWeb {
	private String fileName;
	private String nextPage;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getNextPage() {
		return nextPage;
	}
	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}
	
	public void build(){
		Head head = new Head();
		Meta meta = new Meta();
		meta.addAttribute("http-equiv", "refresh");
		meta.addAttribute("content", Resconfig.getInstance().getRefreshTime() + ",url=" + nextPage);
		head.addElement(meta);
		head.addElement(script());
		head.addElement(style());
		
		Body body = new Body();
		body.addElement(body());
		
	    Html html = new Html();
	    html.addElement(head);
	    html.addElement(body);
	    
	    WebBuilder.writeToFile(fileName, html.toString());
	}
	
	public abstract String body();
	public abstract String style(); 
	public abstract String script();

}

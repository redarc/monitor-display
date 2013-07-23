package com.redarc;


import org.apache.ecs.html.Body;
import org.apache.ecs.html.Head;
import org.apache.ecs.html.Html;
import org.apache.ecs.xml.XML;

/**
 * 
 * @author EGANYAO
 *
 */
public class BaseWeb {
	private String fileName;
	private String style;
	private String body;
	private String script;
	
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getScript() {
		return script;
	}
	public void setScript(String script) {
		this.script = script;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public BaseWeb(){}
	
	public BaseWeb(String fileName){
		this.fileName = fileName;
		this.style = style();
		this.body = body();
		this.script = script();
		
		System.out.println(this.getClass().getName());
	}
	
	public void build(){
		Head head = new Head();
//		Meta meta = new Meta();
//		meta.addAttribute("http-equiv", "refresh");
//		head.addElement(meta);
		head.addElement(getScript());
		head.addElement(getStyle());
		
		Body body = new Body();
		body.addElement(getBody());
		
	    Html html = new Html();
	    html.addElement(head);
	    html.addElement(body);
	    
	    WebBuilder.writeToFile(fileName.concat(".html"), html.toString());
	}
	
	public String style() {
		String styleContent = new String("" +
				"div#wholePage{}" +
				"div#header{height: 0px}"+
				".track_table{" +
				"border:#000000 solid; " +
				"border-width:2px 0px 0px 2px;" +
				"width: 100%; " +
				"height: 100%" +
				"}" +
				".track_table th{" +
				"padding: 5px 5px 5px 5px;" +
				"border:#000000 solid; " +
				"border-width:0px 2px 2px 0px; " +
				"font-family: Arial; " +
				"font-size: 70px; " +
				"font-style: oblique; " +
				"font-weight: 900; " +
				"height:10%;" +
				"background: Aqua" +
				"}" +
				".track_table td{" +
				"padding: 5px 5px 5px 5px;" +
				"border:#000000 solid; " +
				"border-width:0px 2px 2px 0px; " +
				"font-family: Arial; " +
				"font-size: 24px; " +
				"font-style: normal; " +
				"font-weight: 900; " +
				"height:30%;" +
				"text-align: left;" +
				"vertical-align: middle" +
				"}" +
				".security{"+
				"background-image : url(slide0002_image001.jpg);" +
				"background-position: center;"+
				"background-repeat:no-repeat;"+
				"background-size: 1394px 1050px;"+
				"height:1050;"+
				"width:1394;"+
				"}"	+	
				".security h1{font-size: 50px}" +
				".security h2{font-size: 40px}"+
				".recUPTitle H2{color: blue;}" +
				".se_context{line-height: 42px;color : blue;}" + 
				".se_context h1{font-size: 50px}" +
				".se_context h2{font-size: 40px}" +
				".se_context p{font-size: 25px;font-family: Arial}" +
				".live-title{width: 100%; height: 100%}");

		XML style = new XML("style");
		style.setTagText(styleContent);
		return style.toString();
	}
	
	public String body(){
		return null;
	}
	
	public String script(){
		return null;
	}
	/**
	 * for HashSet to avoid duplicate element
	 */
	public boolean equals(Object o){
		boolean ret = false;
		if(this == o){
			ret = true;
		}
		if(o.getClass() == BaseWeb.class){
			BaseWeb b = (BaseWeb)o;
			ret = b.getFileName().equals(fileName);
		}
		return ret;
	}
	
	/**
	 * for HashSet to avoid duplicate element
	 */
	public int hashCode(){
		return fileName.hashCode();
	}
}

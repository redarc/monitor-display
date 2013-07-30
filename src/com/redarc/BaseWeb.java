package com.redarc;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.ecs.html.Body;
import org.apache.ecs.html.Head;
import org.apache.ecs.html.Html;
import org.apache.ecs.html.Meta;
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
	private String head;
	private String script;

	public BaseWeb(){}
	
	public BaseWeb(String fileName){
		this.fileName = fileName;
		this.style = style();
		this.body = body();
		this.script = script();
		this.head = head();
	}
	
	public void build(){
		Head head = new Head();
		head.addElement(getHead());
		head.addElement(getScript());
		head.addElement(getStyle());
		
		Body body = new Body();
		body.addElement(getBody());
		
	    Html html = new Html();
	    html.addElement(head);
	    html.addElement(body);
	    
	    writeToFile(fileName.concat(".html"), html.toString());
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
				"padding: 5px 50px 5px 100px;" +
				"border:#000000 solid; " +
				"border-width:0px 2px 2px 0px; " +
				"font-family: Arial; " +
				"font-size: 24px; " +
				"font-style: normal; " +
				"font-weight: 200; " +
				"height:30%;" +
				"text-align: left;" +
				"vertical-align: middle;" +
				"background: PaleTurquoise" +
				"}" +
				".recUPTitle {color: red;font-size: 40px}" +
				".trName {color: green}" +
				".static_info {color: blue; font-size: 40px}" +
				".statistic_count {color: red}" +
				".se_context{line-height: 42px;color : blue;;font-family: Arial}" + 
				".se_context h1{font-size: 50px;font-family: Arial}" +
				".se_context h2{font-size: 40px;font-family: Arial}" +
				".se_context p{font-size: 25px;font-family: Arial}");

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
	
	public String head(){
		Meta meta = new Meta();
		meta.addAttribute("http-equiv", "Page-Enter");
		meta.addAttribute("content", "blendTrans(duration=" + Resconfig.getInstance().getTranstime() + ",transition=12)");
		//return "<meta http-equiv='Page-Enter' content='blendTrans(duration=2,transition=12)'>";
		return meta.toString();
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
	
    private void writeToFile(String pathName, String html){
    	File file = new File(pathName);
    	BufferedWriter bw = null;
    	if(file.exists()){
    		file.delete();
        }
    	try {
    		bw = new BufferedWriter(new FileWriter(file));
    		bw.write(html);
    		bw.flush();
    	}catch (IOException e){
    		e.printStackTrace();
    		throw(new RuntimeException());
    	}finally{
    		if(null != bw){
    			try {
    				bw.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
         }
    }
    
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
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
}

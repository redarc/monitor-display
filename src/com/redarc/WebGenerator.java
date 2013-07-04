package com.redarc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ecs.html.Body;
import org.apache.ecs.html.Div;
import org.apache.ecs.html.Head;
import org.apache.ecs.html.Html;
import org.apache.ecs.html.Link;
import org.apache.ecs.html.Meta;
import org.apache.ecs.html.Script;
import org.apache.ecs.xml.XML;

public class WebGenerator {
	
	private String filename;
	private List<BaseWeb> weblist = new ArrayList<BaseWeb>();
	private List<String> swapContent = new ArrayList<String>();
	private List<String> stylelist = new ArrayList<String>();
	private List<String> scriptlist = new ArrayList<String>();
	
	public WebGenerator(String filename){
		this.filename = filename;
	}
	
	/**
	 * 
	 * @param pathName
	 * @param html
	 */
    public void genertorHtml(){
    	if(System.getProperty("os.name").contains("Windows")){
    		writeToFile(System.getProperty("user.dir") + filename, builderWeb());
    	}else{
    		writeToFile(System.getProperty("user.dir") + File.separator + filename, builderWeb());
    	}
    }

    private void writeToFile(String pathName, String html){
    	File file = new File(pathName);
    	BufferedReader br = null;
    	BufferedWriter bw = null;
    	
    	if(file.exists()){
        	//update file
        	try {
        		
        		file.delete();
        		bw = new BufferedWriter(new FileWriter(file));
   				bw.write(html);
   				bw.flush();
        		
        	}catch (IOException e){
        		e.printStackTrace();
        		throw(new RuntimeException());
        	}finally{
        		if(null != br){
        			try {
        				br.close();
        			} catch (IOException e) {
        				e.printStackTrace();
        			}
        		}
        		if(null != bw){
        			try {
        				bw.close();
        			} catch (IOException e) {
        				e.printStackTrace();
        			}
        		}
             }
        }else{
        	//create file
        	try {
        		bw = new BufferedWriter(new FileWriter(file));
        		bw.write(html);

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
    }
    private XML buildCCS(){
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
				"text-align: center;" +
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
		return style;
    } 
    
    private void addSwapWeb(String webContent){
    	swapContent.add("\"" + webContent + "\"");
    }
    
    private Script buildScript(){
    	String scriptContent = "$(function(){" +
				"$('.live-tile').liveTile({" +
				"swapBack:'html',"  +
				"mode:'carousel'," +
				"backContent: " + swapContent.toString() + "," +
				"speed: " + "'" + Resconfig.getInstance().getSwapTime() + "'" +"," +
				"initDelay: '0',"+
				"delay: " + "'" + Resconfig.getInstance().getDelayTime() + "'" +"," +
				"htmlSwap: {backIsRandom: false}" +
				"});});";
		
		//System.out.println(scriptContent);
		
		Script script = new Script(scriptContent);
		script.addAttribute("type", "text/javascript");	
		return script;
    } 
    
    private String builderWeb(){
		Link link = new Link();
		link.addAttribute("href", "../MetroJS/MetroJs.css");
		link.addAttribute("type", "text/css");
		link.addAttribute("rel", "stylesheet");
		
		Link pptlink = new Link();
		pptlink.addAttribute("href", "master03_stylesheet.css");
		pptlink.addAttribute("rel", "stylesheet");
		
		Script script = buildScript();
		XML style = buildCCS();
		
		Head head = new Head();
		Meta meta = new Meta();
		meta.addAttribute("http-equiv", "refresh");
		meta.addAttribute("content", Resconfig.getInstance().getRefreshTime());
		head.addElement(meta);
		
		head.addElement(new Script().addAttribute("src", "https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"));
		head.addElement(new Script().addAttribute("src", "https://ajax.googleapis.com/ajax/libs/jqueryui/1.10.1/jquery-ui.min.js"));
		head.addElement(new Script().addAttribute("src", "../MetroJS/MetroJs.js"));
		head.addElement(link);
		head.addElement(pptlink);
		
        head.addElement(script);
        for(String scr : scriptlist){
        	if(null != scr){
        		System.out.print("script: " + scr);
        		head.addElement(scr);
        	}
        }
        
        head.addElement(style);
        for(String sty : stylelist){
        	head.addElement(sty);
        }

        //Body
		Div liveTile = new Div();
		liveTile.setClass("live-tile");
		liveTile.addElement(new Div("Ready")).addElement(new Div("Start"));
		
		Div wholePage = new Div();
		wholePage.setID("wholePage");
		wholePage.addElement(liveTile);
		
		Body body = new Body();
		body.addElement(wholePage);
		
	    Html html = new Html();
	    html.addElement(head);
	    html.addElement(body);
	    
	    return html.toString();
    }

	public void addWeblist(BaseWeb web){
		weblist.add(web);
		for(String content : web.build()){
			addSwapWeb(content);
		}
		stylelist.add(web.style());
		scriptlist.add(web.script());
	}
}

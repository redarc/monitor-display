package com.redarc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ecs.html.Body;
import org.apache.ecs.html.Div;
import org.apache.ecs.html.H1;
import org.apache.ecs.html.H2;
import org.apache.ecs.html.Head;
import org.apache.ecs.html.Html;
import org.apache.ecs.html.Link;
import org.apache.ecs.html.Meta;
import org.apache.ecs.html.Script;
import org.apache.ecs.html.TBody;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TH;
import org.apache.ecs.html.THead;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;
import org.apache.ecs.xhtml.br;
import org.apache.ecs.xhtml.p;
import org.apache.ecs.xml.XML;

import com.redarc.lteweb.FTDailyReportWeb;

public class WebGenerator {
	
	//move to config file
	private static final LinkedHashMap<String,String> TRACK_URLS_MAP = new LinkedHashMap<String,String>(){
		private static final long serialVersionUID = 1L;
		{
			put("FT_88_4_4","https://lte-dailytest.rnd.ki.sw.ericsson.se/n/up/listtrack/?bucket=846");
			put("FT_88_4_5","https://lte-dailytest.rnd.ki.sw.ericsson.se/n/up/listtrack/?bucket=830");
			put("MT_19_10","https://lte-dailytest.rnd.ki.sw.ericsson.se/n/up/listtrack/?bucket=685");
		}
	};
	
	private static final String WEBPATH = System.getProperty("user.dir");
	//private static final String WEBPATH = "C:/Users/EGANYAO/Desktop/Web_Display/MetroTest/";
	private static final Integer DELAY_TIME = 5000;//ms
	private static final Integer SWAP_TIME = 2000;//ms
	private static final Integer REFRESH_TIME = (TRACK_URLS_MAP.size() + 50) * (DELAY_TIME + SWAP_TIME) / 1000;//s
	
	
	private static final String WEBNAME = "iPadUPStatus.html";
	private static final String LOCAL_SRV = "http://10.186.135.173/";
	private static int RECUP_MAX_NO = 3;
	private static int RECORD_MAX_NO = 4;
	private static final String FT_L23_DAILY_TEST_REPORT = "FT_L23_DAILY_REPORT.html"; 
	private static final String MT_DELIVERY_GUIDELINE = "LMR_Main_Track_delivery_guidelines.html"; 
	private static LinkedHashMap<String,String> LOCAL_WEB_PATH = new LinkedHashMap<String, String>();
	private static LinkedHashMap<String, List<RecUP>> recUP_Map = new LinkedHashMap<String, List<RecUP>>();
	
	private String filename;
	private List<String> swapContent = new ArrayList<String>();
	
	public WebGenerator(String filename){
		this.filename = filename;
	}
	
	public String build(BaseWeb web){
		return null;
	}
	
	/**
	 * 
	 * @param pathName
	 * @param html
	 */
    public void genertorHtml(){
		writeToFile(WEBPATH + File.separator + filename, builderWeb(recUP_Map));
		//writeToFile(WEBPATH + WEBNAME, builderWeb(recUP_Map));
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
				".se_context{line-height: 42px;color : blue;}" + 
				".se_context h1{font-size: 50px}" +
				".se_context h2{font-size: 40px}" +
				".se_context p{font-size: 25px;font-family: Arial}" +
				".live-title{width: 100%; height: 100%}");

		XML style = new XML("style");
		style.setTagText(styleContent);
		return style;
    } 
    
    public void addSwapWeb(String webContent){
    	swapContent.add("\"" + webContent + "\"");
    }
    
    private Script buildScript(HashMap<String, List<RecUP>> recUP_Map){
	    //BaseWeb web = new IPadWeb(IPadParser.parseIpad(););
    	BaseWeb web = new FTDailyReportWeb("xxx");
    	web.build(swapContent);
    	
		String scriptContent = "$(function(){" +
				"$('.live-tile').liveTile({" +
				"swapBack:'html',"  +
				"mode:'carousel'," +
				"backContent: " + swapContent.toString() + "," +
				"speed: " + "'" + SWAP_TIME.toString() + "'" +"," +
				"initDelay: '0',"+
				"delay: " + "'" + DELAY_TIME.toString() + "'" +"," +
				"htmlSwap: {backIsRandom: false}" +
				"});});";
		
		//System.out.println(scriptContent);
		
		Script script = new Script(scriptContent);
		script.addAttribute("type", "text/javascript");	
		return script;
    } 
    
    private String builderWeb(HashMap<String, List<RecUP>> recUP_Map){
		Link link = new Link();
		link.addAttribute("href", "../MetroJS/MetroJs.css");
		link.addAttribute("type", "text/css");
		link.addAttribute("rel", "stylesheet");
		
		Script script = buildScript(recUP_Map);
		XML style = buildCCS();
		
		Head head = new Head();
		Meta meta = new Meta();
		meta.addAttribute("http-equiv", "refresh");
		meta.addAttribute("content", REFRESH_TIME);
		head.addElement(meta);
		
		head.addElement(new Script().addAttribute("src", "https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"));
		head.addElement(new Script().addAttribute("src", "https://ajax.googleapis.com/ajax/libs/jqueryui/1.10.1/jquery-ui.min.js"));
		head.addElement(new Script().addAttribute("src", "../MetroJS/MetroJs.js"));
		head.addElement(link);
        head.addElement(script);
        head.addElement(style);
        //TODO style
        //head.addElement(excelStyle);
        //head.addElement(mtGuidelineStyle);

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
}

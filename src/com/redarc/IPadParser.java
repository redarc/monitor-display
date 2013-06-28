package com.redarc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class IPadParser {

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
	private static String excelContent;
	private static String excelStyle;
	private static String mtGuidelineStyle;
	private static String mtGuidelineContent;

	/*
	public static void main(String[] args) throws IOException, InterruptedException{
		System.out.println("iPad parse start");
		             
		//run curl
		downloadWeb();
		
		//snifer web content
		parseIpad();

	    genertorHtml();
	    System.out.println("iPad parse end");
	}
    */
	
	/**
	 * @throws IOException
	 * @author EGANYAO
	 * download all webpage by TRACK_URLS_MAP
	 * @throws InterruptedException 
	 */
	public static void downloadWeb() throws IOException, InterruptedException{
		for(String key : TRACK_URLS_MAP.keySet()){
			String cmd = "curl -o " + WEBPATH + File.separator + key + ".html" + " -u EGANYAO:Qmm123456 -k " + TRACK_URLS_MAP.get(key);
			System.out.println(cmd);
			
			Process p = Runtime.getRuntime().exec(cmd);
			StreamGobbler errGobbler = new StreamGobbler(p.getErrorStream(), "ERROR");
			StreamGobbler outGobbler = new StreamGobbler(p.getInputStream(),"OUTPUT");
			
			errGobbler.start();
			outGobbler.start();
			p.waitFor();
		}
	}
	
	private static void mappingLocalMap(){
		for(String key : TRACK_URLS_MAP.keySet()){
			LOCAL_WEB_PATH.put(key, LOCAL_SRV + key + ".html");
		}
	}
	
	//TODO refine
	private static void parseExcelWeb(){
		Document doc;
		try {
			doc = Jsoup.connect(LOCAL_SRV + FT_L23_DAILY_TEST_REPORT)
				     .data("jquery","java")
				     .userAgent("Mozilla")
				     .followRedirects(true)
				     .get();
			Element t = doc.body().child(0);
			Element s = doc.head().child(doc.head().children().size()-1);
			excelContent = t.toString().replace('\"', '\'').replace('\n', ' ').replace("¡°","\\\"").replace("¡±","\\\"");
			excelStyle = s.toString();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void parseMTGuideline(){
		Document doc;
		try {
			doc = Jsoup.connect(LOCAL_SRV + MT_DELIVERY_GUIDELINE)
				     .data("jquery","java")
				     .userAgent("Mozilla")
				     .followRedirects(true)
				     .get();
			Element t = doc.body().child(0);
			Element s = doc.head().child(doc.head().children().size()-1);
			mtGuidelineContent = t.toString().replace('\"', '\'').replace('\n', ' ').replace("¡°","\\\"").replace("¡±","\\\"").replace('\t', ' ').replace('\r',' ');
			mtGuidelineStyle = s.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void parseIpad() throws IOException{
		mappingLocalMap();
		parseExcelWeb();
		parseMTGuideline();
		for(String key : LOCAL_WEB_PATH.keySet()) {
			String url = LOCAL_WEB_PATH.get(key);
			Document doc = Jsoup.connect(url)
				     .data("jquery","java")
				     .userAgent("Mozilla")
				     .followRedirects(true)
				     .get();
			String[] remote_url_segs = TRACK_URLS_MAP.get(key).split("bucket=");
			int table_key = Integer.parseInt(remote_url_segs[remote_url_segs.length -1]); 
			
			String tableFormat = "table#up_table_%d td:nth-child(%d)";
					
			Elements up_list = doc.select(String.format(tableFormat, table_key,1));
			Elements time_list = doc.select(String.format(tableFormat, table_key,2));
			Elements isRec_list = doc.select(String.format(tableFormat, table_key,3));
			Elements comments_list = doc.select(String.format(tableFormat, table_key,7));
			
			int i = 0;
			int recCount = 0;
			List<RecUP> recUP_list = new ArrayList<RecUP>();
			for(Element rec : isRec_list){
				if(rec.text().equals("Yes")){
					RecUP recUP = upContentSniffer(up_list.get(i).text(), time_list.get(i).text(),comments_list.get(i).text(),key);
					recUP_list.add(recUP);
					recCount++;
					if(RECUP_MAX_NO == recCount){
						break;
					}
				}
				i++;
			}
			recUP_Map.put(key,recUP_list);
		}
	}
	
	private static RecUP upContentSniffer(String upName, String time, String comments,String track){
		//filter duplicate data
		Set<String> trSet = new HashSet<String>();
		Set<String> wpSet = new HashSet<String>();
		Set<String> crSet = new HashSet<String>();
		String sysconst = "";
		
		Pattern pattern = Pattern.compile("(H[Q-Z]\\d+)|(WP\\d+)|(CR\\d+)|(PA\\d+)",Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(comments);
		while(matcher.find()){
			String tr_no = matcher.group(1);
			if(null != tr_no){
				trSet.add(tr_no);
			}
			
			String wp_no = matcher.group(2);
			if(null != wp_no){
				wpSet.add(wp_no);
			}
			
			String cr_no = matcher.group(3);
			if(null != cr_no){
				crSet.add(cr_no);
			}
			
			String sysconst_no = matcher.group(4);
			if(null != sysconst_no){
				sysconst = sysconst_no;
			}
		}
		RecUP recUP = new RecUP();
		recUP.setCr_Set(crSet);
		recUP.setTr_Set(trSet);
		recUP.setWp_Set(wpSet);
		recUP.setTitle(upName + "_" + sysconst + "_" + time);
		recUP.setTrack(track);
		recUP.setTime(time);
		recUP.setSysConstVer(sysconst);

		return recUP;
	}
	/**
	 * 
	 * @param pathName
	 * @param html
	 */
    public static void genertorHtml(){
		writeToFile(WEBPATH + File.separator + WEBNAME, builderWeb(recUP_Map));
		//writeToFile(WEBPATH + WEBNAME, builderWeb(recUP_Map));
    }

    private static void writeToFile(String pathName, String html){
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
    private static XML buildCCS(){
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
    
    private static Div buildW1324(){
		Div w1324 = new Div();
		w1324.setClass("se_context");
		w1324.addElement(new H1("L3 PG Reminders-w1324"));
		w1324.addElement(new H2("LMR delivery Guideline update"));
		w1324.addElement(new p().setTagText("BT and source coding delivery request diff"));
		w1324.addElement(new H2("Documentation in RSARTE"));
		w1324.addElement(new p().setTagText("You will see changes in the environment during the summer"));
		w1324.addElement(new H2("RAC-COP Meeting Agenda, June 11 2013"));
		w1324.addElement(new p().setTagText("Purify memory in use"));
		w1324.addElement(new p().setTagText("Dependency rules"));
		w1324.addElement(new p().setTagText("What MCT suites that need to be run after a certain change in some code"));
		w1324.addElement(new H2("Migration from CDM to EriDoc within DURA"));
		w1324.addElement(new H2("NO TDD support is required for the DUL20&21 platforms from L13B and onwards"));
		return w1324;
    }
    
    private static Div buildL3PGRMD(){
 		Div l3PGRmd = new Div();
 		l3PGRmd.setClass("se_context");
 		l3PGRmd.addElement(new H1("L3 PG Reminders-w22"));
 		l3PGRmd.addElement(new H2("Atest always green and how to make Atest all green"));
 		l3PGRmd.addElement(new p().setTagText("Inform \\\"everyone\\\" about the coming \\\"always green main\\\" plan"));
 		l3PGRmd.addElement(new p().setTagText("Important for everyone to understand this new zero tolerance from the beginning."));
 		l3PGRmd.addElement(new p().setTagText("RAC & OMF should be considered one."));
 		l3PGRmd.addElement(new p().setTagText("MOM, 10 CPP are all just deliveries and don\\'t require any special treatment or rules. "));
 		l3PGRmd.addElement(new p().setTagText("Communicate the plan in advance and when it comes into play"));
 		l3PGRmd.addElement(new p().setTagText("Secure resources for \\\"atest green on main\\\" activity and fully finish this work to get a good baseline."));
 		l3PGRmd.addElement(new p().setTagText("Prioritize work with optimizing multiatest (Peter O), ccstream integration and mechanisms for prioritizing delivering teams activities (delivery check, multiatest)."));
 		
 		l3PGRmd.addElement(new H2("How to write correct format in the pc_extract.conf"));
 		l3PGRmd.addElement(new H2("Product DOC Guardians: 3 doc in EQM, 5 doc in RNH, 9 doc in UEH"));
 		return l3PGRmd;
     }
     
    
    private static TD buildUPTD(RecUP recUP){
        TD td = new TD();
        td.setVAlign("middle");
        
        td.addElement(new br().setTagText(recUP.getTitle()));
        int i = 0;
        for(String wp_no : recUP.getWp_Set()){
        	 if( i > RECORD_MAX_NO){
        		 td.addElement(new br().setTagText("etc."));
        		 break;
        	 }
           	 td.addElement(new br().setTagText(wp_no));
        	 i++;
        }
        
        i = 0;
        for(String cr_no : recUP.getCr_Set()){
	       	 if( i > RECORD_MAX_NO){
	    		 td.addElement(new br().setTagText("etc."));
	    		 break;
	    	 }
        	 td.addElement(new br().setTagText(cr_no));
        	 i++;
        }
        
        i = 0;
        for(String tr_no : recUP.getTr_Set()){
	       	 if( i > RECORD_MAX_NO){
	    		 td.addElement(new br().setTagText("etc."));
	    		 break;
	    	 }
        	 td.addElement(new br().setTagText(tr_no));
        	 i++;
        }
        td.addElement(new br().setTagText("WP_" + recUP.getWp_Set().size() + " TR_" + recUP.getTr_Set().size() + " CR_" + recUP.getCr_Set().size()));
        return td;
    }
    
    private static Div buildTable(List<RecUP> recUP_list__L, String title_L){
		TH th_L = new TH();
		th_L.setVAlign("middle");
		th_L.setTagText(title_L);
		
		THead thead = new THead();
		thead.addElement(new TR().addElement(th_L));
		
		TBody tbody = new TBody();
		
		//TODO assert recUP_list_R/recUP_list_L size must < RECUP_MAX_NO
		for(int i=0; i<RECUP_MAX_NO; i++){
			TD td_L = buildUPTD(recUP_list__L.get(i));
	        tbody.addElement(new TR().addElement(td_L));
		}
		
		Table table = new Table();
		table.setClass("track_table");
		table.addAttribute("cellspacing", "0");
		table.addAttribute("cellpadding", "0");
		table.addAttribute("border", "0");
		table.addElement(thead);
		table.addElement(tbody);
		
		Div track_table_div = new Div();
		
		track_table_div.setID("t_odd");
		track_table_div.addElement(table);
		return track_table_div;
    }
    
    private static Div buildTable(List<RecUP> recUP_list__L, List<RecUP> recUP_list_R, String title_L, String title_R){
		TH th_L = new TH();
		th_L.setVAlign("middle");
		th_L.setTagText(title_L);
		
		TH th_R = new TH();
		th_R.setVAlign("middle");
		th_R.setTagText(title_R);
		
		THead thead = new THead();
		thead.addElement(new TR().addElement(th_L).addElement(th_R));
		
		TBody tbody = new TBody();
		
		//TODO assert recUP_list_R/recUP_list_L size must < RECUP_MAX_NO
		for(int i=0; i<RECUP_MAX_NO; i++){
			TD td_L = buildUPTD(recUP_list__L.get(i));
	        TD td_R = buildUPTD(recUP_list_R.get(i));
	        tbody.addElement(new TR().addElement(td_L).addElement(td_R));
		}
		
		Table table = new Table();
		table.setClass("track_table");
		table.addAttribute("cellspacing", "0");
		table.addAttribute("cellpadding", "0");
		table.addAttribute("border", "0");
		table.addElement(thead);
		table.addElement(tbody);
		
		Div track_table_div = new Div();
		
		track_table_div.setID("t_even");
		track_table_div.addElement(table);
		return track_table_div;
    }
    
    private static Div buildFTL23Report(){
        Div ft_L23_dailyReport = new Div();
        ft_L23_dailyReport.addElement(excelContent);
        return ft_L23_dailyReport;
    }
    
    private static Script buildScript(HashMap<String, List<RecUP>> recUP_Map){
    	Div ft_L23_dailyReport = buildFTL23Report();
		Div w1324 = buildW1324();
		Div l3PGRmd = buildL3PGRMD();
	    List<String> swapContent = new ArrayList<String>();
	    swapContent.add("\"" + w1324.toString() + "\"");
	    swapContent.add("\"" + l3PGRmd.toString() + "\"");
	    swapContent.add("\"" + ft_L23_dailyReport.toString() + "\"");
	    swapContent.add("\"" + mtGuidelineContent.toString() + "\"");
	    String preKey = "";
		if(0 == recUP_Map.size()%2){
			int i = 0;
			for (String key : recUP_Map.keySet()){
				i++;
				if(0 != i%2 && i != recUP_Map.size()){
					preKey= key;
                    continue; 		
				}else{
                    Div div_table = buildTable(recUP_Map.get(key),recUP_Map.get(preKey), key, preKey);
                    swapContent.add("\"" + div_table.toString() + "\"");
				}
			}
		}else{
			int i = 0;
			for (String key : recUP_Map.keySet()){
				i++;
				if(0 != i%2 && i != recUP_Map.size()){
					preKey= key;
                    continue; 		
				}else if(i == recUP_Map.size()){
					Div div_table = buildTable(recUP_Map.get(key),key);
					swapContent.add("\"" + div_table.toString() + "\"");
				}else{
                    Div div_table = buildTable(recUP_Map.get(key),recUP_Map.get(preKey), key, preKey);
                    swapContent.add("\"" + div_table.toString() + "\"");
				}
			}
		}
	    
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
    
    private static String builderWeb(HashMap<String, List<RecUP>> recUP_Map){
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
        head.addElement(excelStyle);
        head.addElement(mtGuidelineStyle);

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

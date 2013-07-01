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

import com.redarc.webfetcher.StreamGobbler;
import com.redarc.webfetcher.WebFetcher;

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
	private static final String FT_L23_DAILY_TEST_REPORT = "FT_L23_DAILY_REPORT.html"; 
	private static final String MT_DELIVERY_GUIDELINE = "LMR_Main_Track_delivery_guidelines.html"; 
	private static LinkedHashMap<String,String> LOCAL_WEB_PATH = new LinkedHashMap<String, String>();
	private static LinkedHashMap<String, List<RecUP>> recUP_Map = new LinkedHashMap<String, List<RecUP>>();

	
	/**
	 * @throws IOException
	 * @author EGANYAO
	 * download all webpage by TRACK_URLS_MAP
	 * @throws InterruptedException 
	 */
	public static void downloadWeb() throws IOException, InterruptedException{
		for(String key : TRACK_URLS_MAP.keySet()){
			WebFetcher.download(key, TRACK_URLS_MAP.get(key));
		}
	}
	
	private static void mappingLocalMap(){
		for(String key : TRACK_URLS_MAP.keySet()){
			LOCAL_WEB_PATH.put(key, LOCAL_SRV + key + ".html");
		}
	}
		
	public static void parseIpad() throws IOException{
		mappingLocalMap();
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
}

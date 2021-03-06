package com.redarc.webparser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.redarc.RecUP;
import com.redarc.Resconfig;
import com.redarc.webfetcher.WebFetcher;

public class IPadParser {
	private LinkedHashMap<String,String> TRACK_URLS_MAP = Resconfig.getInstance().getTrackMap();
	private LinkedHashMap<String,String> LOCAL_WEB_PATH = new LinkedHashMap<String, String>();
	private LinkedHashMap<String, List<RecUP>> recUP_Map = new LinkedHashMap<String, List<RecUP>>();
	
	public IPadParser(){}
	
	/**
	 * 
	 */
	public LinkedHashMap<String, List<RecUP>>  parseIpad(){
		downloadWeb();
		mappingLocalMap();
		for(String key : LOCAL_WEB_PATH.keySet()) {
			String url = LOCAL_WEB_PATH.get(key);
			Document doc = null;
			try {
				doc = Jsoup.connect(url)
					     .data("jquery","java")
					     .userAgent("Mozilla")
					     .followRedirects(true)
					     .get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
					if(Resconfig.getInstance().getUp_max_no() == recCount){
						break;
					}
				}
				i++;
			}
			recUP_Map.put(key,recUP_list);
		}
		return recUP_Map;
	}
	
	/**
	 * @throws IOException
	 * @author EGANYAO
	 * download all web page by TRACK_URLS_MAP
	 * @throws InterruptedException 
	 */
	public void downloadWeb(){
		for(String key : TRACK_URLS_MAP.keySet()){
			WebFetcher.download(key, TRACK_URLS_MAP.get(key));
		}
	}
	
	private void mappingLocalMap(){
		for(String key : TRACK_URLS_MAP.keySet()){
			StringBuffer fileaddr = new StringBuffer();
			fileaddr.append("http://");
			fileaddr.append(Resconfig.getInstance().getLocalsrv());
			fileaddr.append(File.separator);
			fileaddr.append("tmp");
			fileaddr.append(File.separator);
			fileaddr.append(key);
			fileaddr.append(".html");
			LOCAL_WEB_PATH.put(key, fileaddr.toString());
		}
	}
	
	/**
	 * 
	 * @param upName
	 * @param time
	 * @param comments
	 * @param track
	 * UP content sniffer
	 * @return
	 */
	private RecUP upContentSniffer(String upName, String time, String comments,String track){
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

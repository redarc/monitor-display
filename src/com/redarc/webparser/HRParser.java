package com.redarc.webparser;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.redarc.MonitorDisplay;
import com.redarc.webfetcher.WebFetcher;

public class HRParser {
	private static final String MHWEB_URL = "https://mhweb.ericsson.se/TREditWeb/faces/tredit/tredit.xhtml?eriref=";
	private static final String HEADING_ID = "frm_field_heading_j_id_9v_notetextEditMode";
	
	public HRParser(){
	}
	
	public static String heading(String hr_no){
		if(WebFetcher.download(hr_no, MHWEB_URL + hr_no)){
			return parseHRNO(MonitorDisplay.LOCAL_SRV + hr_no + ".html");
		}else{
			return null;
		}
	} 
	
	public static String parseHRNO(String url){
		Document doc;
		try {
			doc = Jsoup.connect(url)
				     .data("jquery","java")
				     .userAgent("Mozilla")
				     .followRedirects(true)
				     .get();
			Element heading = null;
			if(null != doc)
			{
				heading = doc.getElementById(HEADING_ID).child(0);
				return heading.text();
			}else{
				return null;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}

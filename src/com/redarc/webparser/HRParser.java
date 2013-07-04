package com.redarc.webparser;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.redarc.Resconfig;
import com.redarc.webfetcher.WebFetcher;

public class HRParser {
	//private static final String MHWEB_URL = "https://mhweb.ericsson.se/TREditWeb/faces/tredit/tredit.xhtml?eriref=";
	//private static final String HEADING_ID = "frm_field_heading_j_id_9v_notetextEditMode";
	//private static final String EXCEPTION = "compAjaxExceptionDialog_msgDialogPanel";
	
	public HRParser(){}
	
	public static String heading(String hr_no){
		if(WebFetcher.download(hr_no, Resconfig.getInstance().getMhweburl() + hr_no)){
			return parseHRNO(Resconfig.getInstance().getLocalsrv() + "tmp" + File.separator +  hr_no + ".html");
		}else{
			return null;
		}
	} 
	
	/**
	 * 
	 * @param url
	 * @return
	 */
	private static String parseHRNO(String url){
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
				Element headingParent = doc.getElementById(Resconfig.getInstance().getHrheadingId());
				//Element exception = doc.getElementById(Resconfig.getInstance().getHrexceptionId());
				if(null != headingParent){
					heading = headingParent.child(0);
				}
				/*
				if(null != exception){
					if(exception.text().contains("does not exist")){
						return "NOTEXIST";
					}
				}
				*/
			}
			if(null != heading){
				String result = heading.text().replace('\"', '\'').replace('\n', ' ').replace("¡°","\\\"").replace("¡±","\\\"").replace('\t', ' ').replace('\r',' ');
				System.out.println("HRHeading = " + result);
				return result;
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

package com.redarc;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HRParser {
	private static final String WEBPATH = System.getProperty("user.dir");
	private static final String MHWEB_URL = "https://mhweb.ericsson.se/TREditWeb/faces/tredit/tredit.xhtml?eriref=";
	private static final String LOCAL_SRV = "http://10.186.135.173/";
	private static final String HEADING_ID = "frm_field_heading_j_id_9v_notetextEditMode";
	private static String hr_no;
	
	public HRParser(String hr_no){
		HRParser.hr_no = hr_no;
	}
	public static String heading(){
		if(downloadWeb(hr_no)){
			return parseHRNO(LOCAL_SRV + hr_no + ".html");
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
			Element heading = doc.getElementById(HEADING_ID);
			return heading.text();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public static boolean downloadWeb(String hr_no){
		String cmd = "curl -o " + WEBPATH + File.separator + hr_no + ".html" + " -u EGANYAO:Qmm123456 -k " + MHWEB_URL + hr_no;
		System.out.println(cmd);
		
		Process p = null;
		try {
			p = Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		StreamGobbler errGobbler = new StreamGobbler(p.getErrorStream(), "ERROR");
		StreamGobbler outGobbler = new StreamGobbler(p.getInputStream(),"OUTPUT");
		
		errGobbler.start();
		outGobbler.start();
		try {
			p.waitFor();
			return true;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}

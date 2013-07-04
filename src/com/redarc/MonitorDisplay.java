package com.redarc;


import java.io.IOException;

import com.redarc.lteweb.WebFTReport;
import com.redarc.lteweb.WebIPad;
import com.redarc.lteweb.WebMTGuide;
import com.redarc.lteweb.WebSecurity;
import com.redarc.lteweb.WebW1324;
import com.redarc.lteweb.WebW22;
import com.redarc.webparser.IPadParser;

public class MonitorDisplay {
	
	//public static final String WEBPATH = "C:/Users/EGANYAO/Desktop/Web_Display/MetroTest/";
	public static final String WEBPATH = System.getProperty("user.dir");
	public static final String LOCAL_SRV = "http://10.186.135.173/";
	
	public static void main(String[] args) throws IOException, InterruptedException{
		System.out.println("iPad parse start");
		
		Resconfig.getInstance();
		/*
		WebIPad ipadWeb = new WebIPad(new IPadParser());
		WebW22 w22 = new WebW22();
		WebW1324 w1324 = new WebW1324();
		WebFTReport ftReport = new WebFTReport("FT_L23_DAILY_REPORT.html");
		WebMTGuide mtGuide = new WebMTGuide("LMR_Main_Track_delivery_guidelines.html");
		WebSecurity security = new WebSecurity();
		
		WebGenerator webGen = new WebGenerator("iPadUPStatus.html");
		webGen.addWeblist(ipadWeb);
		webGen.addWeblist(ftReport);
		webGen.addWeblist(mtGuide);
		webGen.addWeblist(security);
		webGen.addWeblist(w22);
		webGen.addWeblist(w1324);
		
		webGen.genertorHtml();
		*/
	    System.out.println("iPad parse end");
	}
}

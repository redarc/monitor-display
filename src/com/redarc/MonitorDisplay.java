package com.redarc;


import java.io.IOException;

import com.redarc.lteweb.FTDailyReportWeb;
import com.redarc.lteweb.IPadWeb;
import com.redarc.lteweb.MTDeliveryGuidelineWeb;
import com.redarc.lteweb.W1324Web;
import com.redarc.lteweb.W22Web;
import com.redarc.webparser.IPadParser;

public class MonitorDisplay {
	public static void main(String[] args) throws IOException, InterruptedException{
		System.out.println("iPad parse start");
		
		IPadWeb ipadWeb = new IPadWeb(new IPadParser());
		FTDailyReportWeb ftDailyReportWeb = new FTDailyReportWeb("xxxx");
		MTDeliveryGuidelineWeb mtDeliveryGuidelineWeb = new MTDeliveryGuidelineWeb("xxx");
		W22Web w22Web = new W22Web();
		W1324Web w1324Web = new W1324Web();
		
		WebGenerator webGen = new WebGenerator("xxxxxxxxxx");
		webGen.addWeblist(ipadWeb);
		webGen.addWeblist(ftDailyReportWeb);
		webGen.addWeblist(mtDeliveryGuidelineWeb);
		webGen.addWeblist(w22Web);
		webGen.addWeblist(w1324Web);
		
		webGen.genertorHtml();

	    System.out.println("iPad parse end");
	}
}

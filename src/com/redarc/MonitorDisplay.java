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
	
	public static void main(String[] args) throws IOException, InterruptedException{
		System.out.println("iPad parse start");
		
		WebIPad ipadWeb = new WebIPad(new IPadParser());
		WebW22 w22 = new WebW22();
		WebW1324 w1324 = new WebW1324();
		WebFTReport ftReport = new WebFTReport(Resconfig.getInstance().getFtdailyreport());
		WebMTGuide mtGuide = new WebMTGuide(Resconfig.getInstance().getMtguideline());
		WebSecurity security = new WebSecurity();
		
		WebGenerator webGen = new WebGenerator(Resconfig.getInstance().getIndexfile());
		webGen.addWeblist(ipadWeb);
		webGen.addWeblist(ftReport);
		webGen.addWeblist(mtGuide);
		webGen.addWeblist(security);
		webGen.addWeblist(w22);
		webGen.addWeblist(w1324);
		
		webGen.genertorHtml();
	    System.out.println("iPad parse end");
	}
}

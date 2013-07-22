package com.redarc;


import java.io.IOException;

import com.redarc.lteweb.WebIPad;
import com.redarc.lteweb.WebIndex;
import com.redarc.lteweb.WebSecurity;
import com.redarc.lteweb.WebW1324;
import com.redarc.lteweb.WebL3PGReminder;
import com.redarc.webparser.IPadParser;

public class MonitorDisplay {
	
	public static void main(String[] args) throws IOException, InterruptedException{

		System.out.println("iPad parse start");

		WebFactory webFacotry = new WebFactory();
		webFacotry.addWeb(new WebL3PGReminder(Resconfig.getInstance().getFtreport()));
		webFacotry.addWeb(new WebW1324(Resconfig.getInstance().getMtguide()));
		webFacotry.addWeb(new WebSecurity(Resconfig.getInstance().getSecurity()));
		
		WebIPad ipadWeb = new WebIPad(new IPadParser());
		webFacotry.addWeb(ipadWeb.getIpadWebSet());
		
		WebIndex index = new WebIndex(Resconfig.getInstance().getIndex());
		index.addRolllist(ipadWeb.getIpadWebSet());
		index.addRolllist(Resconfig.getInstance().getWeblist());
		index.setScript(index.script());
		
		webFacotry.addWeb(index);
		
		webFacotry.build();
		
	    System.out.println("iPad parse end");
	}
}

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
		webFacotry.addWeb(new WebL3PGReminder("w1327.html"));
		webFacotry.addWeb(new WebW1324("w1324.html"));
		webFacotry.addWeb(new WebSecurity("security.html"));
		
		WebIPad ipadWeb = new WebIPad(new IPadParser());
		webFacotry.addWeb(ipadWeb.getIpadWebSet());
		
		WebIndex index = new WebIndex("index");
		index.addRolllist(ipadWeb.getIpadWebSet());
		index.addRolllist(Resconfig.getInstance().getWeblist());
		webFacotry.addWeb(index);
		
		webFacotry.build();
		
	    System.out.println("iPad parse end");
	}
}

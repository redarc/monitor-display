package com.redarc;


import java.io.IOException;

import com.redarc.lteweb.WebIPad;
import com.redarc.lteweb.WebIndex;
import com.redarc.lteweb.WebW1329;
import com.redarc.webparser.IPadParser;

public class MonitorDisplay {
	
	public static void main(String[] args) throws IOException, InterruptedException{

		System.out.println("iPad parse start");

		WebFactory webFacotry = new WebFactory();
		webFacotry.addWeb(new WebW1329(Resconfig.getInstance().getL3PGRmd()));
		
		WebIPad ipadWeb = new WebIPad(new IPadParser());
		webFacotry.addWeb(ipadWeb.getIpadWebSet());
		
		WebIndex index = new WebIndex(Resconfig.getInstance().getIndex());
		index.addRolllist(ipadWeb.getIpadWebSet());
		index.addRolllist(Resconfig.getInstance().getWeblist());
		index.setScript(index.script());
		index.setHead(index.head());
		
		webFacotry.addWeb(index);
		
		webFacotry.build();
		
	    System.out.println("iPad parse end");
	}
}

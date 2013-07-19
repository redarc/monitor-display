package com.redarc;


import java.io.IOException;

import com.redarc.ftpclient.FtpUtils;
import com.redarc.lteweb.WebIPad;
import com.redarc.lteweb.WebSecurity;
import com.redarc.lteweb.WebW1324;
import com.redarc.lteweb.WebL3PGReminder;
import com.redarc.webparser.IPadParser;

public class MonitorDisplay {
	
	public static void main(String[] args) throws IOException, InterruptedException{
		System.out.println("iPad parse start");
		/*
		WebIPad ipadWeb = new WebIPad(new IPadParser());
		ipadWeb.build();
		
		WebL3PGReminder w1327 = new WebL3PGReminder("w1327.html");
		w1327.build();
		
		WebW1324 w1324 = new WebW1324("w1324.html");
		w1324.build();
		
		WebSecurity security = new WebSecurity("security.html");
		security.build();
		*/
	    System.out.println("iPad parse end");
		
		FtpUtils f = new FtpUtils();
		f.upload();
	}
}

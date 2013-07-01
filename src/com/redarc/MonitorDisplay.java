package com.redarc;


import java.io.IOException;
import java.util.LinkedHashMap;

import com.redarc.webfetcher.StreamGobbler;
import com.redarc.webfetcher.WebFetcher;

public class MonitorDisplay {
	private static final LinkedHashMap<String,String> TRACK_URLS_MAP = new LinkedHashMap<String,String>(){
		private static final long serialVersionUID = 1L;
		{
			put("FT_88_4_4","https://lte-dailytest.rnd.ki.sw.ericsson.se/n/up/listtrack/?bucket=846");
			put("FT_88_4_5","https://lte-dailytest.rnd.ki.sw.ericsson.se/n/up/listtrack/?bucket=830");
			put("MT_19_10","https://lte-dailytest.rnd.ki.sw.ericsson.se/n/up/listtrack/?bucket=685");
		}
	};
	
	private static final String WEBPATH = System.getProperty("user.dir");
	
	public static void main(String[] args) throws IOException, InterruptedException{
		System.out.println("iPad parse start");
		
		downloadWeb();
		
		IPadParser.parseIpad();

	    System.out.println("iPad parse end");
	}

	/**
	 * @throws IOException
	 * @author EGANYAO
	 * download all webpage by TRACK_URLS_MAP
	 * @throws InterruptedException 
	 */
	public static void downloadWeb() throws IOException, InterruptedException{
		for(String key : TRACK_URLS_MAP.keySet()){
			WebFetcher.download(key, TRACK_URLS_MAP.get(key));
		}
	}
}

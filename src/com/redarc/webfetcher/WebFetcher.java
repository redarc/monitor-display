package com.redarc.webfetcher;

import java.io.File;
import java.io.IOException;

public class WebFetcher {

	public static boolean download(String des_file_name, String url){
		String cmd = "curl -o " + System.getProperty("user.dir") + File.separator + "tmp" + File.separator + des_file_name + ".html " + "-u EGANYAO:Qmm123456 -k " + url;
		System.out.println(cmd);
		
		Process p = null;
		try {
			p = Runtime.getRuntime().exec(cmd);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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

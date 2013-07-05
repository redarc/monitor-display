package com.redarc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WebBuilder {
	public WebBuilder(){}
	
    public static void writeToFile(String pathName, String html){
    	File file = new File(pathName);
    	BufferedWriter bw = null;
    	if(file.exists()){
    		file.delete();
        }
    	try {
    		bw = new BufferedWriter(new FileWriter(file));
    		bw.write(html);
    		bw.flush();
    	}catch (IOException e){
    		e.printStackTrace();
    		throw(new RuntimeException());
    	}finally{
    		if(null != bw){
    			try {
    				bw.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
         }
    }
}

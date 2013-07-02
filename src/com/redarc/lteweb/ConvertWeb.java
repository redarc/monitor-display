package com.redarc.lteweb;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.redarc.BaseWeb;

public abstract class ConvertWeb extends BaseWeb {
	public final String LOCAL_SRV = "http://10.186.135.174";
	private Document doc; 
	
	public ConvertWeb(String fileName){
		try {
			setDoc(Jsoup.connect(LOCAL_SRV + fileName)
				     .data("jquery","java")
				     .userAgent("Mozilla")
				     .followRedirects(true)
				     .get());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Document getDoc() {
		return doc;
	}
	public void setDoc(Document doc) {
		this.doc = doc;
	}
	
	abstract public List<String> build();
	abstract public String style();

}

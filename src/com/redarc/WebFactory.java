package com.redarc;

import java.util.HashSet;
import java.util.Set;

public class WebFactory {
	private Set<BaseWeb> weblist = new HashSet<BaseWeb>();
	
	public WebFactory(){
		
	}
	public void addWeb(BaseWeb web){
		weblist.add(web);
	}
	
	public void addWeb(Set<BaseWeb> webSet){
		for(BaseWeb web : webSet){
			weblist.add(web);
		}
	}
	
	public void build(){
		for(BaseWeb web : weblist){
			web.build();
		}
	}
}

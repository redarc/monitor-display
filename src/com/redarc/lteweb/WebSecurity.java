package com.redarc.lteweb;

import org.apache.ecs.html.Div;
import org.apache.ecs.html.H1;
import org.apache.ecs.html.H2;

import com.redarc.BaseWeb;

public class WebSecurity extends BaseWeb{

	public WebSecurity(String fileName){
		super(fileName);
	}
	
	public String body() {
		Div security = new Div();
		security.setClass("security");
		security.addElement(new H1("Be security smart"));
		security.addElement(new H2("Your Ericsson ID-badge is your personal key to Ericsson office. "));
		security.addElement(new H2("Wear your ID-badge visible. "));
		return security.toString();
	}
}

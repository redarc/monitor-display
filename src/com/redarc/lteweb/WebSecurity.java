package com.redarc.lteweb;

import java.util.ArrayList;
import java.util.List;

import org.apache.ecs.html.Div;
import org.apache.ecs.html.H1;
import org.apache.ecs.html.H2;

import com.redarc.BaseWeb;

public class WebSecurity extends BaseWeb{

	public WebSecurity() {}

	@Override
	public List<String> build() {
		Div security = new Div();
		security.setClass("security");
		security.addElement(new H1("Be security smart"));
		security.addElement(new H2("Your Ericsson ID-badge is your personal key to Ericsson office. "));
		security.addElement(new H2("Wear your ID-badge visible. "));
		List<String> ret = new ArrayList<String>();
		ret.add(security.toString());
		return ret;
	}

	@Override
	public String style() {
		return null;
	}
	
	public String script(){
		return null;
	}
}

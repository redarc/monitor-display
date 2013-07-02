package com.redarc.lteweb;

import java.util.ArrayList;
import java.util.List;

import org.apache.ecs.html.Div;
import org.apache.ecs.html.H1;
import org.apache.ecs.html.H2;
import org.apache.ecs.xhtml.p;

import com.redarc.BaseWeb;

public class WebW1324 extends BaseWeb{

	public WebW1324(){}
	
	@Override
	public List<String> build() {
		Div w1324 = new Div();
		w1324.setClass("se_context");
		w1324.addElement(new H1("L3 PG Reminders-w1324"));
		w1324.addElement(new H2("LMR delivery Guideline update"));
		w1324.addElement(new p().setTagText("BT and source coding delivery request diff"));
		w1324.addElement(new H2("Documentation in RSARTE"));
		w1324.addElement(new p().setTagText("You will see changes in the environment during the summer"));
		w1324.addElement(new H2("RAC-COP Meeting Agenda, June 11 2013"));
		w1324.addElement(new p().setTagText("Purify memory in use"));
		w1324.addElement(new p().setTagText("Dependency rules"));
		w1324.addElement(new p().setTagText("What MCT suites that need to be run after a certain change in some code"));
		w1324.addElement(new H2("Migration from CDM to EriDoc within DURA"));
		w1324.addElement(new H2("NO TDD support is required for the DUL20&21 platforms from L13B and onwards"));
		
		List<String> ret = new ArrayList<String>();
		ret.add(w1324.toString());
		return ret;
	}

	@Override
	public String style() {
		// TODO Auto-generated method stub
		return null;
	}
}

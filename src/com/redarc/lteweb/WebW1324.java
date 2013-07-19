package com.redarc.lteweb;

import org.apache.ecs.html.Div;
import org.apache.ecs.html.H1;
import org.apache.ecs.html.H2;
import org.apache.ecs.xhtml.p;
import org.apache.ecs.xml.XML;

import com.redarc.BaseWeb;

public class WebW1324 extends BaseWeb{

	public WebW1324(String fileName){
		super(fileName);
	}
	
	@Override
	public String style() {
		String styleContent = new String("" +
				"div#wholePage{}" +
				"div#header{height: 0px}"+
				".track_table{" +
				"border:#000000 solid; " +
				"border-width:2px 0px 0px 2px;" +
				"width: 100%; " +
				"height: 100%" +
				"}" +
				".se_context{line-height: 42px;color : blue;}" + 
				".se_context h1{font-size: 50px}" +
				".se_context h2{font-size: 40px}" +
				".se_context p{font-size: 25px;font-family: Arial}"
				);

		XML style = new XML("style");
		style.setTagText(styleContent);
		return style.toString();
	}

	@Override
	public String script() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String body() {
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
		return w1324.toString();
	}
}

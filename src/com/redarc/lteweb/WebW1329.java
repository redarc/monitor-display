package com.redarc.lteweb;

import org.apache.ecs.html.Div;
import org.apache.ecs.html.H1;
import org.apache.ecs.html.H2;
import org.apache.ecs.xhtml.p;

import com.redarc.BaseWeb;

public class WebW1329 extends BaseWeb{
	
	public WebW1329(String fileName) {
		super(fileName);
	}

	public String body() {
 		Div l3PGRmd = new Div();
 		l3PGRmd.setClass("se_context");
 		l3PGRmd.addElement(new H1("L3 PG Reminders-w1329"));
 		l3PGRmd.addElement(new H2("LMR delivery Guideline update"));
 		l3PGRmd.addElement(new p().setTagText("19.13 Allowed new content + TR¡¯s."));
 		l3PGRmd.addElement(new p().setTagText("19.12 Only open for delivery of MH Web tagging UM3 blocking TRs"));
 		
 		l3PGRmd.addElement(new H2().setTagText("DURA JCAT Wiki"));
 		l3PGRmd.addElement(new p().setTagText("https://wiki.lmera.ericsson.se/wiki/DURA_JCAT_Wiki"));
 		
 		l3PGRmd.addElement(new H2().setTagText("System improvement (prestudy) for improving analyzation of dropped call problems"));
 		l3PGRmd.addElement(new p().setTagText("aimed at improving the methods and tools used for analyzation of problems related to dropped calls"));
 		l3PGRmd.addElement(new p().setTagText("have RAC and NC impact"));
 		
 		l3PGRmd.addElement(new H2("Link reference click here"));
 		
		return l3PGRmd.toString();
	}
}

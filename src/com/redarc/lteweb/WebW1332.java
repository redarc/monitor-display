package com.redarc.lteweb;

import org.apache.ecs.html.Div;
import org.apache.ecs.html.H1;
import org.apache.ecs.html.H2;
import org.apache.ecs.xhtml.p;
import org.apache.ecs.xml.XML;

import com.redarc.BaseWeb;

public class WebW1332 extends BaseWeb{
	
	public WebW1332(String fileName) {
		super(fileName);
	}

	public String body() {
 		Div l3PGRmd = new Div();
 		l3PGRmd.setClass("se_context");
 		l3PGRmd.addElement(new H1("L3 PG Reminders-w32"));
 		
 		l3PGRmd.addElement(new H2("Tracks applicable 2013-08-07: "));
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;L12B &nbsp;&nbsp;16.10.27"));
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;L13A &nbsp;&nbsp;18.14.17"));
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;L13B &nbsp;&nbsp;19.14;&nbsp;(All L13B content)"));
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;L14A &nbsp;&nbsp;21.0 (for BBI & Radio only)"));
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;21.1 (for CPP LSV114 only)  "));
 		
 		l3PGRmd.addElement(new H2().setTagText("RA SAM products should not yet be delivered to 21.x"));
 		l3PGRmd.addElement(new H2().setTagText("No progress of RA SAM to Git in w32 due to team being on vacation."));
 		l3PGRmd.addElement(new H2().setTagText("Pre-delivry test (RAST/FEST/EDC) for China team to handle incoming/ongoing MT work"));
 		l3PGRmd.addElement(new H2().setTagText("Link refer to here"));
 		
 		Div wholePage = new Div();
 		wholePage.setClass("wholePage");
 		wholePage.addElement(l3PGRmd);
 		
		return wholePage.toString();
	}
	
	public String style() {
		String styleContent = new String("" +
				".wholePage{background: LightBlue;width:100%;height:100% }" +
				".se_context h1{font-size: 50px;font-family: Arial;color : Green }" +
				".se_context h2{font-size: 40px;font-family: Arial}" +
				".se_context p{font-size: 25px;font-family: Arial}");

		XML style = new XML("style");
		style.setTagText(styleContent);
		return style.toString();
	}
}
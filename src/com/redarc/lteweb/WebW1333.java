package com.redarc.lteweb;

import org.apache.ecs.html.Div;
import org.apache.ecs.html.H1;
import org.apache.ecs.html.H2;
import org.apache.ecs.xhtml.p;
import org.apache.ecs.xml.XML;

import com.redarc.BaseWeb;

public class WebW1333 extends BaseWeb{
	
	public WebW1333(String fileName) {
		super(fileName);
	}

	public String body() {
 		Div l3PGRmd = new Div();
 		l3PGRmd.setClass("se_context");
 		l3PGRmd.addElement(new H1("L3 PG Reminders-W1333"));
 		
 		l3PGRmd.addElement(new H2("FreqInfo refactoring on L14A"));
 		l3PGRmd.addElement(new H2("Implode registers on L14A"));
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;To reduce waste a number of registers will be merged together"));
 		
 		l3PGRmd.addElement(new H2("19.14 and 19.15.1 open for delivery again"));
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;L13B Track 19.14-For UM5 & UM5.1"));
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;L13B Track 19.15-For IP1"));
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;19.14 GA-B TRs only(all deliveries in 19.14 needs to be delivered in 19.15.1 as well)"));
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;19.15.1 open for other L13B deliveries(no more floating baseline: all deliveries in 19.15.1 need to be delivered in 21.0 as well)(i.e. no more deliveries to 19.15)"));
 		
 		l3PGRmd.addElement(new H2("RSARTE 8.5.1 CP1 was deployed"));
 		
 		l3PGRmd.addElement(new H2("Link reference click here"));
 		
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
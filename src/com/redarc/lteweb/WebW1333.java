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
 		l3PGRmd.addElement(new H1("L3 PG Reminders-W1334"));
 		
 		l3PGRmd.addElement(new H2("Dependency_multiatest version 1.13 is now available"));
 		l3PGRmd.addElement(new H2("System const still valid in ClearCase"));
 		l3PGRmd.addElement(new H2("Main track: Still 21.0. Two issues prevent us from stepping up to 21.1."));
 		l3PGRmd.addElement(new H2("MT UEH PG refresh, but RNH PG still keep as before:"));
 		
 		
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;PG In:"));
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Joakim Hellsten"));
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Anders Westroos"));
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Fredrik Hultin N"));
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Fredrik Jonsson F "));
 		
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;PG Out:"));
 		
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Lennart Oscarsson"));
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Morgan Bergkvist"));
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Joakim Leuhusen"));
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Erik Borgenvik (At End of September) "));
 		
 		
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
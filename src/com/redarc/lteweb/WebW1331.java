package com.redarc.lteweb;
import org.apache.ecs.html.Div;
import org.apache.ecs.html.H1;
import org.apache.ecs.html.H2;
import org.apache.ecs.xhtml.p;
import org.apache.ecs.xml.XML;

import com.redarc.BaseWeb;

public class WebW1331 extends BaseWeb{
	
	public WebW1331(String fileName) {
		super(fileName);
	}

	public String body() {
 		Div l3PGRmd = new Div();
 		l3PGRmd.setClass("se_context");
 		l3PGRmd.addElement(new H1("L3 PG Reminders-W1331"));
 		l3PGRmd.addElement(new H2("New MHO transition for RA MBB TR"));
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;Use the new MHO LMRMBB-CN for L23 and L1 MT TRs of RA MBB"));
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;Use the original china delegate MHO until further notice for MT TR from SA SAM and C&O"));
 		
 		l3PGRmd.addElement(new H2().setTagText("L3 CoP kick off at W32"));
 		
 		l3PGRmd.addElement(new H2().setTagText("Checklist G Overview"));
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;GOA&nbsp;-&nbsp;Decision to start Opportunity Analysis(IPP: Feature Candidate)"));
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;G0&nbsp;-&nbsp;Decision to start Pre-study(IPP:Feature Candidate)"));
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;G1&nbsp;-&nbsp;Decision to start Development phase(IPP: Feature Planned)"));
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;G1.5&nbsp;-&nbsp;Decision to confirm Feature ready(IPP: Feature Confirmed)"));
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;G2&nbsp;-&nbsp;Decision to set Feature Ready"));
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;G3&nbsp;-&nbsp;Decision to release XFT from Feature responsibility"));
 		
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
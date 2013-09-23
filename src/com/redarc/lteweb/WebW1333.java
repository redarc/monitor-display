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
 		l3PGRmd.addElement(new H1("L3 PG Reminders-W1338"));
 		
 		l3PGRmd.addElement(new H2("ALWAYS check mail from Kim Grondin to secure delivery stop or not with subject as "));
 		//l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;LDN in signals is replaced with FroRef,parentRef"));
 		//l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;LDN removed in most RNH and UEH code,should not be used in cellLM, mostly removed in centralLM (still used in some OMF blocks)"));
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;INFO:LMR Main Track L14A delivery guidelines"));
 		
 		l3PGRmd.addElement(new H2("L3 Delivery check are grouping to checklist by local PG"));
 		//l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;FOR WPs"));
 		//l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Always email TO:LTE RBS Integration Delivery Request (EMC)"));
 		//l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;UEH area: still to mail group UEH Delivery Coordination"));
 		l3PGRmd.addElement(new H2("eDC guideline updated by INT team, recommended UP shown in RBS INT home page"));
 		l3PGRmd.addElement(new H2("dependency_multiatest 1.14 released, exclude already delivered files"));
 		l3PGRmd.addElement(new H2("Link refer to here"));
 		//l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;FOR TRs"));
 		//l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;14A RNH TRs are allowed to be delivered without sending delivery requests (except UEH)! But not if there is a complete delivery stop"));
 		//l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;14A UEH TRs need to send delivery request to UEH Delivery Cooridnation"));
 		//l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;13A TRs need to propose an answer in MHWeb and get approval from mDPM ; 13B TRs deliver to IP2 after checking with Kim Augustsson"));
 		//l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;Link reference click here"));
 		
 		
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
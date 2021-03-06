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
 		l3PGRmd.addElement(new H1("L3 PG Reminders-W1342"));
 		
 		l3PGRmd.addElement(new H2("LMR delivery Guideline update"));
 		
 		//l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;LDN in signals is replaced with FroRef,parentRef"));
 		//l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;LDN removed in most RNH and UEH code,should not be used in cellLM, mostly removed in centralLM (still used in some OMF blocks)"));
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;LMR Main Track: 21.4"));
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;UEH,RNH,OMF part delivery stop LIFTED"));
 		l3PGRmd.addElement(new H2("Involve Proxy PG at WP study phase"));
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;Since PGs can review especially whether the solution conflict current MT framework or design rules."));
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;Issue found in later phase such as code review need much more time to fix it and high risk for project."));
 		l3PGRmd.addElement(new H2("NO code delivery allowed before TechReport review permitted by MT PG."));
 		l3PGRmd.addElement(new H2("rn_legacy_SUITE-a template for every suite!"));
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;The goal behind this activity was to make the suite an example for all the testers writing Erlang code; use it as a reference or a template for how a suite should be properly written."));
 		l3PGRmd.addElement(new p().setTagText("&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;Location: /vobs/erbs/rnTest/suites/regression/test/rn_legacy_SUITE.erl"));
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
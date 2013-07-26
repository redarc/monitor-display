package com.redarc.lteweb;
import org.apache.ecs.html.Div;
import org.apache.ecs.html.H1;
import org.apache.ecs.html.H2;
import org.apache.ecs.xhtml.p;

import com.redarc.BaseWeb;

public class WebW1330 extends BaseWeb{
	
	public WebW1330(String fileName) {
		super(fileName);
	}

	public String body() {
 		Div l3PGRmd = new Div();
 		l3PGRmd.setClass("se_context");
 		l3PGRmd.addElement(new H1("L3 PG Reminders-W1330"));
 		l3PGRmd.addElement(new H2("Uplift from 19.8 to 88.7 done"));
 		l3PGRmd.addElement(new p().setTagText("recommended UP is ready"));
 		l3PGRmd.addElement(new p().setTagText("LteDc_88 for 88.7 delivery check & LteDc_88_CDP for 88.4.5 delivery check"));
 		
 		l3PGRmd.addElement(new H2().setTagText("14A track opening time delays"));
 		l3PGRmd.addElement(new p().setTagText("the opening of Main Track is postponed and will earliest happen by August 5th"));
 		
 		l3PGRmd.addElement(new H2().setTagText("FT L3 CoP"));
 		l3PGRmd.addElement(new p().setTagText("will be kicked off soon"));
 		
 		l3PGRmd.addElement(new H2().setTagText("RA SAM to Git"));
 		l3PGRmd.addElement(new p().setTagText("All PPC load modules built. Made initial copy of files in LRAT repo, same one used by BB+L2."));
 		
 		l3PGRmd.addElement(new H2("Link reference click here"));
 		
		return l3PGRmd.toString();
	}
}
package com.redarc.lteweb;

import org.apache.ecs.html.Div;
import org.apache.ecs.html.H1;
import org.apache.ecs.html.H2;
import org.apache.ecs.xhtml.p;

import com.redarc.BaseWeb;

public class WebW1327 extends BaseWeb{
	
	public WebW1327(String fileName) {
		super(fileName);
	}

	public String body() {
 		Div l3PGRmd = new Div();
 		l3PGRmd.setClass("se_context");
 		l3PGRmd.addElement(new H1("L3 PG Reminders-w1327"));
 		l3PGRmd.addElement(new H2("New TR process for MT"));
 		l3PGRmd.addElement(new p().setTagText("The Elearning link for the correction process on MT TR."));
 		l3PGRmd.addElement(new p().setTagText("Development TR: CorrectionProcess_Elearning"));
 		l3PGRmd.addElement(new p().setTagText("Maintenance TR: CorrectionProcessMaintenance_Elearning"));
 		
 		l3PGRmd.addElement(new H2().setTagText("ATest Evaluation Problem"));
 		l3PGRmd.addElement(new p().setTagText("All RNH & UEH product show \"Fail\" in \"Total\" column although it seems as they executed ok"));
 		l3PGRmd.addElement(new p().setTagText("MT are looking into the issue. Workaround is ignore \"Total\" column"));
 		
 		l3PGRmd.addElement(new H2().setTagText("Main Track 19.12  now"));
 		l3PGRmd.addElement(new H2("Git implemented in L14A for L2, TBD. For L3"));
 		l3PGRmd.addElement(new H2("Link reference click here"));
 		
		return l3PGRmd.toString();
	}
}

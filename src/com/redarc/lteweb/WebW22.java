package com.redarc.lteweb;

import java.util.ArrayList;
import java.util.List;

import org.apache.ecs.html.Div;
import org.apache.ecs.html.H1;
import org.apache.ecs.html.H2;
import org.apache.ecs.xhtml.p;

import com.redarc.BaseWeb;

public class WebW22 extends BaseWeb{

	public WebW22(){}
	
	@Override
	public List<String> build() {
 		Div l3PGRmd = new Div();
 		l3PGRmd.setClass("se_context");
 		l3PGRmd.addElement(new H1("L3 PG Reminders-w22"));
 		l3PGRmd.addElement(new H2("Atest always green and how to make Atest all green"));
 		l3PGRmd.addElement(new p().setTagText("Inform \\\"everyone\\\" about the coming \\\"always green main\\\" plan"));
 		l3PGRmd.addElement(new p().setTagText("Important for everyone to understand this new zero tolerance from the beginning."));
 		l3PGRmd.addElement(new p().setTagText("RAC & OMF should be considered one."));
 		l3PGRmd.addElement(new p().setTagText("MOM, 10 CPP are all just deliveries and don\\'t require any special treatment or rules. "));
 		l3PGRmd.addElement(new p().setTagText("Communicate the plan in advance and when it comes into play"));
 		l3PGRmd.addElement(new p().setTagText("Secure resources for \\\"atest green on main\\\" activity and fully finish this work to get a good baseline."));
 		l3PGRmd.addElement(new p().setTagText("Prioritize work with optimizing multiatest (Peter O), ccstream integration and mechanisms for prioritizing delivering teams activities (delivery check, multiatest)."));
 		
 		l3PGRmd.addElement(new H2("How to write correct format in the pc_extract.conf"));
 		l3PGRmd.addElement(new H2("Product DOC Guardians: 3 doc in EQM, 5 doc in RNH, 9 doc in UEH"));
 		
		List<String> ret = new ArrayList<String>();
		ret.add(l3PGRmd.toString());
		return ret;
	}

	@Override
	public String style() {
		// TODO Auto-generated method stub
		return null;
	}
}

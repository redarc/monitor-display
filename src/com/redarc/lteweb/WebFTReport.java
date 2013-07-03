package com.redarc.lteweb;


import java.util.ArrayList;
import java.util.List;

import org.apache.ecs.html.Div;
import org.jsoup.nodes.Element;

public class WebFTReport extends ConvertWeb{

	public WebFTReport(String fileName) {
		super(fileName);
	}

	@Override
	public List<String> build() {
		if(null != this.getDoc()){
			Element t = this.getDoc().body().child(0);
			String content = t.toString().replace('\"', '\'').replace('\n', ' ').replace("¡°","\\\"").replace("¡±","\\\"").replace('\t', ' ').replace('\r',' ');
			//TODO add div
	        Div ft_L23_dailyReport = new Div();
	        ft_L23_dailyReport.addElement(content);
	        List<String> ret = new ArrayList<String>();
	        ret.add(ft_L23_dailyReport.toString());
			return ret;
		}else{
			return null;
		}
	}

	@Override
	public String style() {
		if(null != this.getDoc()){
			Element s = this.getDoc().head().child(this.getDoc().head().children().size()-1);
			return s.toString();
		}else{
		    return null;
		}
	}

	@Override
	public String script() {
		// TODO Auto-generated method stub
		return null;
	}
}

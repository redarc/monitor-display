package com.redarc.lteweb;


import java.util.List;

import org.apache.ecs.html.Div;
import org.jsoup.nodes.Element;

public class FTDailyReportWeb extends ConvertWeb{

	public FTDailyReportWeb(String fileName) {
		super(fileName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String build(List<String> swapContent) {
		if(null != this.getDoc()){
			Element t = this.getDoc().body().child(0);
			String content = t.toString().replace('\"', '\'').replace('\n', ' ').replace("¡°","\\\"").replace("¡±","\\\"");
			//TODO add div
	        Div ft_L23_dailyReport = new Div();
	        ft_L23_dailyReport.addElement(content);
			swapContent.add(ft_L23_dailyReport.toString());
			return content;
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

}

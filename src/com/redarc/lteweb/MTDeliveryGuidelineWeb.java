package com.redarc.lteweb;

import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MTDeliveryGuidelineWeb extends ConvertWeb {

	public MTDeliveryGuidelineWeb(String fileName) {
		super(fileName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String build(List<String> swapContent) {
		if(null != this.getDoc()){
			Elements t = this.getDoc().body().getElementsByClass("Section1");
			String content = t.first().toString().replace('\"', '\'').replace('\n', ' ').replace("¡°","\\\"").replace("¡±","\\\"").replace('\t', ' ').replace('\r',' ');
			swapContent.add(content);
			return content;
		}else{
			return null;
		}
	}

	@Override
	public String style() {
		if(null != this.getDoc()){
			Element s = getDoc().head().child(getDoc().head().children().size()-1);
			return s.toString();
		}else{
			return null;
		}
	}

}

package com.redarc.lteweb;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class MTDeliveryGuidelineWeb extends ConvertWeb {

	public MTDeliveryGuidelineWeb(String fileName) {
		super(fileName);
	}

	@Override
	public List<String> build() {
		if(null != this.getDoc()){
			Elements t = this.getDoc().body().getElementsByClass("Section1");
			String content = t.first().toString().replace('\"', '\'').replace('\n', ' ').replace("¡°","\\\"").replace("¡±","\\\"").replace('\t', ' ').replace('\r',' ');
			List<String> ret = new ArrayList<String>();
			ret .add(content);
			return ret;
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

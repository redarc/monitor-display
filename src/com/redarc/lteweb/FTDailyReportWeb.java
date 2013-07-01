package com.redarc.lteweb;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.redarc.BaseWeb;

public class FTDailyReportWeb extends BaseWeb{

	@Override
	public String build() {
		String excelContent = null;
		Document doc;
		try {
			doc = Jsoup.connect(LOCAL_SRV + FT_L23_DAILY_TEST_REPORT)
				     .data("jquery","java")
				     .userAgent("Mozilla")
				     .followRedirects(true)
				     .get();
			Element t = doc.body().child(0);
			Element s = doc.head().child(doc.head().children().size()-1);
			excelContent = t.toString().replace('\"', '\'').replace('\n', ' ').replace("¡°","\\\"").replace("¡±","\\\"");
			excelStyle = s.toString();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return excelContent;
	}

}

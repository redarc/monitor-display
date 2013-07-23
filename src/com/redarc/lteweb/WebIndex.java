package com.redarc.lteweb;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.apache.ecs.html.Script;

import com.redarc.BaseWeb;
import com.redarc.Resconfig;

public class WebIndex extends BaseWeb{

	private HashSet<String> rolllist = new HashSet<String>();
	
	public void addRolllist(HashSet<String> weblist){
		for(String webaddr : weblist){
			rolllist.add(webaddr);
		}
	}
	
	public void addRolllist(Set<BaseWeb> weblist){
		for(BaseWeb web : weblist){
			rolllist.add(web.getFileName());
		}
	}
	
	public WebIndex(String fileName){
		this.setFileName(fileName);
	}
	
	public String body() {
		return "<body></body>";
	}

	public String script() {
		StringBuffer content = new StringBuffer();
		content.append("var httpServer = 'http://");
		content.append(Resconfig.getInstance().getLocalsrv());
		content.append(File.separator);
		content.append("';");
		content.append("var address=new Array();");
		
		int i = 0;
		for(String web : rolllist){
			String tmp = String.format("address[%d]=httpServer.concat('%s.html');", i, web);
			content.append(tmp);
			i++;
		}

		content.append("var mywindow=window.open(address[0]);");
		content.append("var i=1;");
		//content.append("setInterval('page(i)',30000);");
		content.append("setInterval('page(i)',");
		content.append(Resconfig.getInstance().getDelayTime());
		content.append(");");
		content.append("function page(j){");
		content.append("if(j==address.length){");
		content.append("i=0;");
		content.append("}");
		content.append("mywindow.location.href=address[i];");
		content.append("i++;");
		content.append("}");

		Script script = new Script();
		script.setTagText(content.toString());
		return script.toString();
	}
}

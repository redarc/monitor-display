package com.redarc.lteweb;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ecs.html.Body;
import org.apache.ecs.html.Div;
import org.apache.ecs.html.H2;
import org.apache.ecs.html.Head;
import org.apache.ecs.html.Html;
import org.apache.ecs.html.Meta;
import org.apache.ecs.html.TBody;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TH;
import org.apache.ecs.html.THead;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;
import org.apache.ecs.xhtml.br;
import org.apache.ecs.xml.XML;

import com.redarc.BaseWeb;
import com.redarc.RecUP;
import com.redarc.Resconfig;
import com.redarc.WebBuilder;
import com.redarc.webparser.HRParser;
import com.redarc.webparser.IPadParser;

public class WebIPad extends BaseWeb{
	private LinkedHashMap<String, List<RecUP>> recUP_Map;
	private String lastfileName;//TODO init by first other web
	public WebIPad(IPadParser iPadParser){
		this.recUP_Map = iPadParser.parseIpad();
	}
	
	public void build(){
		String preKey = null;
		int i = 0;
		for (String key : recUP_Map.keySet()){
			i++;
			if(0 != i%2 && i != recUP_Map.size()){
				preKey= key;
                continue; 		
			}else if((0 != recUP_Map.size()%2) && (i == recUP_Map.size())){
				Div div_table = buildTable(recUP_Map.get(key),key);
        		
                Head head = new Head();
        		Meta meta = new Meta();
        		meta.addAttribute("http-equiv", "refresh");
        		meta.addAttribute("content", Resconfig.getInstance().getRefreshTime() + ";url=" + preKey + ".html");
        		head.addElement(meta);
        		head.addElement(script());
        		head.addElement(style());
        		
        		Body body = new Body();
        		body.addElement(div_table.toString());
        		
        	    Html html = new Html();
        	    html.addElement(head);
        	    html.addElement(body);
        	    WebBuilder.writeToFile(key + ".html", html.toString());
        	    lastfileName = key;
			}else{
                Div div_table = buildTable(recUP_Map.get(key),recUP_Map.get(preKey), key, preKey);
        		
                Head head = new Head();
        		Meta meta = new Meta();
        		meta.addAttribute("http-equiv", "refresh");
        		meta.addAttribute("content", Resconfig.getInstance().getRefreshTime() + ";url=" + lastfileName + ".html");
        		head.addElement(meta);
        		head.addElement(script());
        		head.addElement(style());
        		
        		Body body = new Body();
        		body.addElement(div_table.toString());
        		
        	    Html html = new Html();
        	    html.addElement(head);
        	    html.addElement(body);
        	    WebBuilder.writeToFile(key + preKey + ".html", html.toString());
        	    lastfileName = key + lastfileName;
			}
		}
	}
	
    private TD buildUPTD(RecUP recUP){
        TD td = new TD();
        td.setVAlign("middle");
        
        br up_title = new br();
        up_title.setClass("recUPTitle");
        up_title.setTagText(recUP.getTitle());
        td.addElement(new H2().setTagText(recUP.getTitle()));
        
        int i = 0;
        for(String wp_no : recUP.getWp_Set()){
        	 if( i > Resconfig.getInstance().getTr_max_no()){
        		 td.addElement(new br().setTagText("etc."));
        		 break;
        	 }
           	 td.addElement(new br().setTagText(wp_no));
        	 i++;
        }
        
        i = 0;
        for(String cr_no : recUP.getCr_Set()){
	       	 if( i > Resconfig.getInstance().getTr_max_no()){
	    		 td.addElement(new br().setTagText("etc."));
	    		 break;
	    	 }
        	 td.addElement(new br().setTagText(cr_no));
        	 i++;
        }
        
        i = 0;
        for(String tr_no : recUP.getTr_Set()){
	       	 if( i > Resconfig.getInstance().getTr_max_no()){
	    		 td.addElement(new br().setTagText("etc."));
	    		 break;
	    	 }
	       	 String trName = HRParser.heading(tr_no);
             try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	    
        	 td.addElement(new br().setTagText(tr_no + " " + trName));
	       	 td.addElement(new br().setTagText(tr_no));
        	 i++;
        }
        td.addElement(new br().setTagText("WP_" + recUP.getWp_Set().size() + " TR_" + recUP.getTr_Set().size() + " CR_" + recUP.getCr_Set().size()));
        return td;
    }
    
    private Div buildTable(List<RecUP> recUP_list__L, String title_L){
		TH th_L = new TH();
		th_L.setVAlign("middle");
		th_L.setTagText(title_L);
		
		THead thead = new THead();
		thead.addElement(new TR().addElement(th_L));
		
		TBody tbody = new TBody();
		
		for(int i=0; i<recUP_list__L.size(); i++){
			TD td_L = buildUPTD(recUP_list__L.get(i));
	        tbody.addElement(new TR().addElement(td_L));
		}
		
		Table table = new Table();
		table.setClass("track_table");
		table.addAttribute("cellspacing", "0");
		table.addAttribute("cellpadding", "0");
		table.addAttribute("border", "0");
		table.addElement(thead);
		table.addElement(tbody);
		
		Div track_table_div = new Div();
		
		track_table_div.setID("t_odd");
		track_table_div.addElement(table);
		return track_table_div;
    }
    
    private Div buildTable(List<RecUP> recUP_list__L, List<RecUP> recUP_list_R, String title_L, String title_R){
		TH th_L = new TH();
		th_L.setVAlign("middle");
		th_L.setTagText(title_L);
		
		TH th_R = new TH();
		th_R.setVAlign("middle");
		th_R.setTagText(title_R);
		
		THead thead = new THead();
		thead.addElement(new TR().addElement(th_L).addElement(th_R));
		
		TBody tbody = new TBody();
		
		for(int i=0; i<recUP_list__L.size(); i++){
			TD td_L = buildUPTD(recUP_list__L.get(i));
	        TD td_R = buildUPTD(recUP_list_R.get(i));
	        tbody.addElement(new TR().addElement(td_L).addElement(td_R));
		}
		
		Table table = new Table();
		table.setClass("track_table");
		table.addAttribute("cellspacing", "0");
		table.addAttribute("cellpadding", "0");
		table.addAttribute("border", "0");
		table.addElement(thead);
		table.addElement(tbody);
		
		Div track_table_div = new Div();
		
		track_table_div.setID("t_even");
		track_table_div.addElement(table);
		return track_table_div;
    }

	@Override
	public String style() {
		String styleContent = new String("" +
				"div#wholePage{}" +
				"div#header{height: 0px}"+
				".track_table{" +
				"border:#000000 solid; " +
				"border-width:2px 0px 0px 2px;" +
				"width: 100%; " +
				"height: 100%" +
				"}" +
				".track_table th{" +
				"padding: 5px 5px 5px 5px;" +
				"border:#000000 solid; " +
				"border-width:0px 2px 2px 0px; " +
				"font-family: Arial; " +
				"font-size: 70px; " +
				"font-style: oblique; " +
				"font-weight: 900; " +
				"height:10%;" +
				"background: Aqua" +
				"}" +
				".track_table td{" +
				"padding: 5px 5px 5px 5px;" +
				"border:#000000 solid; " +
				"border-width:0px 2px 2px 0px; " +
				"font-family: Arial; " +
				"font-size: 24px; " +
				"font-style: normal; " +
				"font-weight: 900; " +
				"height:30%;" +
				"text-align: center;" +
				"vertical-align: middle" +
				"}" +
				".security{"+
				"background-image : url(slide0002_image001.jpg);" +
				"background-position: center;"+
				"background-repeat:no-repeat;"+
				"background-size: 1394px 1050px;"+
				"height:1050;"+
				"width:1394;"+
				"}"	+	
				".security h1{font-size: 50px}" +
				".security h2{font-size: 40px}"+
				".recUPTitle H2{color: blue;}" +
				".se_context{line-height: 42px;color : blue;}" + 
				".se_context h1{font-size: 50px}" +
				".se_context h2{font-size: 40px}" +
				".se_context p{font-size: 25px;font-family: Arial}" +
				".live-title{width: 100%; height: 100%}");

		XML style = new XML("style");
		style.setTagText(styleContent);
		return style.toString();
	}

	@Override
	public String script() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String body() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLastfileName() {
		return lastfileName;
	}

	public void setLastfileName(String lastfileName) {
		this.lastfileName = lastfileName;
	}
}

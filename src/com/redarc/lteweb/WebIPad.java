package com.redarc.lteweb;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ecs.html.Div;
import org.apache.ecs.html.H2;
import org.apache.ecs.html.TBody;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TH;
import org.apache.ecs.html.THead;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;
import org.apache.ecs.xhtml.br;

import com.redarc.BaseWeb;
import com.redarc.RecUP;
import com.redarc.Resconfig;
import com.redarc.webparser.HRParser;
import com.redarc.webparser.IPadParser;

public class WebIPad extends BaseWeb{
	
	private LinkedHashMap<String, List<RecUP>> recUP_Map;
	//private final int REC_RD_MAX_COUNT = 5;
	
	public WebIPad(IPadParser iPadParser){
		this.recUP_Map = iPadParser.parseIpad();
	}
	
	public List<String> build(){
		List<String> ret = new ArrayList<String>();
		String preKey = null;
		if(0 == recUP_Map.size() % 2){
			int i = 0;
			for (String key : recUP_Map.keySet()){
				i++;
				if(0 != i%2 && i != recUP_Map.size()){
					preKey= key;
                    continue; 		
				}else{
                    Div div_table = buildTable(recUP_Map.get(key),recUP_Map.get(preKey), key, preKey);
                    ret.add(div_table.toString());
				}
			}
		}else{
			int i = 0;
			for (String key : recUP_Map.keySet()){
				i++;
				if(0 != i%2 && i != recUP_Map.size()){
					preKey= key;
                    continue; 		
				}else if(i == recUP_Map.size()){
					Div div_table = buildTable(recUP_Map.get(key),key);
					ret.add(div_table.toString());
				}else{
                    Div div_table = buildTable(recUP_Map.get(key),recUP_Map.get(preKey), key, preKey);
                    ret.add(div_table.toString());
				}
			}
		}
		return ret;
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
	       	//String trName = "";
	       	 
	       	 System.out.println("----------------hrparser start------------");
	       	 String trName = HRParser.heading(tr_no);
	       	 //new HRParserDelay(trName,10);
             try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	    
             System.out.println("----------------hrparser over------------");
             
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String script() {
		// TODO Auto-generated method stub
		return null;
	}
}

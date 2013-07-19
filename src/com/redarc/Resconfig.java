package com.redarc;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.VisitorSupport;
import org.dom4j.io.*;

public class Resconfig {

	private String ftdailyreport = "FT_L23_DAILY_REPORT.html";
	private String mtguideline = "LMR_Main_Track_delivery_guidelines.html";
	private String security = "Security.html";
	private String indexfile = "iPadUPStatus.html";
	private String localpath = System.getProperty("user.dir");
	private String localsrv = "http://10.186.135.173/";
	private Integer delayTime = 5000;
	private Integer swapTime = 2000;
	private Integer refreshTime = 10000;
	private int tr_max_no = 5;
	private int up_max_no = 3;
	private String mhweburl = "https://mhweb.ericsson.se/TREditWeb/faces/tredit/tredit.xhtml?eriref=";
	private String hrheadingId = "frm_field_heading_j_id_9v_notetextEditMode";
	private String hrexceptionId = "compAjaxExceptionDialog_msgDialogPanel";
	private LinkedHashMap<String,String> trackMap = new LinkedHashMap<String,String>();
	private Document doc;
	
	private static Resconfig self;
	private Resconfig(){
		SAXReader saxreader = new SAXReader();
		try {
			doc = saxreader.read(new File("config.xml"));
			Element root = doc.getRootElement();
			root.accept(new ResVisitor());
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public static Resconfig getInstance(){
		if(null != self){
			return self;
		}else{
			return new Resconfig();
		}
	}
	
	public class ResVisitor extends VisitorSupport{
		public void visit(Element elem){
		    switch(elem.getName()){
		    case "mhweburl" :
		    	setMhweburl(elem.getText());
		    	break;
		    case "tracklist" :
		    	@SuppressWarnings("rawtypes")
				List list = elem.elements();
		    	@SuppressWarnings("rawtypes")
				Iterator iter = list.iterator();
		    	while(iter.hasNext()){
		    		Element track = (Element)iter.next();
		    		putTrackMap(track.getName(),track.getText());
		    	}
		    	break;
		    case "localserver":
		    	setLocalsrv(elem.getText());
		    	break;
		    case "localpath":
		    	setLocalpath(elem.getText());
		    	break;
		    case "hrheadingid":
		    	setHrheadingId(elem.getText());
		    	break;
		    case "hrexceptionid":
		    	setHrexceptionId(elem.getText());
		    	break;
		    case "swaptime":
		    	setSwapTime(Integer.parseInt(elem.getText()));
		    	break;
		    case "delaytime":
		    	setDelayTime(Integer.parseInt(elem.getText()));
		    	break;
		    case "refreshtme":
		    	setRefreshTime(Integer.parseInt(elem.getText()));
		    	break;
		    case "trmaxno":
		    	setTr_max_no(Integer.parseInt(elem.getText()));
		    	break;
		    case "recmaxno":
		    	setUp_max_no(Integer.parseInt(elem.getText()));
		    	break;
		    case "ftdailyreport":
		    	setFtdailyreport(elem.getText());
		    	break;
		    case "mtguideline":
		    	setMtguideline(elem.getText());
		    	break;
		    case "security:":
		    	setSecurity(elem.getText());
		    	break;
		    case "indexfile":
		    	setIndexfile(elem.getText());
		    	break;
		    default:
		    	break;
		    }
		    
			//System.out.println("elem: " + elem.getName() + " " + elem.getText());
		}
		public void visit(Attribute attr){
			System.out.println("attr: " + attr.getName());
		}
	}
	
	public String getSecurity() {
		return security;
	}

	public void setSecurity(String security) {
		this.security = security;
	}
	public String getLocalpath() {
		return localpath;
	}
	public void setLocalpath(String localpath) {
		this.localpath = localpath;
	}
	public String getLocalsrv() {
		return localsrv;
	}
	public void setLocalsrv(String localsrv) {
		this.localsrv = localsrv;
	}
	public Integer getDelayTime() {
		return delayTime;
	}
	public void setDelayTime(Integer delayTime) {
		this.delayTime = delayTime;
	}
	public Integer getSwapTime() {
		return swapTime;
	}
	public void setSwapTime(Integer swapTime) {
		this.swapTime = swapTime;
	}
	public Integer getRefreshTime() {
		return refreshTime;
	}
	public void setRefreshTime(Integer refreshTime) {
		this.refreshTime = refreshTime;
	}
	public int getTr_max_no() {
		return tr_max_no;
	}
	public void setTr_max_no(int tr_max_no) {
		this.tr_max_no = tr_max_no;
	}
	public int getUp_max_no() {
		return up_max_no;
	}
	public void setUp_max_no(int up_max_no) {
		this.up_max_no = up_max_no;
	}
	public String getMhweburl() {
		return mhweburl;
	}
	public void setMhweburl(String mhweburl) {
		this.mhweburl = mhweburl;
	}
	public String getHrheadingId() {
		return hrheadingId;
	}
	public void setHrheadingId(String hrheadingId) {
		this.hrheadingId = hrheadingId;
	}
	public String getHrexceptionId() {
		return hrexceptionId;
	}
	public void setHrexceptionId(String hrexceptionId) {
		this.hrexceptionId = hrexceptionId;
	}
	public void putTrackMap(String key, String value){
		trackMap.put(key, value);
	}
	public LinkedHashMap<String, String> getTrackMap() {
		return trackMap;
	}
	public void setTrackMap(LinkedHashMap<String, String> trackMap) {
		this.trackMap = trackMap;
	}
	public String getFtdailyreport() {
		return ftdailyreport;
	}

	public void setFtdailyreport(String ftdailyreport) {
		this.ftdailyreport = ftdailyreport;
	}

	public String getMtguideline() {
		return mtguideline;
	}

	public void setMtguideline(String mtguideline) {
		this.mtguideline = mtguideline;
	}

	public String getIndexfile() {
		return indexfile;
	}

	public void setIndexfile(String indexfile) {
		this.indexfile = indexfile;
	}
}

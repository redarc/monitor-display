package com.redarc;

import java.util.Set;

public class RecUP {
	private String title;
	private String track;
	private String sysConstVer;
	private String time;
	private Set<String> wp_set;
	private Set<String> tr_set;
	private Set<String> cr_set;
	private Set<String> trHead_set;
	
	public String getTrack() {
		return track;
	}
	public void setTrack(String track) {
		this.track = track;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSysConstVer() {
		return sysConstVer;
	}
	public void setSysConstVer(String sysConstVer) {
		this.sysConstVer = sysConstVer;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public Set<String> getWp_Set() {
		return wp_set;
	}
	public void setWp_Set(Set<String> wp_set) {
		this.wp_set = wp_set;
	}
	
	public void addWp_Set(String wp) {
		this.wp_set.add(wp);
	}
	
	public Set<String> getTr_Set() {
		return tr_set;
	}
	public void setTr_Set(Set<String> tr_set) {
		this.tr_set = tr_set;
	}
	
	public void addTr_Set(String tr) {
		this.tr_set.add(tr);
	}
	
	public Set<String> getCr_Set() {
		return cr_set;
	}
	
	public void setCr_Set(Set<String> cr_set) {
		this.cr_set = cr_set;
	}
	
	public void addCr_Set(String cr) {
		this.cr_set.add(cr);
	}
	public Set<String> getTrHead_set() {
		return trHead_set;
	}
	public void setTrHead_Set(Set<String> trHead_set) {
		this.trHead_set = trHead_set;
	}
	public void addTrHead_set(String trHead) {
		this.trHead_set.add(trHead);
	}
}

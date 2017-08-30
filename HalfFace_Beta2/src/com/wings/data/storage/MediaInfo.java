package com.wings.data.storage;

import java.io.Serializable;

public class MediaInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int vid;
	private String vname,vactor,vdesc,vdate,vpath;
	private int vstatus;
	
	public MediaInfo(){}

	public int getVid() {
		return vid;
	}

	public void setVid(int vid) {
		this.vid = vid;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public String getVactor() {
		return vactor;
	}

	public void setVactor(String vactor) {
		this.vactor = vactor;
	}

	public String getVdesc() {
		return vdesc;
	}

	public void setVdesc(String vdesc) {
		this.vdesc = vdesc;
	}

	public String getVdate() {
		return vdate;
	}

	public void setVdate(String vdate) {
		this.vdate = vdate;
	}

	public String getVpath() {
		return vpath;
	}

	public void setVpath(String vpath) {
		this.vpath = vpath;
	}

	public int getVstatus() {
		return vstatus;
	}

	public void setVstatus(int vstatus) {
		this.vstatus = vstatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}

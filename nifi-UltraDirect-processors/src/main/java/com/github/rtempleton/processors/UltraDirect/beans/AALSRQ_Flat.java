package com.github.rtempleton.processors.UltraDirect.beans;

public class AALSRQ_Flat {

	private long ts;
	private String transId;
	private String ind, otd, pid;
	private Integer nnt;
	
	public long getTs() {
		return ts;
	}
	public void setTs(long ts) {
		this.ts = ts;
	}
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getInd() {
		return ind;
	}
	public void setInd(String ind) {
		this.ind = ind;
	}
	public String getOtd() {
		return otd;
	}
	public void setOtd(String otd) {
		this.otd = otd;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public Integer getNnt() {
		return nnt;
	}
	public void setNnt(Integer nnt) {
		this.nnt = nnt;
	}
	
}

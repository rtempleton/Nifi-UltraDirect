package com.github.rtempleton.processors.UltraDirect.beans;

public class AALSRP_Flat {

	private long ts;
	private String transId;
	private Integer seg;
	private String chn, pid, pst;
	
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
	public Integer getSeg() {
		return seg;
	}
	public void setSeg(Integer seg) {
		this.seg = seg;
	}
	public String getChn() {
		return chn;
	}
	public void setChn(String chn) {
		this.chn = chn;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPst() {
		return pst;
	}
	public void setPst(String pst) {
		this.pst = pst;
	}

}

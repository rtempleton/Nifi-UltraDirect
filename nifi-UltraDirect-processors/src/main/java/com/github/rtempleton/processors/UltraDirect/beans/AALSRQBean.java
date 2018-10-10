package com.github.rtempleton.processors.UltraDirect.beans;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AALSRQBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(AALSRQBean.class);
	
	private String IND;
	private String OTD;
	private Integer NNT;
	private String CHN;
	private String PID;

	public AALSRQBean(String segment) {
		parseSegment(segment, this);
	}
	
	static void parseSegment(String segment, AALSRQBean bean){
		String[] segments = segment.split("\\|");
		//skip the first segment since we know it's the type
		for (int i=1;i<segments.length;i++){
			
			switch(segments[i].substring(0, 3)){
				case "IND":
					bean.IND = getVal(segments[i]);
					break;
				case "OTD":
					bean.OTD = getVal(segments[i]);
					break;
				case "NNT":
					bean.NNT = Integer.parseInt(getVal(segments[i]));
					break;
				case "CHN":
					bean.CHN = getVal(segments[i]);
					break;
				case "PID":
					bean.PID = getVal(segments[i]);
					break;
				default:
					logger.warn("Unknown Segment:" + segments[i]);
			}
		}
	}
	
	static String getVal(String seg){
		return seg.substring(3, seg.length());
	}
	
	public String getIND() {
		return IND;
	}

	public void setIND(String iND) {
		IND = iND;
	}

	public String getOTD() {
		return OTD;
	}

	public void setOTD(String oTD) {
		OTD = oTD;
	}

	public Integer getNNT() {
		return NNT;
	}

	public void setNNT(Integer nNT) {
		NNT = nNT;
	}

	public String getCHN() {
		return CHN;
	}

	public void setCHN(String cHN) {
		CHN = cHN;
	}

	public String getPID() {
		return PID;
	}

	public void setPID(String pID) {
		PID = pID;
	}


}

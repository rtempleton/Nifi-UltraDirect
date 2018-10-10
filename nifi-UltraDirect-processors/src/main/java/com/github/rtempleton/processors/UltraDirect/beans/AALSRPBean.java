package com.github.rtempleton.processors.UltraDirect.beans;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AALSRPBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(AALSRPBean.class);
	
	private Integer SEG;
	private String CHN;
	private String PID;
	private String PST;

	public AALSRPBean(String segment) {
		parseSegment(segment, this);
	}
	
	static void parseSegment(String segment, AALSRPBean bean){
		String[] segments = segment.split("\\|");
		//skip the first segment since we know it's the type
		for (int i=1;i<segments.length;i++){
			
			switch(segments[i].substring(0, 3)){
				case "SEG":
					bean.SEG = Integer.parseInt(getVal(segments[i]));
					break;
				case "CHN":
					bean.CHN = getVal(segments[i]);
					break;
				case "PID":
					bean.PID = getVal(segments[i]);
					break;
				case "PST":
					bean.PST = getVal(segments[i]);
					break;
				default:
					logger.warn("Unknown Segment:" + segments[i]);
			}
		}
	}
	
	static String getVal(String seg){
		return seg.substring(3, seg.length());
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

	public String getPST() {
		return PST;
	}

	public void setPST(String pST) {
		PST = pST;
	}

	public void setSEG(Integer sEG) {
		SEG = sEG;
	}
	
	public Integer getSEG(){
		return SEG;
	}

}

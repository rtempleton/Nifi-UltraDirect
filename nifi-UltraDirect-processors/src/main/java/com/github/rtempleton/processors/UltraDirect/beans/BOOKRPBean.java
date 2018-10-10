package com.github.rtempleton.processors.UltraDirect.beans;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BOOKRPBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(BOOKRPBean.class);
	
	private String BST;
	private String CNF;
	private String CAN;
	private String CUR;
	private Float BKR;
	private String GQI;
	private String NAM;
	private String RTY;
	

	public BOOKRPBean(String segment) {
		parseSegment(segment, this);
	}
	
	static void parseSegment(String segment, BOOKRPBean bean){
		String[] segments = segment.split("\\|");
		//skip the first segment since we know it's the type
		for (int i=1;i<segments.length;i++){
			
			switch(segments[i].substring(0, 3)){
				case "BST":
					bean.BST = getVal(segments[i]);
					break;
				case "CNF":
					bean.CNF = getVal(segments[i]);
					break;
				case "CAN":
					bean.CAN = getVal(segments[i]);
					break;
				case "CUR":
					bean.CUR = getVal(segments[i]);
					break;
				case "BKR":
					bean.BKR = Float.parseFloat(getVal(segments[i]));
					break;
				case "GQI":
					bean.GQI = getVal(segments[i]);
					break;
				case "NAM":
					bean.NAM = getVal(segments[i]);
					break;
				case "RTY":
					bean.RTY = getVal(segments[i]);
					break;
				default:
					logger.warn("Unknown Segment:" + segments[i]);
			}
		}
	}
	
	static String getVal(String seg){
		return seg.substring(3, seg.length());
	}

	public String getBST() {
		return BST;
	}

	public void setBST(String bST) {
		BST = bST;
	}

	public String getCNF() {
		return CNF;
	}

	public void setCNF(String cNF) {
		CNF = cNF;
	}
	
	public String getCAN() {
		return CAN;
	}

	public void setCAN(String cAN) {
		CAN = cAN;
	}

	public String getCUR() {
		return CUR;
	}

	public void setCUR(String cUR) {
		CUR = cUR;
	}

	public Float getBKR() {
		return BKR;
	}

	public void setBKR(Float bKR) {
		BKR = bKR;
	}

	public String getGQI() {
		return GQI;
	}

	public void setGQI(String gQI) {
		GQI = gQI;
	}

	public String getNAM() {
		return NAM;
	}

	public void setNAM(String nAM) {
		NAM = nAM;
	}

	public String getRTY() {
		return RTY;
	}

	public void setRTY(String rTY) {
		RTY = rTY;
	}

}

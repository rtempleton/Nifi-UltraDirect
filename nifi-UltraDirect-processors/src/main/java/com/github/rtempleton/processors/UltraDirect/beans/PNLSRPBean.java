package com.github.rtempleton.processors.UltraDirect.beans;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PNLSRPBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(PNLSRPBean.class);
	
	private Integer SEG;
	private String CHN;
	private String PID;
	private String PNM;
	private String PS1;
	private String PS2;
	private String CTY;
	private String STE;
	private String CNT;
	private Float DSP;
	private String DRP;
	private String DUP;
	private String RPC;
	private String RPI;
	private String RP1;
	private String RP2;
	private String RP3;

	public PNLSRPBean(String segment) {
		parseSegment(segment, this);
	}
	
	static void parseSegment(String segment, PNLSRPBean bean){
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
				case "PNM":
					bean.PNM = getVal(segments[i]);
					break;
				case "PS1":
					bean.PS1 = getVal(segments[i]);
					break;
				case "PS2":
					bean.PS2 = getVal(segments[i]);
					break;
				case "CTY":
					bean.CTY = getVal(segments[i]);
					break;
				case "STE":
					bean.STE = getVal(segments[i]);
					break;
				case "CNT":
					bean.CNT = getVal(segments[i]);
					break;
				case "DSP":
					bean.DSP = Float.parseFloat(getVal(segments[i]));
					break;
				case "DRP":
					bean.DRP = getVal(segments[i]);
					break;
				case "DUP":
					bean.DUP = getVal(segments[i]);
					break;
				case "RPC":
					bean.RPC = getVal(segments[i]);
					break;
				case "RPI":
					bean.RPI = getVal(segments[i]);
					break;
				case "RP1":
					bean.RP1 = getVal(segments[i]);
					break;
				case "RP2":
					bean.RP2 = getVal(segments[i]);
					break;
				case "RP3":
					bean.RP3 = getVal(segments[i]);
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

	public String getPNM() {
		return PNM;
	}

	public void setPNM(String pNM) {
		PNM = pNM;
	}

	public String getPS1() {
		return PS1;
	}

	public void setPS1(String pS1) {
		PS1 = pS1;
	}

	public String getPS2() {
		return PS2;
	}

	public void setPS2(String pS2) {
		PS2 = pS2;
	}

	public String getCTY() {
		return CTY;
	}

	public void setCTY(String cTY) {
		CTY = cTY;
	}

	public String getSTE() {
		return STE;
	}

	public void setSTE(String sTE) {
		STE = sTE;
	}

	public String getCNT() {
		return CNT;
	}

	public void setCNT(String cNT) {
		CNT = cNT;
	}

	public Float getDSP() {
		return DSP;
	}

	public void setDSP(Float dSP) {
		DSP = dSP;
	}

	public String getDRP() {
		return DRP;
	}

	public void setDRP(String dRP) {
		DRP = dRP;
	}

	public String getDUP() {
		return DUP;
	}

	public void setDUP(String dUP) {
		DUP = dUP;
	}

	public String getRPC() {
		return RPC;
	}

	public void setRPC(String rPC) {
		RPC = rPC;
	}

	public String getRPI() {
		return RPI;
	}

	public void setRPI(String rPI) {
		RPI = rPI;
	}

	public String getRP1() {
		return RP1;
	}

	public void setRP1(String rP1) {
		RP1 = rP1;
	}

	public String getRP2() {
		return RP2;
	}

	public void setRP2(String rP2) {
		RP2 = rP2;
	}

	public String getRP3() {
		return RP3;
	}

	public void setRP3(String rP3) {
		RP3 = rP3;
	}


	public void setSEG(Integer sEG) {
		SEG = sEG;
	}
	
	public Integer getSEG(){
		return SEG;
	}

}

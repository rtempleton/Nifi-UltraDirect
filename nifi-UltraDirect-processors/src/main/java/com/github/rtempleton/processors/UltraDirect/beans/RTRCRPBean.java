package com.github.rtempleton.processors.UltraDirect.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.annotations.SerializedName;

public class RTRCRPBean implements Serializable{
	@SerializedName("RTRCRPB")
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(RTRCRPBean.class);
	
	private Integer SEG;
	private String RTC;
	private String RTY;
	private String RD1;
	private String RD2;
	private String RD3;
	private String LAR;
	private String CXT;
	private List<String> AMC;
	private List<String> AMD;
	private List<String> AMI;
	private List<String> AMF;
	private Float RMR;
	private String RFQ;
	private Float TRR;
	private String RT1;
	private String RT2;
	private String RT3;
	private String RCI;
	private String FLG;

	public RTRCRPBean(String segment) {
		this.AMC = new ArrayList<String>(6);
		this.AMD = new ArrayList<String>(6);
		this.AMI = new ArrayList<String>(6);
		this.AMF = new ArrayList<String>(6);
		parseSegment(segment, this);
	}
	
	static void parseSegment(String segment, RTRCRPBean bean){
		String[] segments = segment.split("\\|");
		//skip the first segment since we know it's the type
		for (int i=1;i<segments.length;i++){
			
			switch(segments[i].substring(0, 3)){
				case "SEG":
					bean.SEG = Integer.parseInt(getVal(segments[i]));
					break;
				case "RTC":
					bean.RTC = getVal(segments[i]);
					break;
				case "RTY":
					bean.RTY = getVal(segments[i]);
					break;
				case "RD1":
					bean.RD1 = getVal(segments[i]);
					break;
				case "RD2":
					bean.RD2 = getVal(segments[i]);
					break;
				case "RD3":
					bean.RD3 = getVal(segments[i]);
					break;
				case "LAR":
					bean.LAR = getVal(segments[i]);
					break;
				case "CXT":
					bean.CXT = getVal(segments[i]);
					break;
				case "AMC":
					bean.AMC.add(getVal(segments[i]));
					break;
				case "AMD":
					bean.AMD.add(getVal(segments[i]));
					break;
				case "AMI":
					bean.AMI.add(getVal(segments[i]));
					break;
				case "AMF":
					bean.AMF.add(getVal(segments[i]));
					break;
				case "RMR":
					bean.RMR = Float.parseFloat(getVal(segments[i]));
					break;
				case "RFQ":
					bean.RFQ = getVal(segments[i]);
					break;
				case "TRR":
					bean.TRR = Float.parseFloat(getVal(segments[i]));
					break;
				case "RT1":
					bean.RT1 = getVal(segments[i]);
					break;
				case "RT2":
					bean.RT2 = getVal(segments[i]);
					break;
				case "RT3":
					bean.RT3 = getVal(segments[i]);
					break;
				case "RCI":
					bean.RCI = getVal(segments[i]);
					break;
				case "FLG":
					bean.FLG = getVal(segments[i]);
					break;
				default:
					logger.warn("Unknown Segment:" + segments[i]);
			}
		}
	}
	
	static String getVal(String seg){
		return seg.substring(3, seg.length());
	}

	public Integer getSEG() {
		return SEG;
	}

	public void setSEG(Integer sEG) {
		SEG = sEG;
	}

	public String getRTC() {
		return RTC;
	}

	public void setRTC(String rTC) {
		RTC = rTC;
	}

	public String getRTY() {
		return RTY;
	}

	public void setRTY(String rTY) {
		RTY = rTY;
	}

	public String getRD1() {
		return RD1;
	}

	public void setRD1(String rD1) {
		RD1 = rD1;
	}

	public String getRD2() {
		return RD2;
	}

	public void setRD2(String rD2) {
		RD2 = rD2;
	}

	public String getRD3() {
		return RD3;
	}

	public void setRD3(String rD3) {
		RD3 = rD3;
	}

	public String getLAR() {
		return LAR;
	}

	public void setLAR(String lAR) {
		LAR = lAR;
	}

	public String getCXT() {
		return CXT;
	}

	public void setCXT(String cXT) {
		CXT = cXT;
	}

	public List<String> getAMC() {
		return AMC;
	}

	public void setAMC(List<String> aMC) {
		AMC = aMC;
	}

	public List<String> getAMD() {
		return AMD;
	}

	public void setAMD(List<String> aMD) {
		AMD = aMD;
	}

	public List<String> getAMI() {
		return AMI;
	}

	public void setAMI(List<String> aMI) {
		AMI = aMI;
	}

	public List<String> getAMF() {
		return AMF;
	}

	public void setAMF(List<String> aMF) {
		AMF = aMF;
	}

	public Float getRMR() {
		return RMR;
	}

	public void setRMR(Float rMR) {
		RMR = rMR;
	}

	public String getRFQ() {
		return RFQ;
	}

	public void setRFQ(String rFQ) {
		RFQ = rFQ;
	}

	public Float getTRR() {
		return TRR;
	}

	public void setTRR(Float tRR) {
		TRR = tRR;
	}

	public String getRT1() {
		return RT1;
	}

	public void setRT1(String rT1) {
		RT1 = rT1;
	}

	public String getRT2() {
		return RT2;
	}

	public void setRT2(String rT2) {
		RT2 = rT2;
	}

	public String getRT3() {
		return RT3;
	}

	public void setRT3(String rT3) {
		RT3 = rT3;
	}

	public String getRCI() {
		return RCI;
	}

	public void setRCI(String rCI) {
		RCI = rCI;
	}

	public String getFLG() {
		return FLG;
	}

	public void setFLG(String fLG) {
		FLG = fLG;
	}


}

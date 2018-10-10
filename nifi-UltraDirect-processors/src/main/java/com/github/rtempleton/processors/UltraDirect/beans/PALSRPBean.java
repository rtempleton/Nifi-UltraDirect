package com.github.rtempleton.processors.UltraDirect.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.annotations.SerializedName;

public class PALSRPBean implements Serializable{
	
	@SerializedName("PALSRP")
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(PALSRPBean.class);
	
	private Integer SEG;
	private String CHN;
	private String PID;
	private String RPC;
	private String RPI;
	private String MTQ;
	private String RP1;
	private String RP2;
	private String RP3;
	private String CCN;
	private String CUA;
	private String IFT;
	private String AFT;
	private List<String> AMC;
	private List<String> AMI;
	private List<String> AMF;
	private String NCU;
	private String RCU;
	private String TX1;
	private List<String> MKT;
	private List<RTRCRPBean> RTRCRP;
	
	public PALSRPBean(String segment) {
		this.AMC = new ArrayList<String>(6);
		this.AMI = new ArrayList<String>(6);
		this.AMF = new ArrayList<String>(6);
		this.MKT = new ArrayList<String>(6);
		this.RTRCRP = new ArrayList<RTRCRPBean>(25); //up to 25 of these segments per PALSRP
		parseSegment(segment, this);
	}
	
	static void parseSegment(String segment, PALSRPBean bean){
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
				case "RPC":
					bean.RPC = getVal(segments[i]);
					break;
				case "RPI":
					bean.RPI = getVal(segments[i]);
					break;
				case "MTQ":
					bean.MTQ = getVal(segments[i]);
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
				case "CCN":
					bean.CCN = getVal(segments[i]);
					break;
				case "CUA":
					bean.CUA = getVal(segments[i]);
					break;
				case "IFT":
					bean.IFT = getVal(segments[i]);
					break;
				case "AFT":
					bean.AFT = getVal(segments[i]);
					break;
				case "AMC":
					bean.AMC.add(getVal(segments[i]));
					break;
				case "AMI":
					bean.AMI.add(getVal(segments[i]));
					break;
				case "AMF":
					bean.AMF.add(getVal(segments[i]));
					break;
				case "NCU":
					bean.NCU = getVal(segments[i]);
					break;
				case "RCU":
					bean.RCU = getVal(segments[i]);
					break;
				case "TX1":
					bean.TX1 = getVal(segments[i]);
					break;
				case "MKT":
					bean.MKT.add(getVal(segments[i]));
					break;
				default:
					logger.warn("Unknown Segment:" + segments[i]);
			}
		}
	}
	
	static String getVal(String seg){
		return seg.substring(3, seg.length());
	}
	
	public void putRTRCRP(RTRCRPBean bean){
		this.RTRCRP.add(bean);
	}

	public Integer getSEG() {
		return SEG;
	}

	public void setSEG(Integer sEG) {
		SEG = sEG;
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

	public String getMTQ() {
		return MTQ;
	}

	public void setMTQ(String mTQ) {
		MTQ = mTQ;
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

	public String getCCN() {
		return CCN;
	}

	public void setCCN(String cCN) {
		CCN = cCN;
	}

	public String getCUA() {
		return CUA;
	}

	public void setCUA(String cUA) {
		CUA = cUA;
	}

	public String getIFT() {
		return IFT;
	}

	public void setIFT(String iFT) {
		IFT = iFT;
	}

	public String getAFT() {
		return AFT;
	}

	public void setAFT(String aFT) {
		AFT = aFT;
	}

	public List<String> getAMC() {
		return AMC;
	}

	public void setAMC(List<String> aMC) {
		AMC = aMC;
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

	public String getNCU() {
		return NCU;
	}

	public void setNCU(String nCU) {
		NCU = nCU;
	}

	public String getRCU() {
		return RCU;
	}

	public void setRCU(String rCU) {
		RCU = rCU;
	}

	public String getTX1() {
		return TX1;
	}

	public void setTX1(String tX1) {
		TX1 = tX1;
	}

	public List<String> getMKT() {
		return MKT;
	}

	public void setMKT(List<String> mKT) {
		MKT = mKT;
	}

	public List<RTRCRPBean> getRTRCRP() {
		return RTRCRP;
	}

	public void setRTRCRP(List<RTRCRPBean> rTRCRP) {
		RTRCRP = rTRCRP;
	}


	public void putChild(RTRCRPBean child) {
		this.RTRCRP.add(child);
	}
	

}

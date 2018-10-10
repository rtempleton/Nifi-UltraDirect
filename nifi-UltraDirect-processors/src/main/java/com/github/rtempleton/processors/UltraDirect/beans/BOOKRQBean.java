package com.github.rtempleton.processors.UltraDirect.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BOOKRQBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(BOOKRQBean.class);
	
	private String ACT;
	private List<String> AFT;
	private String AID;
	private String ARC;
	private String ART;
	private String APC;
	private String BKS;
	private String CCN;
	private String CHN;
	private String CUA;
	private Integer EBN;
	private List<String> EBP;
	private String FLT;
	private String GRP;
	private String GUT;
	private String GCT;
	private String GCN;
	private String GUE;
	private String GNM;
	private List<String> IFT;
	private String IND;
	private String OTD;
	private String NAM;
	private Integer NAD;
	private Integer NCH;
	private Integer NNT;
	private Integer NPR;
	private Integer NRM;
	private String PHN;
	private String PID;
	private String REM;
	private String RPI;
	private Float RMR;
	private String CUR;
	private String RTY;
	private String RTC;
	private String RPC;
	private String SIN;
	private String TAP;
	private String WCR;
	private String AD1;
	private String T1A;
	private String AD2;
	private String T2A;
	


	public BOOKRQBean(String segment) {
		AFT = new ArrayList<String>();
		EBP = new ArrayList<String>();
		IFT = new ArrayList<String>();
		parseSegment(segment, this);
	}
	
	static void parseSegment(String segment, BOOKRQBean bean){
		String[] segments = segment.split("\\|");
		//skip the first segment since we know it's the type
		for (int i=1;i<segments.length;i++){
			
			switch(segments[i].substring(0, 3)){
				case "ACT":
					bean.ACT = getVal(segments[i]);
					break;
				case "AFT":
					bean.AFT.add(getVal(segments[i]));
					break;
				case "AID":
					bean.AID = getVal(segments[i]);
					break;
				case "ARC":
					bean.ARC = getVal(segments[i]);
					break;
				case "ART":
					bean.ART = getVal(segments[i]);
					break;
				case "APC":
					bean.APC = getVal(segments[i]);
					break;
				case "BKS":
					bean.BKS = getVal(segments[i]);
					break;
				case "CCN":
					bean.CCN = getVal(segments[i]);
					break;
				case "CHN":
					bean.CHN = getVal(segments[i]);
					break;
				case "CUA":
					bean.CUA = getVal(segments[i]);
					break;
				case "EBN":
					bean.EBN = Integer.parseInt(getVal(segments[i]));
					break;
				case "EBP":
					bean.EBP.add(getVal(segments[i]));
					break;
				case "FLT":
					bean.FLT = getVal(segments[i]);
					break;
				case "GRP":
					bean.GRP = getVal(segments[i]);
					break;
				case "GUT":
					bean.GUT = getVal(segments[i]);
					break;
				case "GCT":
					bean.GCT = getVal(segments[i]);
					break;
				case "GCN":
					bean.GCN = getVal(segments[i]);
					break;
				case "GUE":
					bean.GUE = getVal(segments[i]);
					break;
				case "GNM":
					bean.GNM = getVal(segments[i]);
					break;
				case "IFT":
					bean.IFT.add(getVal(segments[i]));
					break;
				case "IND":
					bean.IND = getVal(segments[i]);
					break;
				case "OTD":
					bean.OTD = getVal(segments[i]);
					break;
				case "NAM":
					bean.NAM = getVal(segments[i]);
					break;
				case "NAD":
					bean.NAD = Integer.parseInt(getVal(segments[i]));
					break;
				case "NCH":
					bean.NCH = Integer.parseInt(getVal(segments[i]));
					break;
				case "NNT":
					bean.NNT = Integer.parseInt(getVal(segments[i]));
					break;
				case "NPR":
					bean.NPR = Integer.parseInt(getVal(segments[i]));
					break;
				case "NRM":
					bean.NRM = Integer.parseInt(getVal(segments[i]));
					break;
				case "PHN":
					bean.PHN = getVal(segments[i]);
					break;
				case "PID":
					bean.PID = getVal(segments[i]);
					break;
				case "REM":
					bean.REM = getVal(segments[i]);
					break;
				case "RPI":
					bean.RPI = getVal(segments[i]);
					break;
				case "RMR":
					bean.RMR = Float.parseFloat(getVal(segments[i]));
					break;
				case "CUR":
					bean.CUR = getVal(segments[i]);
					break;
				case "RTY":
					bean.RTY = getVal(segments[i]);
					break;
				case "RPC":
					bean.RPC = getVal(segments[i]);
					break;
				case "SIN":
					bean.SIN = getVal(segments[i]);
					break;
				case "TAP":
					bean.TAP = getVal(segments[i]);
					break;
				case "WCR":
					bean.WCR = getVal(segments[i]);
					break;
				case "AD1":
					bean.AD1 = getVal(segments[i]);
					break;
				case "T1A":
					bean.T1A = getVal(segments[i]);
					break;
				case "AD2":
					bean.AD2 = getVal(segments[i]);
					break;
				case "T2A":
					bean.T2A = getVal(segments[i]);
					break;
				default:
					logger.warn("Unknown Segment:" + segments[i]);
			}
		}
	}
	
	static String getVal(String seg){
		return seg.substring(3, seg.length());
	}

	public String getACT() {
		return ACT;
	}

	public void setACT(String aCT) {
		ACT = aCT;
	}

	public List<String> getAFT() {
		return AFT;
	}

	public void setAFT(List<String> aFT) {
		AFT = aFT;
	}

	public String getAID() {
		return AID;
	}

	public void setAID(String aID) {
		AID = aID;
	}

	public String getARC() {
		return ARC;
	}

	public void setARC(String aRC) {
		ARC = aRC;
	}

	public String getART() {
		return ART;
	}

	public void setART(String aRT) {
		ART = aRT;
	}

	public String getAPC() {
		return APC;
	}

	public void setAPC(String aPC) {
		APC = aPC;
	}

	public String getBKS() {
		return BKS;
	}

	public void setBKS(String bKS) {
		BKS = bKS;
	}

	public String getCCN() {
		return CCN;
	}

	public void setCCN(String cCN) {
		CCN = cCN;
	}

	public String getCHN() {
		return CHN;
	}

	public void setCHN(String cHN) {
		CHN = cHN;
	}

	public String getCUA() {
		return CUA;
	}

	public void setCUA(String cUA) {
		CUA = cUA;
	}

	public Integer getEBN() {
		return EBN;
	}

	public void setEBN(Integer eBN) {
		EBN = eBN;
	}

	public List<String> getEBP() {
		return EBP;
	}

	public void setEBP(List<String> eBP) {
		EBP = eBP;
	}

	public String getFLT() {
		return FLT;
	}

	public void setFLT(String fLT) {
		FLT = fLT;
	}

	public String getGRP() {
		return GRP;
	}

	public void setGRP(String gRP) {
		GRP = gRP;
	}

	public String getGUT() {
		return GUT;
	}

	public void setGUT(String gUT) {
		GUT = gUT;
	}

	public String getGCT() {
		return GCT;
	}

	public void setGCT(String gCT) {
		GCT = gCT;
	}

	public String getGCN() {
		return GCN;
	}

	public void setGCN(String gCN) {
		GCN = gCN;
	}

	public String getGUE() {
		return GUE;
	}

	public void setGUE(String gUE) {
		GUE = gUE;
	}

	public String getGNM() {
		return GNM;
	}

	public void setGNM(String gNM) {
		GNM = gNM;
	}

	public List<String> getIFT() {
		return IFT;
	}

	public void setIFT(List<String> iFT) {
		IFT = iFT;
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

	public String getNAM() {
		return NAM;
	}

	public void setNAM(String nAM) {
		NAM = nAM;
	}

	public Integer getNAD() {
		return NAD;
	}

	public void setNAD(Integer nAD) {
		NAD = nAD;
	}

	public Integer getNCH() {
		return NCH;
	}

	public void setNCH(Integer nCH) {
		NCH = nCH;
	}

	public Integer getNNT() {
		return NNT;
	}

	public void setNNT(Integer nNT) {
		NNT = nNT;
	}

	public Integer getNPR() {
		return NPR;
	}

	public void setNPR(Integer nPR) {
		NPR = nPR;
	}

	public Integer getNRM() {
		return NRM;
	}

	public void setNRM(Integer nRM) {
		NRM = nRM;
	}
	
	public String getPHN() {
		return PHN;
	}

	public void setNRM(String pHN) {
		PHN = pHN;
	}

	public String getPID() {
		return PID;
	}

	public void setPID(String pID) {
		PID = pID;
	}

	public String getREM() {
		return REM;
	}

	public void setREM(String rEM) {
		REM = rEM;
	}

	public String getRPI() {
		return RPI;
	}

	public void setRPI(String rPI) {
		RPI = rPI;
	}

	public Float getRMR() {
		return RMR;
	}

	public void setRMR(Float rMR) {
		RMR = rMR;
	}

	public String getCUR() {
		return CUR;
	}

	public void setCUR(String cUR) {
		CUR = cUR;
	}

	public String getRTY() {
		return RTY;
	}

	public void setRTY(String rTY) {
		RTY = rTY;
	}

	public String getRTC() {
		return RTC;
	}

	public void setRTC(String rTC) {
		RTC = rTC;
	}

	public String getRPC() {
		return RPC;
	}

	public void setRPC(String rPC) {
		RPC = rPC;
	}

	public String getSIN() {
		return SIN;
	}

	public void setSIN(String sIN) {
		SIN = sIN;
	}

	public String getTAP() {
		return TAP;
	}

	public void setTAP(String tAP) {
		TAP = tAP;
	}

	public String getWCR() {
		return WCR;
	}

	public void setWCR(String wCR) {
		WCR = wCR;
	}

	public String getAD1() {
		return AD1;
	}

	public void setAD1(String aD1) {
		AD1 = aD1;
	}

	public String getT1A() {
		return T1A;
	}

	public void setT1A(String t1a) {
		T1A = t1a;
	}

	public String getAD2() {
		return AD2;
	}

	public void setAD2(String aD2) {
		AD2 = aD2;
	}

	public String getT2A() {
		return T2A;
	}

	public void setT2A(String t2a) {
		T2A = t2a;
	}

}

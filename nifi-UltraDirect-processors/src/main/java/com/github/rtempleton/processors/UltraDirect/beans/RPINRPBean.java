package com.github.rtempleton.processors.UltraDirect.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RPINRPBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(RPINRPBean.class);
	
	private String CHN;
	private String PID;
	private String IND;
	private String OTD;
	private String RPC;
	private String RPI;
	private String RPX;
	private String CCN;
	private String CUA;
	private List<String> IFT;
	private List<String> AFT;
	private String NCU;
	private String VSD;
	private String VED;
	private String VDW;
	private String VAD;
	private Integer MLO;
	private Integer XLO;
	private String TXX;
	private String SUR;
	private String SVC;
	private String SRX;
	private String ABR;
	private String GTR;
	private String GTM;
	private String DPR;
	private String DPX;
	private String PPR;
	private String PPX;
	private String CMP;
	private String CPX;
	private String RTC;
	private String PKG;
	private String MIS;
	private List<String> MKT;
	private String DPA;
	

	public RPINRPBean(String segment) {
		IFT = new ArrayList<String>(5);
		AFT = new ArrayList<String>(5);
		MKT = new ArrayList<String>(5);
		parseSegment(segment, this);// TODO Auto-generated constructor stub
	}
	
	static void parseSegment(String segment, RPINRPBean bean){
		String[] segments = segment.split("\\|");
		//skip the first segment since we know it's the type
		for (int i=1;i<segments.length;i++){
			
			switch(segments[i].substring(0, 3)){
				case "CHN":
					bean.CHN = getVal(segments[i]);
					break;
				case "PID":
					bean.PID = getVal(segments[i]);
					break;
				case "IND":
					bean.IND = getVal(segments[i]);
					break;
				case "OTD":
					bean.OTD = getVal(segments[i]);
					break;
				case "RPC":
					bean.RPC = getVal(segments[i]);
					break;
				case "RPI":
					bean.RPI = getVal(segments[i]);
					break;
				case "RPX":
					bean.RPX = getVal(segments[i]);
					break;
				case "CCN":
					bean.CCN = getVal(segments[i]);
					break;
				case "CUA":
					bean.CUA = getVal(segments[i]);
					break;
				case "IFT":
					bean.IFT.add(getVal(segments[i]));
					break;
				case "AFT":
					bean.AFT.add(getVal(segments[i]));
					break;
				case "NCU":
					bean.NCU = getVal(segments[i]);
					break;
				case "VSD":
					bean.VSD = getVal(segments[i]);
					break;
				case "VED":
					bean.VED = getVal(segments[i]);
					break;
				case "VDW":
					bean.VDW = getVal(segments[i]);
					break;
				case "VAD":
					bean.VAD = getVal(segments[i]);
					break;
				case "MLO":
					bean.MLO = Integer.parseInt(getVal(segments[i]));
					break;
				case "XLO":
					bean.XLO = Integer.parseInt(getVal(segments[i]));
					break;
				case "TXX":
					bean.TXX = getVal(segments[i]);
					break;
				case "SUR":
					bean.SUR = getVal(segments[i]);
					break;
				case "SVC":
					bean.SVC = getVal(segments[i]);
					break;
				case "SRX":
					bean.SRX = getVal(segments[i]);
					break;
				case "ABR":
					bean.ABR = getVal(segments[i]);
					break;
				case "GTR":
					bean.GTR = getVal(segments[i]);
					break;
				case "GTM":
					bean.GTM = getVal(segments[i]);
					break;
				case "DPR":
					bean.DPR = getVal(segments[i]);
					break;
				case "DPX":
					bean.DPX = getVal(segments[i]);
					break;
				case "PPR":
					bean.PPR = getVal(segments[i]);
					break;
				case "PPX":
					bean.PPX = getVal(segments[i]);
					break;
				case "CMP":
					bean.CMP = getVal(segments[i]);
					break;
				case "CPX":
					bean.CPX = getVal(segments[i]);
					break;
				case "RTC":
					bean.RTC = getVal(segments[i]);
					break;
				case "PKG":
					bean.PKG = getVal(segments[i]);
					break;
				case "MIS":
					bean.MIS = getVal(segments[i]);
					break;
				case "MKT":
					bean.MKT.add(getVal(segments[i]));
					break;
				case "DPA":
					bean.DPA = getVal(segments[i]);
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

	public String getRPX() {
		return RPX;
	}

	public void setRPX(String rPX) {
		RPX = rPX;
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

	public List<String> getIFT() {
		return IFT;
	}

	public void setIFT(List<String> iFT) {
		IFT = iFT;
	}

	public List<String> getAFT() {
		return AFT;
	}

	public void setAFT(List<String> aFT) {
		AFT = aFT;
	}

	public String getNCU() {
		return NCU;
	}

	public void setNCU(String nCU) {
		NCU = nCU;
	}

	public String getVSD() {
		return VSD;
	}

	public void setVSD(String vSD) {
		VSD = vSD;
	}

	public String getVED() {
		return VED;
	}

	public void setVED(String vED) {
		VED = vED;
	}

	public String getVDW() {
		return VDW;
	}

	public void setVDW(String vDW) {
		VDW = vDW;
	}

	public String getVAD() {
		return VAD;
	}

	public void setVAD(String vAD) {
		VAD = vAD;
	}

	public Integer getMLO() {
		return MLO;
	}

	public void setMLO(Integer mLO) {
		MLO = mLO;
	}

	public Integer getXLO() {
		return XLO;
	}

	public void setXLO(Integer xLO) {
		XLO = xLO;
	}

	public String getTXX() {
		return TXX;
	}

	public void setTXX(String tXX) {
		TXX = tXX;
	}

	public String getSUR() {
		return SUR;
	}

	public void setSUR(String sUR) {
		SUR = sUR;
	}

	public String getSVC() {
		return SVC;
	}

	public void setSVC(String sVC) {
		SVC = sVC;
	}

	public String getSRX() {
		return SRX;
	}

	public void setSRX(String sRX) {
		SRX = sRX;
	}

	public String getABR() {
		return ABR;
	}

	public void setABR(String aBR) {
		ABR = aBR;
	}

	public String getGTR() {
		return GTR;
	}

	public void setGTR(String gTR) {
		GTR = gTR;
	}

	public String getGTM() {
		return GTM;
	}

	public void setGTM(String gTM) {
		GTM = gTM;
	}

	public String getDPR() {
		return DPR;
	}

	public void setDPR(String dPR) {
		DPR = dPR;
	}

	public String getDPX() {
		return DPX;
	}

	public void setDPX(String dPX) {
		DPX = dPX;
	}

	public String getPPR() {
		return PPR;
	}

	public void setPPR(String pPR) {
		PPR = pPR;
	}

	public String getPPX() {
		return PPX;
	}

	public void setPPX(String pPX) {
		PPX = pPX;
	}

	public String getCMP() {
		return CMP;
	}

	public void setCMP(String cMP) {
		CMP = cMP;
	}

	public String getCPX() {
		return CPX;
	}

	public void setCPX(String cPX) {
		CPX = cPX;
	}

	public String getRTC() {
		return RTC;
	}

	public void setRTC(String rTC) {
		RTC = rTC;
	}

	public String getPKG() {
		return PKG;
	}

	public void setPKG(String pKG) {
		PKG = pKG;
	}

	public String getMIS() {
		return MIS;
	}

	public void setMIS(String mIS) {
		MIS = mIS;
	}

	public List<String> getMKT() {
		return MKT;
	}

	public void setMKT(List<String> mKT) {
		MKT = mKT;
	}

	public String getDPA() {
		return DPA;
	}

	public void setDPA(String dPA) {
		DPA = dPA;
	}



}

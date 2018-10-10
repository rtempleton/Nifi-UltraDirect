package com.github.rtempleton.processors.UltraDirect.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PALSRQBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(PALSRQBean.class);
	
	private String GDA;
	private String GDI;
	private String CTY;
	private String CNT;
	private String CHN;
	private String PID;
	private String IND;
	private String OTD;
	private Integer NNT;
	private Float MXR;
	private Float MNR;
	private String RCU;
	private String NCU;
	private List<String> AMC;
	private Integer ROC;
	private Integer NPR;
	private Integer NRM;
	private Integer NBD;
	private String MTQ;
	
	

	public PALSRQBean(String segment) {
		this.AMC = new ArrayList<String>(6);
		parseSegment(segment, this);
	}
	
	static void parseSegment(String segment, PALSRQBean bean){
		String[] segments = segment.split("\\|");
		//skip the first segment since we know it's the type
		for (int i=1;i<segments.length;i++){
			
			switch(segments[i].substring(0, 3)){
				case "GDA":
					bean.GDA = getVal(segments[i]);
					break;
				case "GDI":
					bean.GDI = getVal(segments[i]);
					break;
				case "CTY":
					bean.CTY = getVal(segments[i]);
					break;
				case "CNT":
					bean.CNT = getVal(segments[i]);
					break;
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
				case "NNT":
					bean.NNT = Integer.parseInt(getVal(segments[i]));
					break;
				case "MXR":
					bean.MXR = Float.parseFloat(getVal(segments[i]));
					break;
				case "MNR":
					bean.MNR = Float.parseFloat(getVal(segments[i]));
					break;
				case "RCU":
					bean.RCU = getVal(segments[i]);
					break;
				case "NCU":
					bean.NCU = getVal(segments[i]);
					break;
				case "AMC":
					bean.AMC.add(getVal(segments[i]));
					break;
				case "ROC":
					bean.ROC = Integer.parseInt(getVal(segments[i]));
					break;
				case "NPR":
					bean.NPR = Integer.parseInt(getVal(segments[i]));
					break;
				case "NRM":
					bean.NRM = Integer.parseInt(getVal(segments[i]));
					break;
				case "NBD":
					bean.NBD = Integer.parseInt(getVal(segments[i]));
					break;
				case "MTQ":
					bean.MTQ = getVal(segments[i]);
					break;
				default:
					logger.warn("Unknown Segment:" + segments[i]);
			}
		}
	}
	
	static String getVal(String seg){
		return seg.substring(3, seg.length());
	}

	public String getGDA() {
		return GDA;
	}

	public void setGDA(String gDA) {
		GDA = gDA;
	}

	public String getGDI() {
		return GDI;
	}

	public void setGDI(String gDI) {
		GDI = gDI;
	}

	public String getCTY() {
		return CTY;
	}

	public void setCTY(String cTY) {
		CTY = cTY;
	}

	public String getCNT() {
		return CNT;
	}

	public void setCNT(String cNT) {
		CNT = cNT;
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

	public Integer getNNT() {
		return NNT;
	}

	public void setNNT(Integer nNT) {
		NNT = nNT;
	}

	public Float getMXR() {
		return MXR;
	}

	public void setMXR(Float mXR) {
		MXR = mXR;
	}

	public Float getMNR() {
		return MNR;
	}

	public void setMNR(Float mNR) {
		MNR = mNR;
	}

	public String getRCU() {
		return RCU;
	}

	public void setRCU(String rCU) {
		RCU = rCU;
	}

	public String getNCU() {
		return NCU;
	}

	public void setNCU(String nCU) {
		NCU = nCU;
	}

	public List<String> getAMC() {
		return AMC;
	}

	public void setAMC(List<String> aMC) {
		AMC = aMC;
	}

	public Integer getROC() {
		return ROC;
	}

	public void setROC(Integer rOC) {
		ROC = rOC;
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

	public Integer getNBD() {
		return NBD;
	}

	public void setNBD(Integer nBD) {
		NBD = nBD;
	}

	public String getMTQ() {
		return MTQ;
	}

	public void setMTQ(String mTQ) {
		MTQ = mTQ;
	}


}

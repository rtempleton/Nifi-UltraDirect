package com.github.rtempleton.processors.UltraDirect.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RPINRQBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(RPINRQBean.class);
	
	private String CHN;
	private String PID;
	private String IND;
	private String OTD;
	private Integer NPR;
	private Integer NNT;
	private String RPC;
	private String CCN;
	private String CUA;
	private List<String> IFT;
	private List<String> AFT;
	private String CNF;
	

	public RPINRQBean(String segment) {
		IFT = new ArrayList<String>(5);
		AFT = new ArrayList<String>(5);
		parseSegment(segment, this);
	}
	
	static void parseSegment(String segment, RPINRQBean bean){
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
				case "NPR":
					bean.NPR = Integer.parseInt(getVal(segments[i]));
					break;
				case "NNT":
					bean.NNT = Integer.parseInt(getVal(segments[i]));
					break;
				case "RPC":
					bean.RPC = getVal(segments[i]);
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
				case "CNF":
					bean.CNF = getVal(segments[i]);
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

	public Integer getNPR() {
		return NPR;
	}

	public void setNPR(Integer nPR) {
		NPR = nPR;
	}

	public Integer getNNT() {
		return NNT;
	}

	public void setNNT(Integer nNT) {
		NNT = nNT;
	}

	public String getRPC() {
		return RPC;
	}

	public void setRPC(String rPC) {
		RPC = rPC;
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

	public String getCNF() {
		return CNF;
	}

	public void setCNF(String cNF) {
		CNF = cNF;
	}

}


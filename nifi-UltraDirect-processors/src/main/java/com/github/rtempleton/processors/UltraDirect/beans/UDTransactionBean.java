package com.github.rtempleton.processors.UltraDirect.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.annotations.SerializedName;

public class UDTransactionBean implements Serializable{
	
	@SerializedName("Transaction")
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(UDTransactionBean.class);
	
	//the list of subsegments supported
	public static final List<String> subsegments = Arrays.asList("PALSRP","PALSRQ","AALSRQ","AALSRP","PNLSRP","RPINRQ","RPINRP","BOOKRQ","BOOKRP");
	
	private String segmentType;
	private long ts;
	private String transId;
	private transient boolean initialized = false;
	
	//bean subsegment types
	private AALSRQBean AALSRQ;
	private List<AALSRPBean> AALSRP;
	
	private PALSRQBean PALSRQ;
	private List<PALSRPBean> PALSRP;
	private List<PNLSRPBean> PNLSRP;
	
	private RPINRQBean RPINRQ;
	private RPINRPBean RPINRP;
	
	private BOOKRQBean BOOKRQ;
	private BOOKRPBean BOOKRP;
	
	

	public UDTransactionBean(String segment){
		
		this.segmentType = getVal(segment, "|MTP", "|");
		//If this transaction is attempting to parse a subsegment we don't support, abort early
		if(!subsegments.contains(this.segmentType))
			return;
		this.initialized = true;
		this.ts = Timestamp.valueOf(getVal(segment, "[", "]")).getTime();
		this.transId = getVal(segment, "|MSN", "|");
		
		AALSRP = new ArrayList<AALSRPBean>();
		PALSRP = new ArrayList<PALSRPBean>();
		PNLSRP = new ArrayList<PNLSRPBean>();
		
		parseSegment(segment, this);
	}
	
	static void parseSegment(String segment, UDTransactionBean trans){
		String[] segments = segment.split("\\|\\|");
		//skip the first two segments since we know it's the header data
		for (int i=2;i<segments.length;i++){
			//if there's no occurance of a pipe, it's the end of the segmentlist
			if(-1 == segments[i].indexOf("|"))
				continue;
			String subtype = segments[i].substring(0, segments[i].indexOf("|"));
			
			switch(subtype){
			case "PALSRP":					
				PALSRPBean palsrp = new PALSRPBean(segments[i]);
				trans.PALSRP.add(palsrp);
				break;
			case "RTRCRP": //this is a child subsegment of PALSRP but needs to be processed at this level
				RTRCRPBean rtrcrp = new RTRCRPBean(segments[i]);
				trans.putRTRCRPChild(rtrcrp);
				break;
			case "PALSRQ":
				PALSRQBean palsrq = new PALSRQBean(segments[i]);
				trans.PALSRQ = palsrq;
				break;
			case "AALSRQ":
				AALSRQBean aalsrq = new AALSRQBean(segments[i]);
				trans.AALSRQ = aalsrq;
				break;
			case "AALSRP":
				AALSRPBean aalsrp = new AALSRPBean(segments[i]);
				trans.AALSRP.add(aalsrp);
				break;
			case "PNLSRP":
				PNLSRPBean pnlsrp = new PNLSRPBean(segments[i]);
				trans.PNLSRP.add(pnlsrp);
				break;
			case "RPINRQ":
				RPINRQBean rpinrq = new RPINRQBean(segments[i]);
				trans.RPINRQ = rpinrq;
				break;
			case "RPINRP":
				RPINRPBean rpinrp = new RPINRPBean(segments[i]);
				trans.RPINRP = rpinrp;
				break;
			case "BOOKRQ":
				if (trans.segmentType.equals("BOOKRQ")){ //Unique case because BOOKRP will also contain the original BOOKRQ request. If the tans type is NOT actually BOOKRQ but the segment is found we ignore it
					BOOKRQBean bookrq = new BOOKRQBean(segments[i]);
					trans.BOOKRQ = bookrq;
				}
				break;
			case "BOOKRP":
				BOOKRPBean bookrp = new BOOKRPBean(segments[i]);
				trans.BOOKRP = bookrp;
				break;
			default:
				logger.warn("Unknown Segment:" + segments[i]);
			}
		}
	}
	
	/*
	 * RTRCRPBean are linked to their parent PALSRPBean via the segment number
	 * however, segment order is not guaranteed and some segment numbers get skipped.
	 * When adding the rtrcrp, we need to search the list of parent beans to find it's
	 * match in order to properly nest it
	 */
	private void putRTRCRPChild(RTRCRPBean child){
		for (PALSRPBean parent : PALSRP){
			if(child.getSEG() == parent.getSEG()){
				parent.putChild(child);
				return;
			}
		}
		logger.warn("No parent PALSRPbean was found for this RTRCRPBean segment:" + " " + child.getSEG() + " transId: " + transId);
	}
	
	
	static String getVal(String segment, String first, String last){
		int start = segment.indexOf(first);
		int end = segment.indexOf(last, start + first.length());
		return segment.substring(start + first.length(), end);
	}
	

	public boolean isInitialized(){
		return this.initialized;
	}

	public String getSegmentType() {
		return segmentType;
	}

	public long getTs() {
		return ts;
	}

	public String getTransId() {
		return transId;
	}

	public AALSRQBean getAALSRQ() {
		return AALSRQ;
	}

	public List<AALSRPBean> getAALSRP() {
		return AALSRP;
	}

	public PALSRQBean getPALSRQ() {
		return PALSRQ;
	}

	public List<PALSRPBean> getPALSRP() {
		return PALSRP;
	}

	public List<PNLSRPBean> getPNLSRP() {
		return PNLSRP;
	}

	public RPINRQBean getRPINRQ() {
		return RPINRQ;
	}

	public RPINRPBean getRPINRP() {
		return RPINRP;
	}

	public BOOKRQBean getBOOKRQ() {
		return BOOKRQ;
	}

	public BOOKRPBean getBOOKRP() {
		return BOOKRP;
	}
		

}

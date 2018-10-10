package com.github.rtempleton.processors.UltraDirect.denormalizers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.nifi.flowfile.FlowFile;
import org.apache.nifi.processor.ProcessContext;
import org.apache.nifi.processor.ProcessSession;
import org.apache.nifi.processor.io.InputStreamCallback;
import org.apache.nifi.processor.io.OutputStreamCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.rtempleton.processors.UltraDirect.AMFDeNormalizer;
import com.github.rtempleton.processors.UltraDirect.beans.BOOKRQBean;
import com.github.rtempleton.processors.UltraDirect.beans.BOOKRQ_Flat;
import com.github.rtempleton.processors.UltraDirect.beans.UDTransactionBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BOOKRQdenorm {

	private static final Gson gson = new GsonBuilder().create();
	private static final Logger logger = LoggerFactory.getLogger(BOOKRQdenorm.class);

	public BOOKRQdenorm(ProcessContext context, ProcessSession session, FlowFile sourceFlowFile) {

		try{
			session.read(sourceFlowFile, new InputStreamCallback(){

				@Override
				public void process(InputStream in) throws IOException {
					BufferedReader br = new BufferedReader(new InputStreamReader(in));
					for (String line = br.readLine(); line != null; line = br.readLine()){
						final UDTransactionBean trans = gson.fromJson(line, UDTransactionBean.class);
						if(!trans.getSegmentType().equals("BOOKRQ")){
							//will need to add OutputStreamCallback to route failed records?
							logger.error("BOOKRQdenorm found wrong segment type of: " + trans.getSegmentType() + ". skipping record");
							continue;
						}

						BOOKRQBean bean = trans.getBOOKRQ();
						BOOKRQ_Flat denorm = new BOOKRQ_Flat();
						denorm.setTs(trans.getTs());
						denorm.setTransId(trans.getTransId());
						if(bean==null)
							return;
						denorm.setAct(bean.getACT());
						denorm.setAd1(bean.getAD1());
						denorm.setAd2(bean.getAD2());
						denorm.setAft(UDUtils.flattenArray(bean.getAFT()));
						denorm.setAid(bean.getAID());
						denorm.setArc(bean.getARC());
						denorm.setArt(bean.getART());
						denorm.setBks(bean.getBKS());
						denorm.setCcn(bean.getCCN());
						denorm.setChn(bean.getCHN());
						denorm.setCua(bean.getCUA());
						denorm.setCur(bean.getCUR());
						denorm.setEbn(bean.getEBN());
						denorm.setEbp(UDUtils.flattenArray(bean.getEBP()));
						denorm.setFlt(bean.getFLT());
						denorm.setGcn(bean.getGCN());
						denorm.setGct(bean.getGCT());
						denorm.setGnm(bean.getGNM());
						denorm.setGrp(bean.getGRP());
						denorm.setGue(bean.getGUE());
						denorm.setGut(bean.getGUT());
						denorm.setIft(UDUtils.flattenArray(bean.getIFT()));
						denorm.setInd(bean.getIND());
						denorm.setNad(bean.getNAD());
						denorm.setNam(bean.getNAM());
						denorm.setNch(bean.getNCH());
						denorm.setNnt(bean.getNNT());
						denorm.setNpr(bean.getNPR());
						denorm.setNrm(bean.getNRM());
						denorm.setOtd(bean.getOTD());
						denorm.setPhn(bean.getPHN());
						denorm.setPid(bean.getPID());
						denorm.setRem(bean.getREM());
						denorm.setRmr(bean.getRMR());
						denorm.setRpc(bean.getRPC());
						denorm.setRpi(bean.getRPI());
						denorm.setRtc(bean.getRTC());
						denorm.setRty(bean.getRTY());
						denorm.setSin(bean.getSIN());
						denorm.setT1a(bean.getT1A());
						denorm.setT2a(bean.getT2A());
						denorm.setTap(bean.getTAP());

						FlowFile targetFlowFile = session.create(sourceFlowFile);
						targetFlowFile = session.write(targetFlowFile, new OutputStreamCallback() {
							@Override
							public void process(OutputStream out) throws IOException {
								out.write(gson.toJson(denorm).toString().getBytes());
								out.flush();

							}
						});
						targetFlowFile=session.putAttribute(targetFlowFile, "segmentType", trans.getSegmentType());
						session.transfer(targetFlowFile, AMFDeNormalizer.REL_SUCCESS);
					}

				}

			});

		}catch(Exception e){
			session.transfer(session.clone(sourceFlowFile), AMFDeNormalizer.REL_FAILURE);
		}
	}

}

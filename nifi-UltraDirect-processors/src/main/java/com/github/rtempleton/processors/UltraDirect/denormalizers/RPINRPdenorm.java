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
import com.github.rtempleton.processors.UltraDirect.beans.RPINRPBean;
import com.github.rtempleton.processors.UltraDirect.beans.RPINRP_Flat;
import com.github.rtempleton.processors.UltraDirect.beans.UDTransactionBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RPINRPdenorm {

	private static final Gson gson = new GsonBuilder().create();
	private static final Logger logger = LoggerFactory.getLogger(RPINRPdenorm.class);

	public RPINRPdenorm(ProcessContext context, ProcessSession session, FlowFile sourceFlowFile) {
		try{
			session.read(sourceFlowFile, new InputStreamCallback(){

				@Override
				public void process(InputStream in) throws IOException {
					BufferedReader br = new BufferedReader(new InputStreamReader(in));
					for (String line = br.readLine(); line != null; line = br.readLine()){
						final UDTransactionBean trans = gson.fromJson(line, UDTransactionBean.class);
						if(!trans.getSegmentType().equals("RPINRP")){
							//will need to add OutputStreamCallback to route failed records?
							logger.error("RPINRPdenorm found wrong segment type of: " + trans.getSegmentType() + ". skipping record");
							continue;
						}

						RPINRP_Flat denorm = new RPINRP_Flat();
						RPINRPBean bean = trans.getRPINRP();
						if(bean==null)
							return;

						denorm.setTs(trans.getTs());
						denorm.setTransId(trans.getTransId());
						denorm.setAbr(bean.getABR());
						denorm.setAft(UDUtils.flattenArray(bean.getAFT()));
						denorm.setCcn(bean.getCCN());
						denorm.setChn(bean.getCHN());
						denorm.setCmp(bean.getCMP());
						denorm.setCpx(bean.getCPX());
						denorm.setCua(bean.getCUA());
						denorm.setDpa(bean.getDPA());
						denorm.setDpr(bean.getDPR());
						denorm.setDpx(bean.getDPX());
						denorm.setGtm(bean.getGTM());
						denorm.setGtr(bean.getGTR());
						denorm.setIft(UDUtils.flattenArray(bean.getIFT()));
						denorm.setInd(bean.getIND());
						denorm.setMis(bean.getMIS());
						denorm.setMkt(UDUtils.flattenArray(bean.getMKT()));
						denorm.setMlo(bean.getMLO());
						denorm.setNcu(bean.getNCU());
						denorm.setOtd(bean.getOTD());
						denorm.setPid(bean.getPID());
						denorm.setPkg(bean.getPKG());
						denorm.setPpr(bean.getPPR());
						denorm.setPpx(bean.getPPX());
						denorm.setRpc(bean.getRPC());
						denorm.setRpi(bean.getRPI());
						denorm.setRpx(bean.getRPX());
						denorm.setRtc(bean.getRTC());
						denorm.setSrx(bean.getSRX());
						denorm.setSur(bean.getSUR());
						denorm.setSvc(bean.getSVC());

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

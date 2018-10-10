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
import com.github.rtempleton.processors.UltraDirect.beans.PALSRPBean;
import com.github.rtempleton.processors.UltraDirect.beans.PALSRP_Flat;
import com.github.rtempleton.processors.UltraDirect.beans.RTRCRPBean;
import com.github.rtempleton.processors.UltraDirect.beans.UDTransactionBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PALSRPdenorm {

	private static final Gson gson = new GsonBuilder().create();
	private static final Logger logger = LoggerFactory.getLogger(PALSRPdenorm.class);

	public PALSRPdenorm(ProcessContext context, ProcessSession session, FlowFile sourceFlowFile) {

		try{
			session.read(sourceFlowFile, new InputStreamCallback(){

				@Override
				public void process(InputStream in) throws IOException {
					BufferedReader br = new BufferedReader(new InputStreamReader(in));
					for (String line = br.readLine(); line != null; line = br.readLine()){
						final UDTransactionBean trans = gson.fromJson(line, UDTransactionBean.class);
						if(!trans.getSegmentType().equals("PALSRP")){
							//will need to add OutputStreamCallback to route failed records?
							logger.error("PALSRPdenorm found wrong segment type of: " + trans.getSegmentType() + ". skipping record");
							continue;
						}

						PALSRP_Flat denorm = new PALSRP_Flat();
						denorm.setTs(trans.getTs());
						denorm.setTransId(trans.getTransId());
						for (PALSRPBean bean : trans.getPALSRP()){
							denorm.setSeg(bean.getSEG());
							denorm.setChn(bean.getCHN());
							denorm.setPid(bean.getPID());
							denorm.setRpc(bean.getRPC());
							denorm.setRpi(bean.getRPI());
							denorm.setMtq(bean.getMTQ());
							denorm.setRp1(bean.getRP1());
							denorm.setRp2(bean.getRP2());
							denorm.setRp3(bean.getRP3());
							denorm.setCcn(bean.getCCN());
							denorm.setCua(bean.getCUA());
							denorm.setIft(bean.getIFT());
							denorm.setAft(bean.getAFT());
							denorm.setAmc(UDUtils.flattenArray(bean.getAMC()));
							denorm.setAmi(UDUtils.flattenArray(bean.getAMI()));
							denorm.setAmf(UDUtils.flattenArray(bean.getAMF()));
							denorm.setNcu(bean.getNCU());
							denorm.setRcu(bean.getRCU());
							denorm.setTx1(bean.getTX1());
							denorm.setMkt(UDUtils.flattenArray(bean.getMKT()));

							for (RTRCRPBean child : bean.getRTRCRP()){
								denorm.setRtc(child.getRTC());
								denorm.setRty(child.getRTY());
								denorm.setRd1(child.getRD1());
								denorm.setRd2(child.getRD2());
								denorm.setRd3(child.getRD3());
								denorm.setLar(child.getLAR());
								denorm.setCxt(child.getCXT());
								denorm.setRamc(UDUtils.flattenArray(child.getAMC()));
								denorm.setRamd(UDUtils.flattenArray(child.getAMD()));
								denorm.setRami(UDUtils.flattenArray(child.getAMI()));
								denorm.setRamf(UDUtils.flattenArray(child.getAMF()));
								denorm.setRmr(child.getRMR());
								denorm.setRfq(child.getRFQ());
								denorm.setTrr(child.getTRR());
								denorm.setRt1(child.getRT1());
								denorm.setRt2(child.getRT2());
								denorm.setRt3(child.getRT3());
								denorm.setRci(child.getRCI());
								denorm.setFlg(child.getFLG());


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


					}

				}

			});

		}catch(Exception e){
			session.transfer(session.clone(sourceFlowFile), AMFDeNormalizer.REL_FAILURE);
		}
	}

}

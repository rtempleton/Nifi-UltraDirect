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
import com.github.rtempleton.processors.UltraDirect.beans.BOOKRPBean;
import com.github.rtempleton.processors.UltraDirect.beans.BOOKRP_Flat;
import com.github.rtempleton.processors.UltraDirect.beans.UDTransactionBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BOOKRPdenorm {

	private static final Gson gson = new GsonBuilder().create();
	private static final Logger logger = LoggerFactory.getLogger(BOOKRPdenorm.class);

	public BOOKRPdenorm(ProcessContext context, ProcessSession session, FlowFile sourceFlowFile) {

		try{
			session.read(sourceFlowFile, new InputStreamCallback(){

				@Override
				public void process(InputStream in) throws IOException {
					BufferedReader br = new BufferedReader(new InputStreamReader(in));
					for (String line = br.readLine(); line != null; line = br.readLine()){
						final UDTransactionBean trans = gson.fromJson(line, UDTransactionBean.class);
						if(!trans.getSegmentType().equals("BOOKRP")){
							//will need to add OutputStreamCallback to route failed records?
							logger.error("BOOKRPdenorm found wrong segment type of: " + trans.getSegmentType() + ". skipping record");
							continue;
						}

						BOOKRPBean bean = trans.getBOOKRP();
						if(bean==null)
							return;
						BOOKRP_Flat denorm = new BOOKRP_Flat();
						denorm.setTs(trans.getTs());
						denorm.setTransId(trans.getTransId());
						denorm.setBkr(bean.getBKR());
						denorm.setBst(bean.getBST());
						denorm.setCan(bean.getCAN());
						denorm.setCnf(bean.getCNF());
						denorm.setCur(bean.getCUR());
						denorm.setGqi(bean.getGQI());
						denorm.setNam(bean.getNAM());
						denorm.setRty(bean.getRTY());

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

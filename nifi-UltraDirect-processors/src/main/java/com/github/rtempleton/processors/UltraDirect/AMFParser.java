/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.rtempleton.processors.UltraDirect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.nifi.annotation.behavior.ReadsAttribute;
import org.apache.nifi.annotation.behavior.ReadsAttributes;
import org.apache.nifi.annotation.behavior.WritesAttribute;
import org.apache.nifi.annotation.behavior.WritesAttributes;
import org.apache.nifi.annotation.documentation.CapabilityDescription;
import org.apache.nifi.annotation.documentation.SeeAlso;
import org.apache.nifi.annotation.documentation.Tags;
import org.apache.nifi.annotation.lifecycle.OnScheduled;
import org.apache.nifi.components.PropertyDescriptor;
import org.apache.nifi.flowfile.FlowFile;
import org.apache.nifi.processor.AbstractProcessor;
import org.apache.nifi.processor.ProcessContext;
import org.apache.nifi.processor.ProcessSession;
import org.apache.nifi.processor.ProcessorInitializationContext;
import org.apache.nifi.processor.Relationship;
import org.apache.nifi.processor.exception.ProcessException;
import org.apache.nifi.processor.io.InputStreamCallback;
import org.apache.nifi.processor.io.OutputStreamCallback;

import com.github.rtempleton.processors.UltraDirect.beans.UDTransactionBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Tags({"UltraDirect"})
@CapabilityDescription("UltraDirect AMF Log Parser. Converts log entries into JSON objects.")
@SeeAlso({})
@ReadsAttributes({@ReadsAttribute(attribute="", description="")})
@WritesAttributes({@WritesAttribute(attribute="segmentType", description="The type of AMF segment this document relates to. Usable by RouteOnAttribute to group ")})
public class AMFParser extends AbstractProcessor {

	public static final Relationship REL_SUCCESS = new Relationship.Builder()
			.name("success")
			.description("Any AMF records which were successfully parsed.")
			.build();

	public static final Relationship REL_FAILURE = new Relationship.Builder()
			.name("failure")
			.description("AMF records which were not parsed.")
			.build();

	private List<PropertyDescriptor> descriptors;

	private Set<Relationship> relationships;

	private static final Gson gson = new GsonBuilder().create();

	@Override
	protected void init(final ProcessorInitializationContext context) {
		final List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
		//		descriptors.add(MY_PROPERTY);
		this.descriptors = Collections.unmodifiableList(descriptors);

		final Set<Relationship> relationships = new HashSet<Relationship>();
		relationships.add(REL_SUCCESS);
		relationships.add(REL_FAILURE);
		this.relationships = Collections.unmodifiableSet(relationships);
	}

	@Override
	public Set<Relationship> getRelationships() {
		return this.relationships;
	}

	@Override
	public final List<PropertyDescriptor> getSupportedPropertyDescriptors() {
		return descriptors;
	}

	@OnScheduled
	public void onScheduled(final ProcessContext context) {

	}

	@Override
	public void onTrigger(final ProcessContext context, final ProcessSession session) throws ProcessException {
		FlowFile sourceFlowFile = session.get();
		if ( sourceFlowFile == null ) {
			return;
		}

		/*
		 * The sourceFlowFile may be composed of more than one line which is a transaction in the UD AMF file.
		 * We need to read each line in the source file and parse it
		 */
		session.read(sourceFlowFile, new InputStreamCallback() {

			@Override
			public void process(InputStream in) throws IOException {
				BufferedReader br=new BufferedReader(new InputStreamReader(in));
				for (String line = br.readLine(); line != null; line = br.readLine()){ 
					UDTransactionBean trans = new UDTransactionBean(line); 
					FlowFile targetFlowFile = session.create(sourceFlowFile);
					//if the trans is inited, then it was successfully parsed so we push it's JSON body out on the SUCCESS relationship
					if(trans.isInitialized()){
						targetFlowFile = session.write(targetFlowFile, new OutputStreamCallback() {

							@Override
							public void process(OutputStream out) throws IOException {
								out.write(gson.toJson(trans).toString().getBytes());
								out.flush();
							}
						});
						targetFlowFile=session.putAttribute(targetFlowFile, "segmentType", trans.getSegmentType());
						session.transfer(targetFlowFile, REL_SUCCESS);

					}else{
						//If trans was NOT inited, this was an unrecognized segment which we want to push out on the FAILURE relationship	
						String badSeg = line;
						targetFlowFile = session.write(targetFlowFile, new OutputStreamCallback() {

							@Override
							public void process(OutputStream out) throws IOException {
								out.write(badSeg.getBytes());
								out.flush();
							}
						});
						targetFlowFile= session.putAttribute(targetFlowFile, "segmentType", trans.getSegmentType());
						session.transfer(targetFlowFile, REL_FAILURE);
					}
				}
			}
		});
		session.remove(sourceFlowFile);

	}

}

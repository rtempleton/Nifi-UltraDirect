package com.github.rtempleton.processors.UltraDirect;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.rtempleton.processors.UltraDirect.denormalizers.AALSRPdenorm;
import com.github.rtempleton.processors.UltraDirect.denormalizers.AALSRQdenorm;
import com.github.rtempleton.processors.UltraDirect.denormalizers.BOOKRPdenorm;
import com.github.rtempleton.processors.UltraDirect.denormalizers.BOOKRQdenorm;
import com.github.rtempleton.processors.UltraDirect.denormalizers.PALSRPdenorm;
import com.github.rtempleton.processors.UltraDirect.denormalizers.PALSRQdenorm;
import com.github.rtempleton.processors.UltraDirect.denormalizers.PNLSRPdenorm;
import com.github.rtempleton.processors.UltraDirect.denormalizers.RPINRPdenorm;
import com.github.rtempleton.processors.UltraDirect.denormalizers.RPINRQdenorm;


@Tags({"UltraDirect"})
@CapabilityDescription("UDTransaction record denormalizer. Converts hierarchical output record from AMFparser to denormalized flattend records.")
@SeeAlso({})
@ReadsAttributes({@ReadsAttribute(attribute="", description="")})
@WritesAttributes({@WritesAttribute(attribute="", description="")})
public class AMFDeNormalizer extends AbstractProcessor {
	
	
	public static final Relationship REL_SUCCESS = new Relationship.Builder()
			.name("success")
			.description("AMF records which were successfully flattened.")
			.build();
	
	public static final Relationship REL_FAILURE = new Relationship.Builder()
			.name("failure")
			.description("AMF records which encountered an error during flattening.")
			.build();
	
	public static final Relationship REL_ORIGINAL = new Relationship.Builder()
			.name("original")
			.description("The original hierarchical AMF transaction record.")
			.build();

	
	private List<PropertyDescriptor> descriptors;
	private Set<Relationship> relationships;
	private static final Logger logger = LoggerFactory.getLogger(AMFDeNormalizer.class);

	@Override
	protected void init(final ProcessorInitializationContext context) {
		final List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
		this.descriptors = Collections.unmodifiableList(descriptors);

		final Set<Relationship> relationships = new HashSet<Relationship>();
		relationships.add(REL_SUCCESS);
		relationships.add(REL_FAILURE);
		relationships.add(REL_ORIGINAL);
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
	public void onTrigger(ProcessContext context, ProcessSession session) throws ProcessException {
		FlowFile sourceFlowFile = session.get();
		if ( sourceFlowFile == null ) {
			return;
		}
		
		switch(sourceFlowFile.getAttribute("segmentType")){
		
			case "AALSRP":
				new AALSRPdenorm(context, session, sourceFlowFile);
				break;
			case "AALSRQ":
				new AALSRQdenorm(context, session, sourceFlowFile);
				break;
			case "BOOKRP":
				new BOOKRPdenorm(context, session, sourceFlowFile);
				break;
			case "BOOKRQ":
				new BOOKRQdenorm(context, session, sourceFlowFile);
				break;
			case "PALSRP":
				new PALSRPdenorm(context, session, sourceFlowFile);
				break;
			case "PALSRQ":
				new PALSRQdenorm(context, session, sourceFlowFile);
				break;
			case "PNLSRP":
				new PNLSRPdenorm(context, session, sourceFlowFile);
				break;
			case "RPINRP":
				new RPINRPdenorm(context, session, sourceFlowFile);
				break;
			case "RPINRQ":
				new RPINRQdenorm(context, session, sourceFlowFile);
				break;
			default:
				logger.error("Unknown Segment type: " + context.getProperty("SEGTYPE_PROPERTY").toString() + ". Skipping record.");
				break;
		
		}

		
		session.transfer(sourceFlowFile, REL_ORIGINAL);

	}

}

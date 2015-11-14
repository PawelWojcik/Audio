package net.pwojcik.audio.broadcast;

import java.util.Collection;
import java.util.Optional;

import net.pwojcik.audio.flowdata.FlowData;
import net.pwojcik.audio.flowdata.FlowHandler;

/**
 * Interface that describes unit which want to take part in communication in
 * application. We consider {@link net.pwojcik.audio.module.Module} or
 * {@link net.pwojcik.audio.segment.Segment} as "unit". Various kinds of data
 * can be passed between BroadcastParticipants only if this data is valid
 * subclass of {@link net.pwojcik.audio.flowdata.FlowData}.
 * 
 * @author Pawel Wojcik
 * @version 1.0
 */
public interface BroadcastParticipant {

	/**
	 * Returns types of messages which BroadcastParticipant wants to subscribe.
	 * @return collection of subscribed data types
	 */
	Collection<String> getObservedFlowTypes();
	
	/**
	 * Method for choosing handler of given flowData from given unit.
	 * @param data data to handle
	 * @return handler
	 */
	FlowHandler handleData(FlowData data);
	
	/**
	 * Method for registering primary broadcaster.
	 * @param primaryBroadcaster broadcaster
	 */
	void visit(Broadcaster primaryBroadcaster);
	
	/**
	 * Returns names of classes which instances can be provided by this participant.
	 * @return collection of provided resources
	 */
	Collection<String> getProvidedResources();

	/**
	 * Method provides global instance of variable defined by class parameter.
	 * @param classRepresentation class of required type
	 */
	<P> Optional<P> provide(Class<P> classRepresentation);

}

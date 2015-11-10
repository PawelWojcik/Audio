package net.pwojcik.audio.broadcast;

import java.util.Collection;

import net.pwojcik.audio.flowdata.FlowData;
import net.pwojcik.audio.flowdata.FlowDataType;

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
	Collection<FlowDataType> getObservedFlowTypes();

	/**
	 * Method for handling received message.
	 * @param data received message
	 */
	void handleData(FlowData data);

	/**
	 * Method for registering primary broadcaster.
	 * @param primaryBroadcaster broadcaster
	 */
	void visit(Broadcaster primaryBroadcaster);

}

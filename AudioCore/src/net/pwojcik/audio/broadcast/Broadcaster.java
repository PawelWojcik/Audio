package net.pwojcik.audio.broadcast;

import java.util.Collection;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import net.pwojcik.audio.flowdata.FlowData;
import net.pwojcik.audio.flowdata.FlowDataType;

/**
 * Class responsible for handling modules' broadcasted messages and adressing
 * them to proper destination.
 * 
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class Broadcaster {

	private Multimap<FlowDataType, BroadcastParticipant> receivers;

	public Broadcaster() {
		receivers = ArrayListMultimap.create();
	}

	/**
	 * Method registers all units which are capable of sending and receiving
	 * messages. Given collection of units is processed that way it stores
	 * information about each unit's preferences about subscribed channels.
	 * @param broadcastParticipants collection of units which take part in communication
	 */
	public void registerParticipants(Collection<BroadcastParticipant> broadcastParticipants) {
		for (BroadcastParticipant participant : broadcastParticipants) {
			Collection<FlowDataType> observedTypes = participant.getObservedFlowTypes();
			for (FlowDataType type : observedTypes) {
				receivers.put(type, participant);
			}
			participant.visit(this);
		}
	}

	/**
	 * Method used by units which want to send message.
	 * <p>Use case:</p>
	 * <ol> 
	 * <li>Sender is calling <code>broadcastData(...)</code> method.</li>
	 * <li>Method is finding units which are interested in receiving given kind of messages.</li>
	 * <li>Message is being sent to these units.</li>
	 * </ol>
	 * @param data wrapped message
	 */
	public void broadcastData(FlowData data) {
		FlowDataType dataType = data.getType();
		Collection<BroadcastParticipant> broadcastReceivers = receivers.get(dataType);
		for (BroadcastParticipant participant : broadcastReceivers) {
			participant.handleData(data);
		}
	}

}

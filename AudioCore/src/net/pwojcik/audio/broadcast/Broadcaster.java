package net.pwojcik.audio.broadcast;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import net.pwojcik.audio.exception.ProvidedResourceException;
import net.pwojcik.audio.flowdata.FlowData;
import net.pwojcik.audio.flowdata.FlowHandler;

/**
 * Class responsible for handling modules' broadcasted messages and adressing
 * them to proper destination.
 * 
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class Broadcaster {

	private Multimap<String, BroadcastParticipant> receivers;
	private Map<String, BroadcastParticipant> providers;

	public Broadcaster() {
		receivers = ArrayListMultimap.create();
		providers = new HashMap<>();
	}

	/**
	 * Method registers all units which are capable of sending and receiving
	 * messages. Given collection of units is processed that way it stores
	 * information about each unit's preferences about subscribed channels.
	 * 
	 * @param broadcastParticipants
	 *            collection of units which take part in communication
	 */
	public void registerParticipants(Collection<BroadcastParticipant> broadcastParticipants) {
		for (BroadcastParticipant participant : broadcastParticipants) {
			processReceivers(participant);
			processProviders(participant);
			participant.visit(this);
		}
	}

	private void processProviders(BroadcastParticipant participant) {
		Collection<String> providedTypes = participant.getProvidedResources();
		for(String type : providedTypes) {
			providers.put(type, participant);
		}
	}

	private void processReceivers(BroadcastParticipant participant) {
		Collection<String> observedTypes = participant.getObservedFlowTypes();
		for (String type : observedTypes) {
			receivers.put(type, participant);
		}
	}

	/**
	 * Method used by units which want to send message.
	 * <p>
	 * Use case:
	 * </p>
	 * <ol>
	 * <li>Sender is calling <code>broadcastData(...)</code> method.</li>
	 * <li>Method is finding units which are interested in receiving given kind
	 * of messages.</li>
	 * <li>Message is being sent to these units to achieve
	 * {@linkplain net.pwojcik.audio.flowdata.FlowHandler} instance.</li>
	 * <li>Message is being sent to proper handler.</li>
	 * </ol>
	 * @param data wrapped message
	 */
	public void broadcastData(FlowData data) {
		String dataType = data.getType();
		Collection<BroadcastParticipant> broadcastReceivers = receivers.get(dataType);
		for (BroadcastParticipant participant : broadcastReceivers) {
			FlowHandler handler = participant.handleData(data);
			handler.handle(data);
		}
	}

	/**
	 * Method used by units to get instance of class which should be singleton
	 * in whole application.
	 * @param classRepresentation description of class
	 * @return instance of object
	 */
	public <T> T request(Class<T> classRepresentation) {
		String className = classRepresentation.getName();
		if (providers.containsKey(className)) {
			BroadcastParticipant provider = providers.get(className);
			Optional<T> requestedData = provider.provide(classRepresentation);
			if(!requestedData.isPresent()) {
				throw new ProvidedResourceException(ProvidedResourceException.RESOURCE_NOT_PROVIDED);
			}
			return requestedData.get();
		}
		throw new ProvidedResourceException(ProvidedResourceException.RESOURCE_NOT_SUPPORTED);
	}
}

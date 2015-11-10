package net.pwojcik.audio.flowdata;

/**
 * Container for data sent between {@link net.pwojcik.audio.broadcast.BroadcastParticipant} instances.
 * @author Pawel Wojcik
 * @version 1.0
 */
public interface FlowData {
	
	/**
	 * Returns type of transferred data.
	 * @return type of contained data
	 */
	String getType();
	
	/**
	 * Checks whether FlowData is instance of given class.
	 * @param classRepresentation class to compare
	 */
	boolean isInstanceOf(Class<?> classRepresentation);
	
}

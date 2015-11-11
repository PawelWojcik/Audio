package net.pwojcik.audio.flowdata;

/**
 * Representation of unit which is responsible for providing behaviour of
 * component or module when broadcasted message arrives.
 * @author Pawel Wojcik
 * @version 1.0
 */
public interface FlowHandler {

	/**
	 * Method containing logic of component/module.
	 * @param data broadcasted message
	 */
	void handle(FlowData data);
}

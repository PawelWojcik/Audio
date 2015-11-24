package net.pwojcik.audio.module;

import java.util.Collection;

import net.pwojcik.audio.broadcast.BroadcastParticipant;

/**
 * Module is considered as unit which provides new functionality in application.
 * For example, Equalizer or Format Connverter can be such a module.
 * 
 * @author Pawel Wojcik
 * @version 1.0
 */
public interface Module extends BroadcastParticipant {

	/**
	 * Method runs additional initializations after Module is created and
	 * obtained Broadcaster instance.
	 */
	void postConstruct();

	/**
	 * Determines type of module.
	 * 
	 * @return type of module
	 */
	String getType();

	/**
	 * Defines type of module.
	 * 
	 * @param type
	 *            new type of module
	 */
	void setType(String type);

	/**
	 * Checks whether module is the one which is loaded and seen at application
	 * startup.
	 * 
	 * @return is module default
	 */
	boolean isApplicationDefaultModule();

	/**
	 * Sets flag determining whether module is the one which is loaded and seen
	 * at application startup.
	 * @param isDefault true if module is default module
	 */
	void setApplicationDefaultModule(boolean isDefault);

	/**
	 * Sets new collection of observed Flow Data types. Collection holds class
	 * names with full package names.
	 * 
	 * @param observedTypes
	 *            collection of types
	 */
	void setObservedFlowTypes(Collection<String> observedTypes);

	/**
	 * Sets new collection of resources that module can provide.
	 * 
	 * @param resources
	 *            new collection of resources
	 */
	void setProvidedResources(Collection<String> resources);

	/**
	 * All specific GUI information related to module.
	 * 
	 * @return gui-related data
	 */
	CanvasDataContainer getCanvasDataContainer();

}

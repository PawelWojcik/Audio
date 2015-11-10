package net.pwojcik.audio.module;

import java.util.Collection;
import java.util.Optional;

import javafx.scene.layout.Pane;
import net.pwojcik.audio.broadcast.BroadcastParticipant;
import net.pwojcik.audio.tree.TreeDescription;

/**
 * Module is considered as unit which provides new functionality in application.
 * For example, Equalizer or Format Connverter can be such a module.
 * 
 * @author Pawel Wojcik
 * @version 1.0
 */
public interface Module extends BroadcastParticipant {

	/**
	 * Returns description of tree node representing module. If representation
	 * on tree is not necessary, then empty Optional value should be returned.
	 * @return decription of tree node for this module
	 */
	Optional<TreeDescription> getTreeDescription();

	/**
	 * Determines type of module.
	 * @return type of module
	 */
	String getType();

	/**
	 * Defines type of module.
	 * @param type new type of module
	 */
	void setType(String type);
	
	/**
	 * Sets new collection of observed Flow Data types. 
	 * Collection holds class names with full package names.
	 * @param observedTypes collection of types
	 */
	void setObservedFlowTypes(Collection<String> observedTypes);
	
	/**
	 * Returns GUI representation for that module.
	 * @return panel for module
	 */
	Pane getCanvas();
	
}

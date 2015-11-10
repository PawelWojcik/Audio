package net.pwojcik.audio.module;

import java.util.Optional;

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
	ModuleType getType();

}

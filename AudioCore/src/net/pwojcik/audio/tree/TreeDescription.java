package net.pwojcik.audio.tree;

import java.util.Optional;

import net.pwojcik.audio.module.ModuleType;

/**
 * Description of GUI tree node. Provides all necessary information about
 * coupling with specific {@link net.pwojcik.audio.module.Module} instance and
 * about general hierarchy in tree.
 * 
 * @author Pawel Wojcik
 * @version 1.0
 */
public interface TreeDescription {

	/**
	 * Returns label of node.
	 * @return label of tree node
	 */
	String getTreeLabel();
	
	/**
	 * Contains optional path to icon of tree node.
	 * @return path to icon if available
	 */
	Optional<String> getTreeIconPath();

	/**
	 * Optional ModuleType describing parent node of this node.
	 * @return ModuleType of parent node if available
	 */
	Optional<ModuleType> getParentModule();
}

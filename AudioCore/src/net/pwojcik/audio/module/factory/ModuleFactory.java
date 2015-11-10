package net.pwojcik.audio.module.factory;

import java.util.Collection;

import net.pwojcik.audio.module.Module;

/**
 * Factory for all modules available in application.
 * @author Pawel Wojcik
 * @version 1.0
 */
public interface ModuleFactory {
	
	/**
	 * Generates all modules' descriptions.
	 * @return collection of modules
	 */
	Collection<Module> create();
}

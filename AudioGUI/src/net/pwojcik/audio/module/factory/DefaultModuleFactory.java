package net.pwojcik.audio.module.factory;

import java.util.Collection;
import java.util.List;

import net.pwojcik.audio.module.Module;
import net.pwojcik.audio.module.xml.Configuration;
import net.pwojcik.audio.module.xml.ModuleXMLParser;

/**
 * Default implementation of module factory.
 * By default, modules are parsed from XML configuration.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class DefaultModuleFactory implements ModuleFactory {

	@Override
	public Collection<Module> create() {
		ModuleXMLParser parser = new ModuleXMLParser();
		List<Configuration> configurations = parser.parseConfiguration();
		return parser.transformConfigurations(configurations);
	}

}

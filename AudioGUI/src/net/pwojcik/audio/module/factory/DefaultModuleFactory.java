package net.pwojcik.audio.module.factory;

import java.util.Collection;
import java.util.List;

import net.pwojcik.audio.module.Module;
import net.pwojcik.audio.module.xml.Configuration;
import net.pwojcik.audio.module.xml.ModuleXMLParser;

public final class DefaultModuleFactory implements ModuleFactory {

	@Override
	public Collection<Module> create() {
		ModuleXMLParser parser = new ModuleXMLParser();
		List<Configuration> configurations = parser.parseConfiguration();
		return parser.transformConfigurations(configurations);
	}

}

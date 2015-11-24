package net.pwojcik.audio.module.xml;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import net.pwojcik.audio.exception.ModulesConfigurationException;
import net.pwojcik.audio.module.Module;

/**
 * Parser for XML configuration of modules enabled in application.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class ModuleXMLParser {
	private static final String RESOURCE_PREFIX_TO_REMOVE = "file:";
	private static final String MODULES_CONFIGURATION_FILENAME = "modules.xml";
	private static final String XML_PARSE_EXCEPTION_MSG = "XML configuration file cannot be parsed correctly.";
	private static final String INSTANTIATION_EXCEPTION = "Configured module cannot be instantiated.";

	/**
	 * Reads XML file and returns parsed configuration.
	 * @return modules configuration list
	 */
	public List<Configuration> parseConfiguration() {
		try {
			String fullPath = getClass().getClassLoader().getResource(MODULES_CONFIGURATION_FILENAME).toURI()
					.toString();
			String validPath = fullPath.substring(RESOURCE_PREFIX_TO_REMOVE.length());
			File file = new File(validPath);
			if (file.exists()) {
				JAXBContext context = JAXBContext.newInstance(ModulesConfiguration.class);
				Unmarshaller unmarshaller = context.createUnmarshaller();
				ModulesConfiguration configurations = (ModulesConfiguration) unmarshaller.unmarshal(file);
				return configurations.getConfigurations();
			}
		} catch (JAXBException | URISyntaxException exception) {
			throw new ModulesConfigurationException(XML_PARSE_EXCEPTION_MSG, exception);
		}

		return Collections.emptyList();
	}

	/**
	 * Transforms configuration objects into new instances of specific Modules.
	 * @param modulesConfiguration configuration list
	 * @return collection of enabled modules
	 */
	public Collection<Module> transformConfigurations(List<Configuration> modulesConfiguration) {
		Collection<Module> allModules = new ArrayList<>();
		try {
			for (Configuration config : modulesConfiguration) {
				Class<?> classRepresentation = Class.forName(config.getClassName());
				Module module = (Module) classRepresentation.newInstance();
				module.setType(config.getModuleType());
				module.setApplicationDefaultModule(config.isDefaultModule());
				Collection<String> observedTypes = getObservedTypesAsStrings(config);
				module.setObservedFlowTypes(observedTypes);
				Collection<String> providedResources = getProvidedResourcesAsStrings(config);
				module.setProvidedResources(providedResources);
				allModules.add(module);
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException exception) {
			throw new ModulesConfigurationException(INSTANTIATION_EXCEPTION, exception);
		}
		return allModules;
	}

	private Collection<String> getObservedTypesAsStrings(Configuration config) {
		Collection<String> observedTypes = config.getObservedTypes().stream().map(type -> type.getClassName())
				.collect(Collectors.toList());
		return observedTypes;
	}
	
	private Collection<String> getProvidedResourcesAsStrings(Configuration config) {
		Collection<String> providedResources = config.getProvidedResources().stream().map(type -> type.getClassName())
				.collect(Collectors.toList());
		return providedResources;
	}

}

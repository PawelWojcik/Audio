package net.pwojcik.audio.module.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "modulesConfiguration")
@XmlAccessorType(XmlAccessType.FIELD)
final class ModulesConfiguration {

	@XmlElement(name = "configuration")
	private List<Configuration> configurations;

	public List<Configuration> getConfigurations() {
		return configurations;
	}

	public void setConfigurations(List<Configuration> enabledModules) {
		configurations = enabledModules;
	}
}

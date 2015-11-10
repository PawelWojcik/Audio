package net.pwojcik.audio.module.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "configuration")
@XmlAccessorType(XmlAccessType.FIELD)
public final class Configuration {

	@XmlAttribute(name = "type")
	private String moduleType;
	@XmlAttribute(name = "class")
	private String className;
	@XmlElement(name = "observedType", required = false)
	private List<ObservedFlowDataType> observedTypes = new ArrayList<>();

	public String getClassName() {
		return className;
	}
	
	public void setClassName(String classPath) {
		className = classPath;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String type) {
		moduleType = type;
	}
	
	public void setObservedTypes(List<ObservedFlowDataType> types) {
		observedTypes = types;
	}
	
	public List<ObservedFlowDataType> getObservedTypes() {
		return observedTypes;
	}

}

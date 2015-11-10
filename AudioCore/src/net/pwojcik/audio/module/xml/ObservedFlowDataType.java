package net.pwojcik.audio.module.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "observedType")
@XmlAccessorType(XmlAccessType.FIELD)
public final class ObservedFlowDataType {

	@XmlAttribute(name = "class")
	private String className;
	
	public String getClassName() {
		return className;
	}
	
	public void setClassName(String classPath) {
		className = classPath;
	}
}

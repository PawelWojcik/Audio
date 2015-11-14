package net.pwojcik.audio.module.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "provides")
@XmlAccessorType(XmlAccessType.FIELD)
public final class ProvidedType {

	@XmlAttribute(name = "class")
	private String className;
	
	public String getClassName() {
		return className;
	}
	
	public void setClassName(String classPath) {
		className = classPath;
	}
}

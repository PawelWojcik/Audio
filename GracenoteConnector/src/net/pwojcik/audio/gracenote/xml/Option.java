package net.pwojcik.audio.gracenote.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "OPTION")
@XmlAccessorType(XmlAccessType.FIELD)
public class Option {
	
	public enum Parameter {
		COVER_SIZE, FALLBACK_GENRECOVER, RETURN_COVER, SELECT_EXTENDED, SELECT_DETAIL
	}
	
	@XmlElement(name="PARAMETER")
	private Parameter parameter;
	@XmlElement(name="VALUE")
	private String value;
	
	public Parameter getParameter() {
		return parameter;
	}
	
	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
}
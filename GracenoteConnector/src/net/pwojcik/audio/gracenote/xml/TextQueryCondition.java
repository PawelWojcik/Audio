package net.pwojcik.audio.gracenote.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name="TEXT")
@XmlAccessorType(XmlAccessType.FIELD)
public class TextQueryCondition {

	public enum TextQueryType {
		ARTIST, ALBUM_TITLE, TRACK_TITLE
	}
	
	@XmlAttribute(name="TYPE")
	private TextQueryType type;
	@XmlValue
	private String value;
	
	public TextQueryType getType() {
		return type;
	}
	
	public void setType(TextQueryType type) {
		this.type = type;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
}

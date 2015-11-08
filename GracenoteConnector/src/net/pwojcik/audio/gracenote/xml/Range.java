package net.pwojcik.audio.gracenote.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="RANGE")
@XmlAccessorType(XmlAccessType.FIELD)
public class Range {
	
	@XmlElement(name="START")
	private String start;
	@XmlElement(name="END")
	private String end;
	@XmlElement(name="COUNT")
	private String count;
	
	public String getStart() {
		return String.valueOf(start);
	}
	
	public void setStart(String i) {
		start = i;
	}
	
	public String getEnd() {
		return end;
	}
	
	public void setEnd(String i) {
		end = i;
	}
	
	public String getCount() {
		return count;
	}
	
	public void setCount(String value) {
		count = value;
	}
}
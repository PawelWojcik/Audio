package net.pwojcik.audio.gracenote.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="AUTH")
@XmlAccessorType(XmlAccessType.FIELD)
public class Authorization {
	
	@XmlElement(name="CLIENT")
	private String clientID;
	@XmlElement(name="USER")
	private String userID;
	
	public String getClientID() {
		return clientID;
	}
	
	public String getUserID() {
		return userID;
	}
	
	public void setClientID(String newValue) {
		clientID = newValue;
	}
	
	public void setUserID(String newValue) {
		userID = newValue;
	}
}
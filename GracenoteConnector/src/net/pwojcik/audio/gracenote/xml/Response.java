package net.pwojcik.audio.gracenote.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RESPONSE")
@XmlAccessorType(XmlAccessType.FIELD)
public class Response {
	public enum ResponseType {
		OK, NO_MATCH, ERROR;
	}
	
	@XmlElement(name = "USER")
	private String userID;
	@XmlElement(name = "ALBUM")
	private List<Album> albums;
	@XmlElement(name = "RANGE")
	private Range range;
	@XmlAttribute(name = "STATUS")
	private ResponseType responseStatus;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String newValue) {
		userID = newValue;
	}

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> newValues) {
		albums = newValues;
	}

	public ResponseType getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(String newValue) {
		responseStatus = ResponseType.valueOf(newValue);
	}
	
	public Range getRange() {
		return range;
	}
	
	public void setRange(Range newValue) {
		range = newValue;
	}
}
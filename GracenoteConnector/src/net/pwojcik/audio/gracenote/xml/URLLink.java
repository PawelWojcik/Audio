package net.pwojcik.audio.gracenote.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name="URL")
@XmlAccessorType(XmlAccessType.FIELD)
public class URLLink {
	
	public enum CoverSize {
		THUMBNAIL, SMALL, MEDIUM, LARGE, XLARGE
	}
	
	public enum URLType {
		COVERART, GENRE_COVERART, ARTIST_IMAGE, ARTIST_BIOGRAPHY, REVIEW
	}
	
	@XmlAttribute(name="SIZE")
	private CoverSize size;
	@XmlAttribute(name="TYPE")
	private URLType urlType;
	@XmlValue
	private String value;
	
	public CoverSize getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = CoverSize.valueOf(size);
	}
	public URLType getUrlType() {
		return urlType;
	}
	public void setUrlType(String urlType) {
		this.urlType = URLType.valueOf(urlType);
	}
	public String getValue() {
		return value;
	}
	public void setValue(String newValue) {
		this.value = newValue;
	}
	
}

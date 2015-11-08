package net.pwojcik.audio.gracenote.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="TRACK")
@XmlAccessorType(XmlAccessType.FIELD)
public class Track {
	
	@XmlElement(name="ARTIST")
	private String artist;
	@XmlElement(name="ARTIST_ERA")
	private String artistEra;
	@XmlElement(name="ARTIST_ORIGIN")
	private String artistOrigin;
	@XmlElement(name="ARTIST_TYPE")
	private String artistType;
	@XmlElement(name="GENRE")
	private String genre;
	@XmlElement(name="GN_ID")
	private String gracenoteID;
	@XmlElement(name="MOOD")
	private String mood;
	@XmlElement(name="TEMPO")
	private String tempo;
	@XmlElement(name="TITLE")
	private String title;
	@XmlElement(name="TRACK_NUM")
	private int trackNumber;
	
	public String getArtist() {
		return artist;
	}
	
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	public String getArtistEra() {
		return artistEra;
	}
	
	public void setArtistEra(String artistEra) {
		this.artistEra = artistEra;
	}
	
	public String getArtistOrigin() {
		return artistOrigin;
	}
	
	public void setArtistOrigin(String artistOrigin) {
		this.artistOrigin = artistOrigin;
	}
	
	public String getArtistType() {
		return artistType;
	}
	
	public void setArtistType(String artistType) {
		this.artistType = artistType;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String getGracenoteID() {
		return gracenoteID;
	}
	
	public void setGracenoteID(String gracenoteID) {
		this.gracenoteID = gracenoteID;
	}
	public String getMood() {
		return mood;
	}
	
	public void setMood(String mood) {
		this.mood = mood;
	}
	
	public String getTempo() {
		return tempo;
	}
	
	public void setTempo(String tempo) {
		this.tempo = tempo;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getTrackNumber() {
		return trackNumber;
	}
	
	public void setTrackNumber(String trackNumber) {
		this.trackNumber = Integer.valueOf(trackNumber);
	}
}

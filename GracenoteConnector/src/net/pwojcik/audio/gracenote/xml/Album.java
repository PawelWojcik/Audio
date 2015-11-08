package net.pwojcik.audio.gracenote.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ALBUM")
@XmlAccessorType(XmlAccessType.FIELD)
public class Album {
	
	@XmlElement(name="TITLE")
	private String title;
	@XmlElement(name="ARTIST")
	private String artist;
	@XmlElement(name="GENRE")
	private String genre;
	@XmlElement(name="GN_ID")
	private String gracenoteID;
	@XmlElement(name="DATE")
	private int releaseYear;
	@XmlElement(name="TRACK_COUNT")
	private int trackCount;
	@XmlElement(name="MATCHED_TRACK_NUM")
	private int matchedTrackNumber;
	@XmlElement(name="RANGE")
	private Range range;
	@XmlElement(name="URL")
	private List<URLLink> urlLinks;
	@XmlElement(name="TRACK")
	private List<Track> tracks;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String newTitle) {
		title = newTitle;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
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

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(String releaseYear) {
		this.releaseYear = Integer.valueOf(releaseYear);
	}

	public int getTrackCount() {
		return trackCount;
	}

	public void setTrackCount(String trackCount) {
		this.trackCount = Integer.valueOf(trackCount);
	}

	public int getMatchedTrackNumber() {
		return matchedTrackNumber;
	}

	public void setMatchedTrackNumber(String matchedTrackNumber) {
		this.matchedTrackNumber = Integer.valueOf(matchedTrackNumber);
	}

	public Range getRange() {
		return range;
	}

	public void setRange(Range range) {
		this.range = range;
	}

	public List<URLLink> getUrlLinks() {
		return urlLinks;
	}

	public void setUrlLinks(List<URLLink> urlLinks) {
		this.urlLinks = urlLinks;
	}

	public List<Track> getTracks() {
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}
}
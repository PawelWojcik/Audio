package net.pwojcik.audio.gracenote;

/**
 * @author Pawel Wojcik
 * @version 1.0
 */
public class GracenoteExtendedData {
	private String coverLink;
	private String trackTitle;
	private String album;
	private String artist;
	private String genre;
	private int year;
	private int trackNumber;
	
	public String getTrackTitle() {
		return trackTitle;
	}
	
	public void setTrackTitle(String trackTitle) {
		this.trackTitle = trackTitle;
	}
	
	public String getAlbum() {
		return album;
	}
	
	public void setAlbum(String album) {
		this.album = album;
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
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public int getTrackNumber() {
		return trackNumber;
	}
	
	public void setTrackNumber(int trackNumber) {
		this.trackNumber = trackNumber;
	}

	public String getCoverLink() {
		return coverLink;
	}
	
	public void setCoverLink(String link) {
		coverLink = link;
	}
	
}

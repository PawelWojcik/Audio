package net.pwojcik.audio.gracenote;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.pwojcik.audio.gracenote.xml.Album;
import net.pwojcik.audio.gracenote.xml.Track;
import net.pwojcik.audio.gracenote.xml.URLLink;
import net.pwojcik.audio.gracenote.xml.URLLink.URLType;

final class GracenoteUtil {

	public static List<GracenoteExtendedData> getExtendedList(List<Album> albums) {
		List<GracenoteExtendedData> list = new ArrayList<>(albums.size());
		for (Album album : albums) {
			for (Track track : album.getTracks()) {
				GracenoteExtendedData data = new GracenoteExtendedData();
				data.setAlbum(album.getTitle());
				data.setArtist(album.getArtist());
				data.setGenre(track.getGenre());
				data.setTrackNumber(track.getTrackNumber());
				data.setTrackTitle(track.getTitle());
				data.setYear(album.getReleaseYear());
				list.add(data);
			}
		}
		return list;
	}

	public static String getCoverURL(List<Album> albums) {
		String link = "";
		for (Album album : albums) {
			boolean coverFound = false;
			if (album.getUrlLinks() != null) {
				Iterator<URLLink> urls = album.getUrlLinks().iterator();
				while (!coverFound && urls.hasNext()) {
					URLLink currentURL = urls.next();
					if (currentURL.getUrlType() == URLType.COVERART) {
						link = currentURL.getValue();
						coverFound = true;
					}
				}
			}
		}
		return link;
	}

}

package net.pwojcik.audio.flowdata;

import net.pwojcik.audio.flowdata.AbstractFlowData;
import net.pwojcik.audio.model.playlist.Playlist;

/**
 * Flow data representing change of actual playlist.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class CurrentPlaylistChangeFlowData extends AbstractFlowData {

	private Playlist currentPlaylist;
	
	public CurrentPlaylistChangeFlowData(Playlist newPlaylist) {
		currentPlaylist = newPlaylist;
	}
	
	/**
	 * Returns current playlist.
	 * @return current playlist
	 */
	public Playlist getCurrentPlaylist() {
		return currentPlaylist;
	}
}

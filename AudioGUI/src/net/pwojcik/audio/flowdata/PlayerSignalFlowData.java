package net.pwojcik.audio.flowdata;

import net.pwojcik.audio.model.playlist.Playlist;

/**
 * Flow data containing {@linkplain Playlist} instance which should be played at
 * the moment.
 * 
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class PlayerSignalFlowData extends AbstractFlowData {

	private final Playlist playlist;

	public PlayerSignalFlowData(Playlist newPlaylist) {
		playlist = newPlaylist;
	}

	public Playlist getSignal() {
		return playlist;
	}
}

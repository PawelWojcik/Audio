package net.pwojcik.audio.model.playlist;

import java.util.Collection;

import net.pwojcik.audio.model.Audio;

/**
 * Playlist interface representing specific set of tracks.
 * @author Pawel Wojcik
 * @version 1.0
 */
public interface Playlist {

	/**
	 * Returns all tracks contained in playlist.
	 * @return tracks collection
	 */
	Collection<Audio> getAudioList();

	/**
	 * Gets track located at given position in playlist.
	 * @param index track's localisation
	 * @return proper track
	 */
	Audio getAudio(int index);
} 

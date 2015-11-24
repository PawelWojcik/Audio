package net.pwojcik.audio.model.playlist;

import java.util.Collection;

import com.google.common.collect.Iterators;

import net.pwojcik.audio.model.Audio;

/**
 * Default implementation of {@linkplain Playlist} interface.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class PlaylistImpl implements Playlist {

	private Collection<Audio> audios;
	
	public PlaylistImpl(Collection<Audio> list) {
		audios = list;
	}
	
	@Override
	public Collection<Audio> getAudioList() {
		return audios;
	}
	
	@Override
	public Audio getAudio(int index) {
		return Iterators.get(audios.iterator(), index);
	}

}

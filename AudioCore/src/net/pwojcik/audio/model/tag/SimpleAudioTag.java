package net.pwojcik.audio.model.tag;

import org.jaudiotagger.tag.Tag;

import net.pwojcik.audio.model.AbstractAudioTag;

/**
 * Default implementation of {@linkplain AudioTag} interface.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class SimpleAudioTag extends AbstractAudioTag {

	private int lengthInSeconds;

	public SimpleAudioTag(Tag audioTag, int length) {
		super(audioTag);
		lengthInSeconds = length;
	}

	@Override
	public String getFormattedAudioLength() {
		int seconds = (lengthInSeconds % 60);
		String secondsString = seconds < 10 ? "0" + seconds : seconds + "";
		return (lengthInSeconds / 60) + ":" + secondsString;
	}

}

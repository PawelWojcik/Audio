package net.pwojcik.audio.model.tag;

/**
 * Interface representing set of tags related to specific audio track.
 * @author Pawel Wojcik
 * @version 1.0
 */
public interface AudioTag {
	
	/**
	 * Returns tag determined by type parameter.
	 * @param type type of tag
	 * @return proper tag
	 */
	String getTagByType(AudioTagType type);

	/**
	 * Returns length of track in format HH:MM:SS
	 * @return formatted audio track length
	 */
	String getFormattedAudioLength();
}

package net.pwojcik.audio.model;

import net.pwojcik.audio.model.tag.AudioTag;

/**
 * Representation of single audio track.
 * @author Pawel Wojcik
 * @version 1.0
 */
public interface Audio {

	/**
	 * Returns path to audio file.
	 * @return file path
	 */
	String getPath();
	
	/**
	 * Returns identifier of directory in which file is located.
	 * @return ID of parent directory
	 */
	int getDirectoryIdentifier();
	
	/**
	 * Sets identifier of directory in which file is located.
	 * @param identifier ID of parent directory
	 */
	void setDirectoryIdentifier(int identifier);

	/**
	 * Returns container for tags related to this audio track.
	 * @return tag container
	 */
	AudioTag getAudioTag();

}


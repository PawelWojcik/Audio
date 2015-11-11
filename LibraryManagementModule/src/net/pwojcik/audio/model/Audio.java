package net.pwojcik.audio.model;

/**
 * Representation of single audio track.
 * @author Pawel Wojcik
 * @version 1.0
 */
public interface Audio {

	String getPath();
	
	int getDirectoryIdentifier();
	
	void setDirectoryIdentifier(int identifier);
}

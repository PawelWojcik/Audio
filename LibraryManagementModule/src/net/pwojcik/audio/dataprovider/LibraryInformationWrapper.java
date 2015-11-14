package net.pwojcik.audio.dataprovider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.pwojcik.audio.model.Audio;

/**
 * Container class for all information about application's audio library.
 * @author Pawel Wojcik
 * @version 1.0
 */
final class LibraryInformationWrapper {

	private Map<String, SourceDirectory> directoryMap;
	private Collection<Audio> audios;
	private Collection<String> allDirectories;
	private List<SourceDirectory> displayedDirectories;
	private int lastDirectoryID;

	LibraryInformationWrapper() {
		lastDirectoryID = 0;
		directoryMap = new HashMap<>();
		audios = new ArrayList<>();
		displayedDirectories = new ArrayList<>();
		allDirectories = new ArrayList<>();
	}

	/**
	 * Returns all tracks contained in library.
	 * @return all tracks collection
	 */
	Collection<Audio> getAudioList() {
		return audios;
	}

	/**
	 * Returns list of directories which should be displayed in GUI.
	 * @return GUI-visible directories
	 */
	List<SourceDirectory> getMainLevelDirectoryList() {
		return displayedDirectories;
	}

	/**
	 * Returns paths to all directories in library. Collection includes only
	 * these subdirectories which contain at least one track.
	 * @return all directories in library
	 */
	Collection<String> getAllDirectories() {
		return allDirectories;
	}

	/**
	 * Map with entries: Path-Directory
	 * @return map of library directories
	 */
	Map<String, SourceDirectory> getDirectoryMap() {
		return directoryMap;
	}

	/**
	 * Increments highest identifier number from directories and returns it. 
	 * @return highest directory identifier
	 */
	int incrementDirectoryID() {
		++lastDirectoryID;
		return lastDirectoryID;
	}

	/**
	 * Returns highest directory identifier.
	 * @return highest directory identifier
	 */
	int getLastDirectoryID() {
		return lastDirectoryID;
	}

	/**
	 * Sets new highest directory identifier
	 * @param id new identifier
	 */
	void setLastDirectoryID(int id) {
		lastDirectoryID = id;
	}

	void resetDirectoryID() {
		lastDirectoryID = 0;
	}

}

package net.pwojcik.audio.dataprovider;

import java.io.File;
import java.util.Collection;

import net.pwojcik.audio.model.Audio;

/**
 * Class responsible for handling operations on audio library: adding, removing,
 * saving and reading configuration from file.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class LibraryDataManager {

	private LibraryInformationWrapper libraryInformation;
	private LibraryDataAddingService addService;
	private LibraryDataRemovingService removeService;
	private LibraryDataUpdatingService updatingService;

	public LibraryDataManager() {
		libraryInformation = new LibraryInformationWrapper();
		addService = new LibraryDataAddingService(libraryInformation);
		removeService = new LibraryDataRemovingService(libraryInformation);
		updatingService = new LibraryDataUpdatingService(libraryInformation);
		LibraryDataConfigurationReadingService readingService = new LibraryDataConfigurationReadingService(
				libraryInformation);
		readingService.readData();
	}

	/**
	 * Returns all tracks contained in library.
	 * 
	 * @return all tracks collection
	 */
	public Collection<Audio> getAudioList() {
		return libraryInformation.getAudioList();
	}

	/**
	 * Returns list of main library directories.
	 * @return list of directories
	 */
	public Collection<SourceDirectory> getMainLevelDirectoryList() {
		return libraryInformation.getMainLevelDirectoryList();
	}

	/**
	 * Adds directory to library. Method scans directory and subdirectories for
	 * .mp3 tracks. To save changes permanently in XML descriptor
	 * {@linkplain LibraryDataManager#updateDirectories()} should be called.
	 * @param directory directory to add
	 * @return number of tracks added to library from directory
	 */
	public int addDirectory(File directory) {
		return addService.addDirectory(directory);
	}

	/**
	 * Removes collection of directories from library.
	 * @param directoryList directories to remove
	 */
	public void removeDirectories(Collection<SourceDirectory> directoryList) {
		removeService.removeDirectories(directoryList);
	}

	/**
	 * Saves all changes in XML descriptor file.
	 */
	public void updateDirectories() {
		updatingService.updateDirectories();
	}

}
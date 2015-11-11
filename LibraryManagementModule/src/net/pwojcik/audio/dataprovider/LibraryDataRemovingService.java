package net.pwojcik.audio.dataprovider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import net.pwojcik.audio.model.Audio;

/**
 * Class responsible for removing directories from library. Directories are only
 * removed from memory, so any permanent change has to be confirmed by using
 * {@linkplain LibraryDataUpdatingService}.
 * XML file.
 * @author Pawel Wojcik
 * @version 1.0
 */
final class LibraryDataRemovingService {

	private LibraryInformationWrapper libraryInformation;
	private Collection<SourceDirectory> displayedDirectoriesToRemove;

	LibraryDataRemovingService(LibraryInformationWrapper libraryInformationWrapper) {
		libraryInformation = libraryInformationWrapper;
		displayedDirectoriesToRemove = new ArrayList<>();
	}

	/**
	 * Removes collection of directories given as parameter.
	 * @param directoryList directories to remove.
	 */
	void removeDirectories(Collection<SourceDirectory> directoryList) {
		Iterator<SourceDirectory> iterator = directoryList.iterator();
		while (iterator.hasNext()) {
			SourceDirectory directory = iterator.next();
			removeDirectory(directory);
		}

		for (SourceDirectory dirToRemove : displayedDirectoriesToRemove) {
			libraryInformation.getMainLevelDirectoryList().remove(dirToRemove);
		}
		displayedDirectoriesToRemove.clear();
	}

	private void removeDirectory(SourceDirectory directory) {
		List<SourceDirectory> children = getDirectoryChildren(directory);
		if (!children.isEmpty()) {
			for (SourceDirectory child : children) {
				removeDirectory(child);
			}
		}
		removeAudioElements(directory);
		removeDirectoryFromCollections(directory);
	}

	private void removeAudioElements(SourceDirectory directory) {
		int dirID = directory.getId();
		Collection<Audio> audioList = libraryInformation.getAudioList();
		Collection<Audio> filteredAudio = audioList.stream().filter(audio -> audio.getDirectoryIdentifier() == dirID)
				.collect(Collectors.toList());
		audioList.removeAll(filteredAudio);
	}

	private void removeDirectoryFromCollections(SourceDirectory directory) {
		String path = directory.getPath();
		libraryInformation.getAllDirectories().remove(path);
		Map<String, SourceDirectory> directoryMap = libraryInformation.getDirectoryMap();
		SourceDirectory srcDir = directoryMap.get(path);
		if (libraryInformation.getMainLevelDirectoryList().contains(srcDir)) {
			displayedDirectoriesToRemove.add(srcDir);
		}
		directoryMap.remove(path);
	}

	private List<SourceDirectory> getDirectoryChildren(SourceDirectory directory) {
		int dirID = directory.getId();
		List<SourceDirectory> collect = libraryInformation.getDirectoryMap().values().stream()
				.filter(element -> element.getParentID() == dirID).collect(Collectors.toList());
		return collect;
	}
}

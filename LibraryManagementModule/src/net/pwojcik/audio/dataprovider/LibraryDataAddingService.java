package net.pwojcik.audio.dataprovider;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import net.pwojcik.audio.model.Audio;

/**
 * Class responsible for adding new directories to library. Directories are only
 * added to memory, so any permanent change has to be confirmed by using
 * {@linkplain LibraryDataUpdatingService}.
 * @author Pawel Wojcik
 * @version 1.0
 */
final class LibraryDataAddingService {

	private LibraryInformationWrapper libraryInformation;

	LibraryDataAddingService(LibraryInformationWrapper libraryInformationWrapper) {
		libraryInformation = libraryInformationWrapper;
	}

	/**
	 * Adds new directory and it's all subdirectories to library.
	 * @param directory representation of directory
	 * @return total count of tracks found in directory and subdirectories
	 */
	int addDirectory(File directory) {
		int startSize = libraryInformation.getAudioList().size();
		addDirectory(directory, true, 0);
		int endSize = libraryInformation.getAudioList().size();
		return endSize - startSize;
	}

	private SourceDirectory addDirectory(File directory, int parentID) {
		return addDirectory(directory, false, parentID);
	}

	private SourceDirectory addDirectory(File directory, boolean addToListView, int parentID) {
		String path = directory.getAbsolutePath();
		SourceDirectory resultDirectory;
		Collection<String> allDirectories = libraryInformation.getAllDirectories();
		if (!allDirectories.contains(path)) {
			int directoryID = libraryInformation.incrementDirectoryID();
			List<SourceDirectory> subDirs = processContainedElements(directory, directoryID);
			SourceDirectory sourceDirectory = new SourceDirectory(directoryID);
			sourceDirectory.setPath(path);
			sourceDirectory.setSubDirectories(subDirs);
			libraryInformation.getDirectoryMap().put(path, sourceDirectory);
			allDirectories.add(path);
			if (addToListView) {
				libraryInformation.getMainLevelDirectoryList().add(sourceDirectory);
			} else {
				sourceDirectory.setParentID(parentID);
			}
			resultDirectory = sourceDirectory;
		} else {
			resultDirectory = libraryInformation.getDirectoryMap().get(path);
		}
		return resultDirectory;
	}

	private List<SourceDirectory> processContainedElements(File directory, int directoryID) {
		List<SourceDirectory> resultList = new LinkedList<>();
		File[] fileList = directory.listFiles();
		for (File file : fileList) {
			if (file.isDirectory()) {
				String path = file.getAbsolutePath();
				if (libraryInformation.getAllDirectories().contains(path)) {
					SourceDirectory srcDir = libraryInformation.getDirectoryMap().get(path);
					srcDir.setParentID(directoryID);
					List<SourceDirectory> displayedDirectories = libraryInformation.getMainLevelDirectoryList();
					if (displayedDirectories.contains(srcDir)) {
						displayedDirectories.remove(srcDir);
					}
					resultList.add(srcDir);
				} else {
					SourceDirectory subDirectory = addDirectory(file, directoryID);
					resultList.add(subDirectory);
				}
			} else {
				Optional<Audio> optionalAudio = LibraryDataProviderUtil.processFile(file, directoryID);
				optionalAudio.ifPresent(libraryInformation.getAudioList()::add);
			}
		}
		return resultList;
	}
}

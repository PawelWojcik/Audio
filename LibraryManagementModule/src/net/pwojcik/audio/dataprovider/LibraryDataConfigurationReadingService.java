package net.pwojcik.audio.dataprovider;

import java.io.File;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import net.pwojcik.audio.exception.LibraryXMLCommunicationException;
import net.pwojcik.audio.model.Audio;

/**
 * Class responsible for reading configuration from XML descriptor to memory.
 * @author Pawel Wojcik
 * @version 1.0
 */
final class LibraryDataConfigurationReadingService {

	private LibraryInformationWrapper libraryInformation;

	LibraryDataConfigurationReadingService(LibraryInformationWrapper libraryInformationWrapper) {
		libraryInformation = libraryInformationWrapper;
	}

	/**
	 * Method reads XML descriptor and completes library.
	 */
	void readData() {
		String path = LibraryDataProviderUtil.prepareLoaderFilePath();
		Optional<SourceLibraries> libraries = readConfiguration(path);
		if (libraries.isPresent()) {
			processLoadedLibraries(libraries.get());
		}
	}

	private Optional<SourceLibraries> readConfiguration(String path) {
		Optional<SourceLibraries> optional;
		File file = new File(path);
		if (file.exists()) {
			try {
				JAXBContext context = JAXBContext.newInstance(SourceLibraries.class);
				Unmarshaller unmarshaller = context.createUnmarshaller();
				SourceLibraries libraries = (SourceLibraries) unmarshaller.unmarshal(file);
				optional = Optional.ofNullable(libraries);
			} catch (JAXBException exception) {
				throw new LibraryXMLCommunicationException(exception);
			}
		} else {
			optional = Optional.empty();
		}

		return optional;
	}

	private void processLoadedLibraries(SourceLibraries libraries) {
		if (libraries.getDirectories() != null) {
			for (SourceDirectory dir : libraries.getDirectories()) {
				processLoadedDirectory(dir);
				libraryInformation.getMainLevelDirectoryList().add(dir);
			}
		}
	}

	private void processLoadedDirectory(SourceDirectory dir) {
		libraryInformation.getAllDirectories().add(dir.getPath());
		libraryInformation.getDirectoryMap().put(dir.getPath(), dir);
		readMaxDirectoryIndex(dir);
		String dirPath = dir.getPath();
		File directoryHandle = new File(dirPath);
		File[] listFiles = directoryHandle.listFiles();
		for (File file : listFiles) {
			if (file.isFile()) {
				Optional<Audio> optionalAudio = LibraryDataProviderUtil.processFile(file, dir.getId());
				optionalAudio.ifPresent(libraryInformation.getAudioList()::add);
			}
		}
		if (dir.getSubDirectories() != null) {
			for (SourceDirectory subDir : dir.getSubDirectories()) {
				processLoadedDirectory(subDir);
			}
		}
	}

	private void readMaxDirectoryIndex(SourceDirectory dir) {
		int id = dir.getId();
		if (id > libraryInformation.getLastDirectoryID()) {
			libraryInformation.setLastDirectoryID(id);
		}
	}

}

package net.pwojcik.audio.dataprovider;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import net.pwojcik.audio.exception.LibraryXMLCommunicationException;

/**
 * Class responsible for updating XML descriptor file with information about
 * library directories.
 * 
 * @author Pawel Wojcik
 * @version 1.0
 */
final class LibraryDataUpdatingService {

	private LibraryInformationWrapper libraryInformation;

	LibraryDataUpdatingService(LibraryInformationWrapper libraryInformationWrapper) {
		libraryInformation = libraryInformationWrapper;
	}

	/**
	 * Saves all changes in XML file.
	 */
	void updateDirectories() {
		String path = LibraryDataProviderUtil.prepareLoaderFilePath();
		SourceLibraries libs = new SourceLibraries();
		libs.setDirectories(libraryInformation.getMainLevelDirectoryList());
		try {
			File file = new File(path);
			JAXBContext context = JAXBContext.newInstance(SourceLibraries.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(libs, file);
		} catch (JAXBException exception) {
			throw new LibraryXMLCommunicationException(exception);
		}

		updateDirectoryIDsIfNecessary();
	}

	private void updateDirectoryIDsIfNecessary() {
		if (libraryInformation.getAudioList().isEmpty() && libraryInformation.getAllDirectories().isEmpty()) {
			libraryInformation.resetDirectoryID();
		}
	}

}

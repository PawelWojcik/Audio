package net.pwojcik.audio.dataprovider;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Optional;

import net.pwojcik.audio.exception.LibraryXMLCommunicationException;
import net.pwojcik.audio.model.Audio;
import net.pwojcik.audio.model.AudioImpl;

/**
 * Utitlity methods useful in library modifications context.
 * @author Pawel Wojcik
 * @version 1.0
 */
final class LibraryDataProviderUtil {

	private static final String MP3_FORMAT = "mp3";
	private static final String DATA_LOADER_FILE = "loader.xml";

	/**
	 * Converts filepath to Audio object if filepath represents .mp3 file.
	 * @param file file representation
	 * @param directoryID identifier of directory containing file
	 * @return Audio object or empty Optional if File is not in .mp3 format
	 */
	static Optional<Audio> processFile(File file, int directoryID) {
		String name = file.getName();
		String extension = name.substring(name.lastIndexOf(".") + 1);
		if (MP3_FORMAT.equals(extension)) {
			Audio audio = new AudioImpl(file.getAbsolutePath());
			audio.setDirectoryIdentifier(directoryID);
			return Optional.of(audio);
		}
		return Optional.empty();
	}

	/**
	 * Constructs relative path to library configuration file.
	 * @return path to library configuration file
	 */
	static String prepareLoaderFilePath() {
		try {
			String naturalPath = LibraryDataProviderUtil.class.getProtectionDomain().getCodeSource().getLocation()
					.toURI().getPath() + DATA_LOADER_FILE;
			return naturalPath;
		} catch (URISyntaxException exception) {
			throw new LibraryXMLCommunicationException(exception);
		}
	}
}

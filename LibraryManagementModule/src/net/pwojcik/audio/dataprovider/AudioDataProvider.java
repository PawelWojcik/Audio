package net.pwojcik.audio.dataprovider;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.collections.ObservableList;
import net.pwojcik.audio.model.Audio;
import net.pwojcik.audio.model.AudioImpl;

/**
 * @author Pawel Wojcik
 * @version 1.0
 */
public class AudioDataProvider {

	private static final String DATA_LOADER_FILE = "loader.xml";
	private static final String MP3_FORMAT = "mp3";

	private Map<String, SourceDirectory> dirMap;
	private List<SourceDirectory> displayedDirectories;
	private List<SourceDirectory> displayedDirectoriesToRemove;
	private List<Audio> audios;
	private List<String> allDirectories;
	private String naturalPath;

	private int lastDirectoryID = 0;

	public AudioDataProvider() {
		dirMap = new HashMap<>();
		audios = new ArrayList<>();
		displayedDirectories = new LinkedList<>();
		displayedDirectoriesToRemove = new LinkedList<>();
		allDirectories = new LinkedList<>();
		prepareLoaderFilePath();
		readDataFromXMLDescriptor();
	}

	public List<Audio> getAudioList() {
		return audios;
	}

	public List<SourceDirectory> getDisplayedDirectoryList() {
		return displayedDirectories;
	}

	public int addDirectory(File directory) {
		int startSize = audios.size();
		addDirectory(directory, true, 0);
		int endSize = audios.size();
		return endSize - startSize;
	}

	/**
	 * @param selections
	 */
	public void removeDirectories(ObservableList<SourceDirectory> directoryList) {
		Iterator<SourceDirectory> iterator = directoryList.iterator();
		while (iterator.hasNext()) {
			SourceDirectory directory = iterator.next();
			removeDirectory(directory);
		}

		for (SourceDirectory dirToRemove : displayedDirectoriesToRemove) {
			displayedDirectories.remove(dirToRemove);
		}
		displayedDirectoriesToRemove.clear();
	}

	/**
	 * 
	 */
	public void updateDirectories() {
		SourceLibraries libs = new SourceLibraries();
		libs.setDirectories(displayedDirectories);
		try {
			File file = new File(naturalPath);
			JAXBContext context = JAXBContext.newInstance(SourceLibraries.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(libs, file);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	private void prepareLoaderFilePath() {
		try {
			naturalPath = getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath()
					+ DATA_LOADER_FILE;
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	private void readDataFromXMLDescriptor() {
		try {
			Optional<SourceLibraries> libraries = extractData();
			if (libraries.isPresent()) {
				processLoadedLibraries(libraries.get());
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	private Optional<SourceLibraries> extractData() throws JAXBException {
		Optional<SourceLibraries> optional;
		File file = new File(naturalPath);
		if (file.exists()) {
			JAXBContext context = JAXBContext.newInstance(SourceLibraries.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			SourceLibraries libraries = (SourceLibraries) unmarshaller.unmarshal(file);
			optional = Optional.ofNullable(libraries);
		} else {
			optional = Optional.empty();
		}

		return optional;
	}

	private void processLoadedLibraries(SourceLibraries libraries) {
		if (libraries.getDirectories() != null) {
			for (SourceDirectory dir : libraries.getDirectories()) {
				processLoadedDirectory(dir);
				displayedDirectories.add(dir);
			}
		}
	}

	private void processLoadedDirectory(SourceDirectory dir) {
		allDirectories.add(dir.getPath());
		dirMap.put(dir.getPath(), dir);
		readMaxDirectoryIndex(dir);
		String dirPath = dir.getPath();
		File directoryHandle = new File(dirPath);
		File[] listFiles = directoryHandle.listFiles();
		for (File file : listFiles) {
			if (file.isFile()) {
				processFile(file, dir.getID());
			}
		}
		if (dir.getSubDirectories() != null) {
			for (SourceDirectory subDir : dir.getSubDirectories()) {
				processLoadedDirectory(subDir);
			}
		}
	}

	private void readMaxDirectoryIndex(SourceDirectory dir) {
		int id = dir.getID();
		if (id > lastDirectoryID) {
			lastDirectoryID = id;
		}
	}

	private SourceDirectory addDirectory(File directory, int parentID) {
		return addDirectory(directory, false, parentID);
	}

	private SourceDirectory addDirectory(File directory, boolean addToListView, int parentID) {
		String path = directory.getAbsolutePath();
		SourceDirectory resultDirectory;
		if (!allDirectories.contains(path)) {
			int directoryID = ++lastDirectoryID;
			List<SourceDirectory> subDirs = processContainedElements(directory, directoryID);
			SourceDirectory sourceDirectory = new SourceDirectory(directoryID);
			sourceDirectory.setPath(path);
			sourceDirectory.setSubDirectories(subDirs);
			dirMap.put(path, sourceDirectory);
			allDirectories.add(path);
			if (addToListView) {
				displayedDirectories.add(sourceDirectory);
			} else {
				sourceDirectory.setParentID(parentID);
			}
			resultDirectory = sourceDirectory;
		} else {
			resultDirectory = dirMap.get(path);
		}
		return resultDirectory;
	}

	private List<SourceDirectory> processContainedElements(File directory, int directoryID) {
		List<SourceDirectory> resultList = new LinkedList<>();
		File[] fileList = directory.listFiles();
		for (File file : fileList) {
			if (file.isDirectory()) {
				String path = file.getAbsolutePath();
				if (allDirectories.contains(path)) {
					SourceDirectory srcDir = dirMap.get(path);
					srcDir.setParentID(directoryID);
					if (displayedDirectories.contains(srcDir)) {
						displayedDirectories.remove(srcDir);
					}
					resultList.add(srcDir);
				} else {
					SourceDirectory subDirectory = addDirectory(file, directoryID);
					resultList.add(subDirectory);
				}
			} else {
				processFile(file, directoryID);
			}
		}
		return resultList;
	}

	private void processFile(File file, int directoryID) {
		String name = file.getName();
		String extension = name.substring(name.lastIndexOf(".") + 1);
		if (MP3_FORMAT.equals(extension)) {
			Audio audio = new AudioImpl(file.getAbsolutePath());
			audio.setDirectoryIdentifier(directoryID);
			audios.add(audio);
		}
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
		int dirID = directory.getID();
		List<Audio> filteredAudio = audios.stream().filter(audio -> audio.getDirectoryIdentifier() == dirID)
				.collect(Collectors.toList());

		audios.removeAll(filteredAudio);
	}

	private void removeDirectoryFromCollections(SourceDirectory directory) {
		String path = directory.getPath();
		allDirectories.remove(path);
		SourceDirectory srcDir = dirMap.get(path);
		if (displayedDirectories.contains(srcDir)) {
			displayedDirectoriesToRemove.add(srcDir);
		}
		dirMap.remove(path);
	}

	private List<SourceDirectory> getDirectoryChildren(SourceDirectory directory) {
		int dirID = directory.getID();
		List<SourceDirectory> collect = dirMap.values().stream().filter(p -> p.getParentID() == dirID)
				.collect(Collectors.toList());
		return collect;
	}

}
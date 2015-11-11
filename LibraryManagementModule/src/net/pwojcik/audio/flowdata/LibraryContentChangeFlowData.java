package net.pwojcik.audio.flowdata;

import java.util.Collection;

import net.pwojcik.audio.dataprovider.SourceDirectory;
import net.pwojcik.audio.flowdata.AbstractFlowData;
import net.pwojcik.audio.model.Audio;

/**
 * Implementation of {@linkplain FlowData} containing information about
 * current library content.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class LibraryContentChangeFlowData extends AbstractFlowData {

	private Collection<SourceDirectory> directories;
	private Collection<Audio> allAudio;
	
	public LibraryContentChangeFlowData(Collection<SourceDirectory> mainLevelDirectoryList, Collection<Audio> audioList) {
		directories = mainLevelDirectoryList;
		allAudio = audioList;
	}

	/**
	 * Returns collection of all tracks in library.
	 * @return all tracks handled by application
	 */
	public Collection<Audio> getAllAudio() {
		return allAudio;
	}
	
	/**
	 * Returns collection of directories which should be visible in GUI.
	 * @return GUI-visible directories
	 */
	public Collection<SourceDirectory> getDirectories() {
		return directories;
	}
	
}

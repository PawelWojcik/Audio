package net.pwojcik.audio.model;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import net.pwojcik.audio.model.tag.AudioTag;
import net.pwojcik.audio.model.tag.SimpleAudioTag;

/**
 * Default implementation of {@linkplain Audio} interface.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class AudioImpl implements Audio {

	private static final String AUDIOTAGGER_LOGGER = "org.jaudiotagger";
	private AudioTag audioTag;
	private final String path;
	private int directoryIdentifier;

	public AudioImpl(String filepath) {
		path = filepath;
		loadAudioData();
	}

	@Override
	public String getPath() {
		return path;
	}

	@Override
	public int getDirectoryIdentifier() {
		return directoryIdentifier;
	}

	@Override
	public void setDirectoryIdentifier(int identifier) {
		directoryIdentifier = identifier;
	}
	
	private void loadAudioData() {
		Logger.getLogger(AUDIOTAGGER_LOGGER).setLevel(Level.OFF);
		try {
			AudioFile tagAudioFile = AudioFileIO.read(new File(path));
			AudioHeader header = tagAudioFile.getAudioHeader();
//			technicalData = new TechnicalData(header);
			audioTag = new SimpleAudioTag(tagAudioFile.getTag(), header.getTrackLength());
//			if (audioTag.getArtwork().length == 0) {
//				audioTag.setArtwork(ViewConstants.BLANK_COVER);
//			}
		} catch (IOException | CannotReadException | TagException | ReadOnlyFileException
				| InvalidAudioFrameException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public AudioTag getAudioTag() {
		return audioTag;
	}
	
}

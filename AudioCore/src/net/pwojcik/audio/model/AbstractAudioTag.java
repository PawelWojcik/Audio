package net.pwojcik.audio.model;

import java.util.HashMap;
import java.util.Map;

import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

import net.pwojcik.audio.model.tag.AudioTag;
import net.pwojcik.audio.model.tag.AudioTagType;

/**
 * Abstract implementation of {@linkplain AudioTag} interface.
 * @author Pawel Wojcik
 * @version 1.0
 */
public abstract class AbstractAudioTag implements AudioTag {

	private final Map<AudioTagType, FieldKey> keyMapping;
	private Tag tag;
	
	public AbstractAudioTag(Tag audioTag) {
		tag = audioTag;
		keyMapping = configureMapping();
	}

	private Map<AudioTagType, FieldKey> configureMapping() {
		Map<AudioTagType, FieldKey> map = new HashMap<>();
		map.put(AudioTagType.ALBUM, FieldKey.ALBUM);
		map.put(AudioTagType.ARTIST, FieldKey.ARTIST);
		map.put(AudioTagType.COMMENT, FieldKey.COMMENT);
		map.put(AudioTagType.DISC_NUMBER, FieldKey.DISC_NO);
		map.put(AudioTagType.GENRE, FieldKey.GENRE);
		map.put(AudioTagType.RELEASE_YEAR, FieldKey.YEAR);
		map.put(AudioTagType.TITLE, FieldKey.TITLE);
		map.put(AudioTagType.TOTAL_DISCS, FieldKey.DISC_TOTAL);
		map.put(AudioTagType.TRACK_NUMBER, FieldKey.TRACK);
		return map;
	}

	@Override
	public final String getTagByType(AudioTagType type) {
		return tag.getFirst(keyMapping.get(type));
	}
}

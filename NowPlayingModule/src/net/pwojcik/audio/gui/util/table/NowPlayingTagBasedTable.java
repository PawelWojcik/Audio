package net.pwojcik.audio.gui.util.table;

import static net.pwojcik.audio.model.tag.AudioTagType.ALBUM;
import static net.pwojcik.audio.model.tag.AudioTagType.ARTIST;
import static net.pwojcik.audio.model.tag.AudioTagType.LENGTH;
import static net.pwojcik.audio.model.tag.AudioTagType.TITLE;

import java.util.HashMap;
import java.util.Map;

import net.pwojcik.audio.broadcast.Broadcaster;
import net.pwojcik.audio.gui.table.AbstractTagBasedTable;
import net.pwojcik.audio.model.tag.AudioTagType;

/**
 * Implementation of {@linkplain AbstractTagBasedTable} desired for Now Playing Module.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class NowPlayingTagBasedTable extends AbstractTagBasedTable {

	private static final String EMPTY_TABLE_MSG = "NowPlaying_NoData";

	public NowPlayingTagBasedTable(Broadcaster primaryBroadcaster) {
		super(primaryBroadcaster, EMPTY_TABLE_MSG);
	}

	@Override
	protected Map<AudioTagType, Float> getVisibleColumns() {
		Map<AudioTagType, Float> map = new HashMap<>();
		map.put(TITLE, Float.valueOf(0.30f));
		map.put(ARTIST, Float.valueOf(0.29f));
		map.put(ALBUM, Float.valueOf(0.29f));
		map.put(LENGTH, Float.valueOf(0.095f));
		return map;
	}

}

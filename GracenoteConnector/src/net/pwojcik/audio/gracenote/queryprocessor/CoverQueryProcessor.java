package net.pwojcik.audio.gracenote.queryprocessor;

import net.pwojcik.audio.gracenote.GracenoteException;
import net.pwojcik.audio.gracenote.xml.Query;
import net.pwojcik.audio.gracenote.xml.Query.QueryMode;
import net.pwojcik.audio.gracenote.xml.TextQueryCondition.TextQueryType;

/**
 * @author Pawel Wojcik
 * @version 1.0
 */
public class CoverQueryProcessor extends AlbumSearchQueryProcessor {

	private static final String NO_SUFFICIENT_DATA_EXC = "All TextQueryType fields in data map have "
			+ "to be filled to use cover finder.";

	/**
	 * @param clientIdentifier
	 * @param clientTag
	 */
	public CoverQueryProcessor(String clientIdentifier, String clientTag) {
		super(clientIdentifier, clientTag);
	}

	@Override
	public Query processQuery() {
		Query superQuery = super.processQuery();
		superQuery.setQueryMode(QueryMode.SINGLE_BEST_COVER);
		return superQuery;
	}

	@Override
	public void setData(Object object) {
		super.setData(object);
		if (!textQueryConditions.containsKey(TextQueryType.ALBUM_TITLE)
				|| !textQueryConditions.containsKey(TextQueryType.ARTIST)) {
			throw new GracenoteException(NO_SUFFICIENT_DATA_EXC);
		}
	}
}

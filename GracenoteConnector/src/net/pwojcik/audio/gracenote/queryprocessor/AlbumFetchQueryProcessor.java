package net.pwojcik.audio.gracenote.queryprocessor;

import net.pwojcik.audio.gracenote.GracenoteException;
import net.pwojcik.audio.gracenote.xml.MajorQuery;
import net.pwojcik.audio.gracenote.xml.Query;
import net.pwojcik.audio.gracenote.xml.Query.QueryCommand;

public final class AlbumFetchQueryProcessor extends AbstractQueryProcessor {

	private static final String INVALID_DATA_EXCEPTION = "Data type is invalid for "
			+ "AlbumFetchQueryProcessor. Given type should be String.";
	private String albumGracenoteID;

	public AlbumFetchQueryProcessor(String clientID, String clientTag) {
		super(clientID, clientTag);
	}

	@Override
	public void setData(Object gracenoteID) {
		if (gracenoteID instanceof String) {
			albumGracenoteID = (String) gracenoteID;
		} else {
			throw new GracenoteException(INVALID_DATA_EXCEPTION);
		}
	}

	@Override
	public Query processQuery() {
		Query query = new Query();
		query.setQueryCommand(QueryCommand.ALBUM_FETCH);
		query.setAlbumGracenoteID(albumGracenoteID);
		prepareOptions(query);
		return query;
	}

	@Override
	public void processAdditionalOperation(MajorQuery majorQuery) {
		prepareAuthorizationBlock(majorQuery);
	}

	@Override
	public void setUserID(String userID) {
		this.userID = userID;
	}
}

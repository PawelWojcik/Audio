package net.pwojcik.audio.gracenote.queryprocessor;

import java.util.Map;
import java.util.Map.Entry;
import net.pwojcik.audio.gracenote.GracenoteException;
import net.pwojcik.audio.gracenote.xml.MajorQuery;
import net.pwojcik.audio.gracenote.xml.Query;
import net.pwojcik.audio.gracenote.xml.Range;
import net.pwojcik.audio.gracenote.xml.TextQueryCondition;
import net.pwojcik.audio.gracenote.xml.Query.QueryCommand;
import net.pwojcik.audio.gracenote.xml.TextQueryCondition.TextQueryType;

public class AlbumSearchQueryProcessor extends AbstractQueryProcessor {
	
	private static final String INVALID_DATA_EXCEPTION = "Data type is invalid for "
			+ "AlbumSearchQueryProcessor. Given type should be Map<TextQueryType, String>.";
	protected Map<TextQueryType, String> textQueryConditions;

	public AlbumSearchQueryProcessor(String clientIdentifier, String clientTag) {
		super(clientIdentifier, clientTag);
	}

	@Override
	public Query processQuery() {
		Query query = new Query();
		query.setQueryCommand(QueryCommand.ALBUM_SEARCH);
		setQueryResultRange(query);
		prepareTextQueryConditions(query);
		prepareOptions(query);
		return query;
	}
	
	@Override
	public void processAdditionalOperation(MajorQuery majorQuery) {
		prepareAuthorizationBlock(majorQuery);
	}
	
	protected void setQueryResultRange(Query query) {
		Range range = new Range();
		range.setStart(Integer.toString(1));
		range.setEnd(Integer.toString(25));
		query.setRange(range);
	}
	
	protected void prepareTextQueryConditions(Query query) {
		for (Entry<TextQueryType, String> entry : textQueryConditions.entrySet()) {
			TextQueryCondition qc = new TextQueryCondition();
			qc.setType(entry.getKey());
			qc.setValue(entry.getValue());
			query.addTextQueryCondition(qc);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void setData(Object object) {
		if(object instanceof Map) {
			textQueryConditions = (Map<TextQueryType, String>) object;
		} else {
			throw new GracenoteException(INVALID_DATA_EXCEPTION);
		}
	}

	@Override
	public void setUserID(String userID) {
		this.userID = userID;
	}
}

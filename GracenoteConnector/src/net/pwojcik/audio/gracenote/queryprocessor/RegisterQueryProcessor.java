package net.pwojcik.audio.gracenote.queryprocessor;

import net.pwojcik.audio.gracenote.xml.MajorQuery;
import net.pwojcik.audio.gracenote.xml.Query;
import net.pwojcik.audio.gracenote.xml.Query.QueryCommand;

public final class RegisterQueryProcessor extends AbstractQueryProcessor {

	public RegisterQueryProcessor(String clientIdentifier, String clientTag) {
		super(clientIdentifier, clientTag);
	}
	
	@Override
	public Query processQuery() {
		Query query = new Query();
		query.setQueryCommand(QueryCommand.REGISTER);
		query.setClientIdentifier(clientID);
		return query;
	}

	@Override
	public void processAdditionalOperation(MajorQuery majorQuery) {
	}

	@Override
	public void setData(Object obj) {
	}

	@Override
	public void setUserID(String userID) {
	}

}

package net.pwojcik.audio.gracenote.queryprocessor;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.pwojcik.audio.gracenote.xml.Authorization;
import net.pwojcik.audio.gracenote.xml.MajorQuery;
import net.pwojcik.audio.gracenote.xml.Option;
import net.pwojcik.audio.gracenote.xml.Query;
import net.pwojcik.audio.gracenote.xml.Option.Parameter;

public abstract class AbstractQueryProcessor implements QueryProcessor {

	protected final String clientID;
	protected String userID;
	private Map<Parameter, String> queryParams;

	protected AbstractQueryProcessor(String clientIdentifier, String clientTag) {
		clientID = clientIdentifier + "-" + clientTag;
		queryParams = prepareQueryParams();
	}

	protected Map<Parameter, String> prepareQueryParams() {
		Map<Parameter, String> params = new HashMap<>();
		params.put(Parameter.COVER_SIZE, "MEDIUM,SMALL,THUMBNAIL,LARGE,XLARGE");
		params.put(Parameter.FALLBACK_GENRECOVER, "YES");
		params.put(Parameter.SELECT_EXTENDED, "COVER,REVIEW,ARTIST_BIOGRAPHY,ARTIST_IMAGE");
		return params;
	}

	@Override
	public MajorQuery processMajorQuery() {
		MajorQuery majorQuery = new MajorQuery();
		processAdditionalOperation(majorQuery);
		Query processedQuery = processQuery();
		majorQuery.addQuery(processedQuery);
		return majorQuery;
	}

	protected final void prepareAuthorizationBlock(MajorQuery majorQuery) {
		Authorization auth = new Authorization();
		auth.setClientID(clientID);
		auth.setUserID(userID);
		majorQuery.setAuthorization(auth);
	}

	protected void prepareOptions(Query query) {
		if (!queryParams.isEmpty()) {
			for (Entry<Parameter, String> entry : queryParams.entrySet()) {
				Option option = new Option();
				option.setParameter(entry.getKey());
				option.setValue(entry.getValue());
				query.addOption(option);
			}
		}
	}

	protected abstract Query processQuery();
}

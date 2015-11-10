package net.pwojcik.audio.gracenote;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.pwojcik.audio.gracenote.queryprocessor.AlbumFetchQueryProcessor;
import net.pwojcik.audio.gracenote.queryprocessor.AlbumSearchQueryProcessor;
import net.pwojcik.audio.gracenote.queryprocessor.CoverQueryProcessor;
import net.pwojcik.audio.gracenote.queryprocessor.QueryProcessor;
import net.pwojcik.audio.gracenote.queryprocessor.RegisterQueryProcessor;
import net.pwojcik.audio.gracenote.xml.Album;
import net.pwojcik.audio.gracenote.xml.MajorQuery;
import net.pwojcik.audio.gracenote.xml.MajorResponse;
import net.pwojcik.audio.gracenote.xml.Query.QueryCommand;
import net.pwojcik.audio.gracenote.xml.Response;
import net.pwojcik.audio.gracenote.xml.TextQueryCondition.TextQueryType;

/**
 * Class supporting communication with Gracenote Web API Server. Gracenote is a
 * service that provides various information about songs, music albums and
 * artist.
 * 
 * @author Pawel Wojcik
 * @version 1.0
 */
public class GracenoteConnector {

	public static final String USER_ID = "259612896117224195-64722537622769CEAF994C6A5AE179E9";
	private static final String CLIENT_ID = "13389824";
	private static final String CLIENT_TAG = "A0015C599D012C090217A32ABB502DA1";
	private static final String SERVER_URL = "https://c" + CLIENT_ID + ".web.cddbp.net/webapi/xml/1.0/";
	
	private Map<QueryCommand, QueryProcessor> queryProcessors;
	private final GracenoteConnectionManager connectionManager;
	private final GracenoteXMLManager xmlManager;
	private String userID;

	/**
	 * Default constructor for GracenoteConnector. Registers component in
	 * Gracenote Web API Service.
	 */
	public GracenoteConnector() {
		connectionManager = new GracenoteConnectionManager();
		xmlManager = new GracenoteXMLManager();
		queryProcessors = prepareQueryProcessors();
	}

	/**
	 * Constructs new connector with given userID. In this case, client doesn't
	 * have to be registered anymore.
	 * 
	 * @param newUserID
	 *            userID fetched after first registration
	 */
	public GracenoteConnector(String newUserID) {
		this();
		userID = newUserID;
	}

	/**
	 * Return userID fetched after first registration
	 * 
	 * @return user ID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * Searches for album, artist and track data using ALBUM_SEARCH mode.
	 * 
	 * @param criteria
	 *            query criteria
	 * @return Gracenote Web API Server formatted response
	 */
	public Response search(Map<TextQueryType, String> criteria) {
		registerUserIfNecessary();
		Response response = processQuery(QueryCommand.ALBUM_SEARCH, criteria);
		return response;
	}

	/**
	 * Searches for artwork using ALBUM_SEARCH mode.
	 * 
	 * @param criteria
	 *            query criteria
	 * @return Gracenote Web API Server formatted response
	 */
	public Response searchCover(Map<TextQueryType, String> criteria) {
		registerUserIfNecessary();
		Response response = processQuery(QueryCommand.COVER_SEARCH, criteria);
		return response;
	}

	/**
	 * Searches for album, artist and track data using ALBUM_FETCH mode.
	 * 
	 * @param gracenoteID
	 *            album Gracenote ID
	 * @return Gracenote Web API Server formatted response
	 */
	public Response search(String gracenoteID) {
		registerUserIfNecessary();
		Response response = processQuery(QueryCommand.ALBUM_FETCH, gracenoteID);
		return response;
	}

	/**
	 * 
	 * @param albums
	 * @return
	 */
	public List<GracenoteExtendedData> getExtendedList(List<Album> albums) {
		return GracenoteUtil.getExtendedList(albums);
	}

	/**
	 * 
	 * @param albums
	 * @return
	 */
	public String getCoverURL(List<Album> albums) {
		return GracenoteUtil.getCoverURL(albums);
	}

	private Map<QueryCommand, QueryProcessor> prepareQueryProcessors() {
		Map<QueryCommand, QueryProcessor> processors = new HashMap<>();
		processors.put(QueryCommand.REGISTER, new RegisterQueryProcessor(CLIENT_ID, CLIENT_TAG));
		processors.put(QueryCommand.ALBUM_SEARCH, new AlbumSearchQueryProcessor(CLIENT_ID, CLIENT_TAG));
		processors.put(QueryCommand.ALBUM_FETCH, new AlbumFetchQueryProcessor(CLIENT_ID, CLIENT_TAG));
		processors.put(QueryCommand.COVER_SEARCH, new CoverQueryProcessor(CLIENT_ID, CLIENT_TAG));
		return processors;
	}

	private void registerUserIfNecessary() {
		if (userID == null) {
			Response response = processQuery(QueryCommand.REGISTER, null);
			userID = response.getUserID();
		}
	}

	private Response processQuery(QueryCommand command, Object data) {
		QueryProcessor processor = queryProcessors.get(command);
		processor.setData(data);
		processor.setUserID(userID);
		MajorQuery majorQuery = processor.processMajorQuery();
		String marshalledQuery = xmlManager.marshallQueryToString(majorQuery);
		String responseString = connectionManager.executeRequestAndFetchResponse(SERVER_URL, marshalledQuery);
		MajorResponse majResponse = xmlManager.unmarshallQueryString(responseString);
		if (majResponse.isResponseValid()) {
			return majResponse.getResponses().get(0);
		}

		throw new GracenoteException(majResponse.getErrorMessage());
	}

}
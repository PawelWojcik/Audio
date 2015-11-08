package net.pwojcik.audio.gracenote;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
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
import net.pwojcik.audio.gracenote.xml.URLLink.URLType;
import net.pwojcik.audio.gracenote.xml.Track;
import net.pwojcik.audio.gracenote.xml.URLLink;

/**
 * Class supporting communication with Gracenote Web API Server.
 * Gracenote is a service that provides various information about songs, music albums
 * and artist.
 * @author Pawel Wojcik
 * @version 1.0
 */
public class GracenoteConnector {

	public static final String USER_ID = "259612896117224195-64722537622769CEAF994C6A5AE179E9";
	private static final String POST_METHOD = "POST";
	private static final String CONTENT_TYPE_ATTR = "Content-Type";
	private static final String CONTENT_TYPE_TEXT_PLAIN_VALUE = "text/plain";
	private static final String CHARSET_ATTR = "Charset";
	private static final String CHARSET_UTF_8_VALUE = "utf-8";
	private static final String CONTENT_LENGTH_ATTR = "Content-Length";
	private static final String CLIENT_ID = "13389824";
	private static final String CLIENT_TAG = "A0015C599D012C090217A32ABB502DA1";

	private String serverURL = "https://c*.web.cddbp.net/webapi/xml/1.0/";
	private String userID;
	private Map<QueryCommand, QueryProcessor> queryProcessors;

	/**
	 * Default constructor for GracenoteConnector. 
	 * Registers component in Gracenote Web API Service.
	 */
	public GracenoteConnector() {
		serverURL = serverURL.replace("*", CLIENT_ID);
		queryProcessors = prepareQueryProcessors();
	}

	/**
	 * Constructs new connector with given userID. 
	 * In this case, client doesn't have to be registered anymore.
	 * @param newUserID userID fetched after first registration
	 */
	public GracenoteConnector(String newUserID) {
		this();
		userID = newUserID;
	}

	/** 
	 * Return userID fetched after first registration
	 * @return user ID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * Searches for album, artist and track data using ALBUM_SEARCH mode.
	 * @param criteria query criteria
	 * @return Gracenote Web API Server formatted response
	 */
	public Response search(Map<TextQueryType, String> criteria) {
		registerUserIfNecessary();
		Response response = processQuery(QueryCommand.ALBUM_SEARCH, criteria);
		return response;
	}

	/**
	 * Searches for artwork using ALBUM_SEARCH mode.
	 * @param criteria query criteria
	 * @return Gracenote Web API Server formatted response
	 */
	public Response searchCover(Map<TextQueryType, String> criteria) {
		registerUserIfNecessary();
		Response response = processQuery(QueryCommand.COVER_SEARCH, criteria);
		return response;
	}

	/**
	 * Searches for album, artist and track data using ALBUM_FETCH mode.
	 * @param gracenoteID album Gracenote ID
	 * @return Gracenote Web API Server formatted response
	 */
	public Response search(String gracenoteID) {
		registerUserIfNecessary();
		Response response = processQuery(QueryCommand.ALBUM_FETCH, gracenoteID);
		return response;
	}

	public List<GracenoteExtendedData> getExtendedList(List<Album> albums) {
		List<GracenoteExtendedData> list = new ArrayList<>(albums.size());
		for (Album album : albums) {
			for (Track track : album.getTracks()) {
				GracenoteExtendedData data = new GracenoteExtendedData();
				data.setAlbum(album.getTitle());
				data.setArtist(album.getArtist());
				data.setGenre(track.getGenre());
				data.setTrackNumber(track.getTrackNumber());
				data.setTrackTitle(track.getTitle());
				data.setYear(album.getReleaseYear());
				list.add(data);
			}
		}
		return list;
	}

	public String getCoverURL(List<Album> albums) {
		String link = "";
		for (Album album : albums) {
			boolean coverFound = false;
			if (album.getUrlLinks() != null) {
				Iterator<URLLink> urls = album.getUrlLinks().iterator();
				while (!coverFound && urls.hasNext()) {
					URLLink currentURL = urls.next();
					if (currentURL.getUrlType() == URLType.COVERART) {
						link = currentURL.getValue();
						coverFound = true;
					}
				}
			}
		}
		return link;
	}

	private Map<QueryCommand, QueryProcessor> prepareQueryProcessors() {
		Map<QueryCommand, QueryProcessor> processors = new HashMap<>();

		processors.put(QueryCommand.REGISTER, new RegisterQueryProcessor(CLIENT_ID, CLIENT_TAG));
		processors.put(QueryCommand.ALBUM_SEARCH, new AlbumSearchQueryProcessor(CLIENT_ID,
				CLIENT_TAG));
		processors.put(QueryCommand.ALBUM_FETCH,
				new AlbumFetchQueryProcessor(CLIENT_ID, CLIENT_TAG));
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
		String marshalledQuery = marshallQueryToString(majorQuery);
		String responseString = executeRequestAndFetchResponse(marshalledQuery);
		MajorResponse majResponse = unmarshallQueryString(responseString);
		if (majResponse.isResponseValid()) {
			return majResponse.getResponses().get(0);
		}

		throw new GracenoteException(majResponse.getErrorMessage());
	}

	private String marshallQueryToString(MajorQuery majorQuery) {
		try {
			StringWriter stringWriter = new StringWriter();
			JAXBContext jaxbContext = JAXBContext.newInstance(MajorQuery.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			marshaller.marshal(majorQuery, stringWriter);
			return stringWriter.toString();
		} catch (JAXBException e) {
			throw new GracenoteException(e.getMessage());
		}
	}

	private MajorResponse unmarshallQueryString(String queryString) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(MajorResponse.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(queryString);
			MajorResponse result = (MajorResponse) unmarshaller.unmarshal(reader);
			return result;
		} catch (JAXBException e) {
			throw new GracenoteException(e.getMessage());
		}
	}

	private String executeRequestAndFetchResponse(String requestData) {
		String result;
		int requestDataLengthInBytes = requestData.getBytes().length;

		try {
			URL url = new URL(serverURL);
			HttpURLConnection connection = prepareConnection(requestDataLengthInBytes, url);

			sendDataToServer(connection, requestData);
			result = retrieveData(connection);

			connection.disconnect();
		} catch (IOException e) {
			throw new GracenoteException(e.getMessage());
		}

		return result;
	}

	private String retrieveData(HttpURLConnection connection) throws UnsupportedEncodingException,
			IOException {
		StringBuilder stringBuilder = new StringBuilder();
		InputStreamReader isr = new InputStreamReader(connection.getInputStream(),
				CHARSET_UTF_8_VALUE.toUpperCase());
		BufferedReader reader = new BufferedReader(isr);

		String line;
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
		}
		reader.close();

		return stringBuilder.toString();
	}

	private void sendDataToServer(HttpURLConnection connection, String data)
			throws UnsupportedEncodingException, IOException {
		OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream(),
				CHARSET_UTF_8_VALUE.toUpperCase());
		BufferedWriter writer = new BufferedWriter(osw);
		writer.write(data);
		writer.flush();
		writer.close();
	}

	private HttpURLConnection prepareConnection(int requestDataLengthInBytes, URL url)
			throws IOException, ProtocolException {
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setInstanceFollowRedirects(false);
		connection.setRequestMethod(POST_METHOD);
		connection.setRequestProperty(CONTENT_TYPE_ATTR, CONTENT_TYPE_TEXT_PLAIN_VALUE);
		connection.setRequestProperty(CHARSET_ATTR, CHARSET_UTF_8_VALUE);
		connection.setRequestProperty(CONTENT_LENGTH_ATTR,
				"" + Integer.toString(requestDataLengthInBytes));
		connection.setUseCaches(false);
		return connection;
	}
}
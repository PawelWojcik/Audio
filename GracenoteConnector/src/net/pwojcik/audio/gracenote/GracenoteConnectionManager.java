package net.pwojcik.audio.gracenote;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * 
 * @author Pawel Wojcik
 *
 */
final class GracenoteConnectionManager {

	private static final String POST_METHOD = "POST";
	private static final String CONTENT_TYPE_ATTR = "Content-Type";
	private static final String CONTENT_TYPE_TEXT_PLAIN_VALUE = "text/plain";
	private static final String CHARSET_ATTR = "Charset";
	private static final String CONTENT_LENGTH_ATTR = "Content-Length";
	private static final String CHARSET_UTF_8_VALUE = "utf-8";
	
	public String executeRequestAndFetchResponse(String serverURL, String requestData) {
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

	private String retrieveData(HttpURLConnection connection) throws UnsupportedEncodingException, IOException {
		StringBuilder stringBuilder = new StringBuilder();
		InputStreamReader isr = new InputStreamReader(connection.getInputStream(), CHARSET_UTF_8_VALUE.toUpperCase());
		try (BufferedReader reader = new BufferedReader(isr)) {
			String line;
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
			}
			reader.close();
		}
		return stringBuilder.toString();
	}

	private void sendDataToServer(HttpURLConnection connection, String data)
			throws UnsupportedEncodingException, IOException {
		OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream(),
				CHARSET_UTF_8_VALUE.toUpperCase());
		try (BufferedWriter writer = new BufferedWriter(osw)) {
			writer.write(data);
			writer.flush();
			writer.close();
		}
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
		connection.setRequestProperty(CONTENT_LENGTH_ATTR, "" + Integer.toString(requestDataLengthInBytes));
		connection.setUseCaches(false);
		return connection;
	}

}

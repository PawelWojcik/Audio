package net.pwojcik.audio.gracenote;

import java.util.HashMap;
import java.util.Map;

import net.pwojcik.audio.gracenote.xml.Response;
import net.pwojcik.audio.gracenote.xml.Response.ResponseType;
import net.pwojcik.audio.gracenote.xml.TextQueryCondition.TextQueryType;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Pawel Wojcik
 * @version 1.0
 */
public class GracenoteWebAPIWithUserIDTest extends AbstractGracenoteWebAPITest{

	private static final String USER_ID = "259612896117224195-64722537622769CEAF994C6A5AE179E9";
	
	@BeforeClass
	public static void initializeData() {
		api = new GracenoteConnector(USER_ID);
	}
	
	@Test
	public void checkWhetherUserIDHasBeenModified() {
		Map<TextQueryType, String> criteria = new HashMap<>();
		criteria.put(TextQueryType.ARTIST, ARTIST_LABEL);
		Response response = api.search(criteria);

		Assert.assertEquals(ResponseType.OK, response.getResponseStatus());
		Assert.assertEquals(USER_ID, api.getUserID());
	}
}

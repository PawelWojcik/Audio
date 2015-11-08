package net.pwojcik.audio.gracenote;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.pwojcik.audio.gracenote.xml.Album;
import net.pwojcik.audio.gracenote.xml.Response;
import net.pwojcik.audio.gracenote.xml.Response.ResponseType;
import net.pwojcik.audio.gracenote.xml.TextQueryCondition.TextQueryType;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Pawel Wojcik
 * @version 1.0
 */
@Ignore
public abstract class AbstractGracenoteWebAPITest {
	
	protected static final String ARTIST_LABEL = "Nine inch nails";
	protected static final String VARIOUS_ARTISTS_LABEL = "Various artists";
	protected static final String SAMPLE_GRACENOTE_ALBUM_ID = "5113714-2154763688B3737CB70D08819AAE1D5B";
	
	protected static GracenoteConnector api;
	
	@Test
	public void searchWithGivenArtist() {
		Map<TextQueryType, String> criteria = new HashMap<>();
		criteria.put(TextQueryType.ARTIST, ARTIST_LABEL);
		Response response = api.search(criteria);

		Assert.assertEquals(ResponseType.OK, response.getResponseStatus());
		List<Album> albums = response.getAlbums();

		Assert.assertFalse(albums.isEmpty());
		Album firstAlbum = albums.get(0);

		boolean validArtist = firstAlbum.getArtist().equalsIgnoreCase(ARTIST_LABEL) 
				|| firstAlbum.getArtist().equalsIgnoreCase(VARIOUS_ARTISTS_LABEL);

		Assert.assertTrue(validArtist);
	}
	
	@Test
	public void searchWithGivenGracenoteID() {
		Map<TextQueryType, String> criteria = new HashMap<>();
		criteria.put(TextQueryType.ARTIST, ARTIST_LABEL);
		Response response = api.search(SAMPLE_GRACENOTE_ALBUM_ID);

		Assert.assertEquals(ResponseType.OK, response.getResponseStatus());
		List<Album> albums = response.getAlbums();
		
		Assert.assertEquals(1, albums.size());
		Assert.assertFalse(albums.isEmpty());
		Album firstAlbum = albums.get(0);

		boolean validArtist = firstAlbum.getArtist().equalsIgnoreCase(ARTIST_LABEL);

		Assert.assertTrue(validArtist);
	}
}

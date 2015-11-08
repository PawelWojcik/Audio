package net.pwojcik.audio.gracenote;

import org.junit.BeforeClass;

/**
 * @author Pawel Wojcik
 * @version 1.0
 */
public class GracenoteWebAPIWithoutPreparedUserIDTest extends AbstractGracenoteWebAPITest{

	@BeforeClass
	public static void initializeData() {
		api = new GracenoteConnector();
	}

}

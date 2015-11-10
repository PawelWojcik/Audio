package net.pwojcik.audio.segment;

import javafx.scene.layout.Pane;
import net.pwojcik.audio.broadcast.BroadcastParticipant;

/**
 * Segment is considered as unit which describes some area in application's workspace.
 * For example, navigation tree can be considered as segment.
 * @author Pawel Wojcik
 * @version 1.0
 */
public interface Segment extends BroadcastParticipant {

	/**
	 * Determines type of segment.
	 * @return type of segment
	 */
	SegmentType getSegmentType();
	

	Pane getCanvas();
	
}

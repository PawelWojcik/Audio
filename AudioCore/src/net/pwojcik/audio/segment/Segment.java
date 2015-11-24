package net.pwojcik.audio.segment;

import javafx.scene.layout.Pane;
import net.pwojcik.audio.broadcast.BroadcastParticipant;

/**
 * Segment is considered as unit which describes some area in application's
 * workspace. For example, navigation tree can be considered as segment.
 * 
 * @author Pawel Wojcik
 * @version 1.0
 */
public interface Segment<S extends SegmentState> extends BroadcastParticipant {

	/**
	 * Determines type of segment.
	 * 
	 * @return type of segment
	 */
	SegmentType getSegmentType();

	/**
	 * Returns GUI representation of Segment, depending on current state.
	 * 
	 * @return GUI canvas
	 */
	Pane getCanvas();

	/**
	 * Returns current state of Segment.
	 * 
	 * @return current state
	 */
	S getState();

	/**
	 * Sets new state of Segment. If state changes, then GUI of it is produced
	 * in different way.
	 * 
	 * @param state
	 *            new state
	 */
	void setState(S state);

}

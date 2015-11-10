package net.pwojcik.audio.segment;

/**
 * Specific type of Segment in application.
 * 
 * @author Pawel Wojcik
 * @version 1.0
 */
public enum SegmentType {

	/**
	 * Control Segment is located where all player's control buttons are
	 * present.
	 */
	CONTROL,

	/**
	 * Navigation Segment describes area when main application's navigation is
	 * located.
	 */
	NAVIGATION,

	/**
	 * Seek Segment represents area where currently played track's moment can be
	 * changed.
	 */
	SEEK,

	/**
	 * Scene Segment is main area of application where content area is shared
	 * between modules.
	 */
	SCENE
}

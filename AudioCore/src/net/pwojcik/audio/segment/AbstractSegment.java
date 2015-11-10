package net.pwojcik.audio.segment;

import net.pwojcik.audio.broadcast.Broadcaster;

/**
 * Abstract representation of  {@link net.pwojcik.audio.segment.Segment}.
 * @author Pawel Wojcik
 * @version 1.0
 */
public abstract class AbstractSegment implements Segment {
	
	private Broadcaster broadcaster;
	
	@Override
	public void visit(Broadcaster primaryBroadcaster) {
		broadcaster = primaryBroadcaster;
	}
	
	/**
	 * Returns application's primary broadcaster.
	 * @return major broadcaster
	 */
	protected final Broadcaster getBroadcaster() {
		return broadcaster;
	}
}

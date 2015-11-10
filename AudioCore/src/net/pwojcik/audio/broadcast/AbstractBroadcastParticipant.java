package net.pwojcik.audio.broadcast;

public abstract class AbstractBroadcastParticipant implements BroadcastParticipant {

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

package net.pwojcik.audio.broadcast;

import net.pwojcik.audio.flowdata.FlowData;
import net.pwojcik.audio.flowdata.FlowHandler;
import net.pwojcik.audio.flowdata.IdleFlowHandler;

/**
 * Abstract representation of BroadcastParticipant.
 * @author Pawel Wojcik
 * @version 1.0
 */
public abstract class AbstractBroadcastParticipant implements BroadcastParticipant {

	private Broadcaster broadcaster;
	
	@Override
	public void visit(Broadcaster primaryBroadcaster) {
		broadcaster = primaryBroadcaster;
	}
	
	@Override
	public FlowHandler handleData(FlowData data) {
		return IdleFlowHandler.getInstance();
	}
	
	/**
	 * Returns application's primary broadcaster.
	 * @return major broadcaster
	 */
	protected final Broadcaster getBroadcaster() {
		return broadcaster;
	}

	
}

package net.pwojcik.audio.flowdata;

/**
 * Stub for FlowHandler object. It has no logic inside.
 * This class is used as default returned object in abstract Segments and Modules.
 * For this purpose class is designed as Singleton.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class IdleFlowHandler implements FlowHandler {

	private static IdleFlowHandler instance;

	/**
	 * Gets Singleton instance of handler.
	 * @return instance of handler
	 */
	public static IdleFlowHandler getInstance() {
		if (instance == null) {
			instance = new IdleFlowHandler();
		}
		return instance;
	}

	private IdleFlowHandler() {
	}

	@Override
	public void handle(FlowData data) {
		// Do nothing by default
	}

}

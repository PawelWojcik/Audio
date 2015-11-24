package net.pwojcik.audio.flowhandler;

import net.pwojcik.audio.flowdata.CurrentPlaylistChangeFlowData;
import net.pwojcik.audio.flowdata.FlowData;
import net.pwojcik.audio.flowdata.FlowHandler;
import net.pwojcik.audio.module.NowPlayingModuleCanvasFactory;

/**
 * Handler run when application's current playlist is changed.
 * @author Pawel Wojcik
 * @version 1.0
 */
public class CurrentPlaylistChangeFlowHandler implements FlowHandler {

	private NowPlayingModuleCanvasFactory canvasFactory;

	public CurrentPlaylistChangeFlowHandler(NowPlayingModuleCanvasFactory factory) {
		canvasFactory = factory;
	}

	@Override
	public void handle(FlowData data) {
		CurrentPlaylistChangeFlowData flowData = (CurrentPlaylistChangeFlowData) data;
		canvasFactory.updateTable(flowData.getCurrentPlaylist());
	}

}

package net.pwojcik.audio.module;

import net.pwojcik.audio.flowdata.CurrentPlaylistChangeFlowData;
import net.pwojcik.audio.flowdata.FlowData;
import net.pwojcik.audio.flowdata.FlowHandler;
import net.pwojcik.audio.flowhandler.CurrentPlaylistChangeFlowHandler;
import net.pwojcik.audio.locale.I18N;
import net.pwojcik.audio.tree.DefaultTreeDescription;
import net.pwojcik.audio.tree.DefaultTreeDescription.TreeDescriptionMode;
import net.pwojcik.audio.tree.TreeDescription;

/**
 * Class representing Now Playing Module.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class NowPlayingModule extends AbstractModule<DesktopCanvasDataContainer<NowPlayingModuleCanvasFactory>> {

	private static final String TREE_LABEL = "NowPlaying_TreeLabel";
	private static final String CANVAS_LABEL = "NowPlaying_CanvasLabel";
	private static final String ICON_FILE = "nowplaying.png";

	@Override
	public FlowHandler handleData(FlowData data) {
		if (data.isInstanceOf(CurrentPlaylistChangeFlowData.class)) {
			NowPlayingModuleCanvasFactory canvasFactory = getCanvasDataContainer().getCanvasFactory();
			return new CurrentPlaylistChangeFlowHandler(canvasFactory);
		}
		return super.handleData(data);
	}

	@Override
	protected DesktopCanvasDataContainer<NowPlayingModuleCanvasFactory> produceCanvasDataContainer() {
		String canvasLabel = I18N.label(CANVAS_LABEL);
		NowPlayingModuleCanvasFactory factory = new NowPlayingModuleCanvasFactory(getBroadcaster());
		String treeLabel = I18N.label(TREE_LABEL);
		TreeDescription treeDescription = new DefaultTreeDescription(treeLabel, ICON_FILE,
				TreeDescriptionMode.WITH_ICON);
		DesktopCanvasDataContainer<NowPlayingModuleCanvasFactory> container = new DesktopCanvasDataContainerImpl<>(
				factory, canvasLabel, treeDescription);
		return container;
	}

}

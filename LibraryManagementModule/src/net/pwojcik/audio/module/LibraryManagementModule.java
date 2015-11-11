package net.pwojcik.audio.module;

import net.pwojcik.audio.dataprovider.LibraryDataManager;

import net.pwojcik.audio.flowdata.FlowData;
import net.pwojcik.audio.flowdata.FlowHandler;
import net.pwojcik.audio.flowdata.LibraryContentChangeFlowData;
import net.pwojcik.audio.flowhandler.LibraryContentChangeFlowHandler;
import net.pwojcik.audio.locale.I18N;
import net.pwojcik.audio.tree.DefaultTreeDescription;
import net.pwojcik.audio.tree.DefaultTreeDescription.TreeDescriptionMode;
import net.pwojcik.audio.tree.TreeDescription;

/**
 * Class representing Library Management module.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class LibraryManagementModule
		extends AbstractModule<DesktopCanvasDataContainer<LibraryManagementModuleCanvasFactory>> {

	private static final String TREE_LABEL = "LibraryManagement_TreeLabel";
	private static final String CANVAS_LABEL = "LibraryManagement_CanvasLabel";
	private static final String ICON_FILE = "library.png";

	@Override
	public void postConstruct() {
		LibraryDataManager libraryManager = new LibraryDataManager();
		getBroadcaster().broadcastData(new LibraryContentChangeFlowData(libraryManager.getMainLevelDirectoryList(),
				libraryManager.getAudioList()));
	}

	@Override
	public FlowHandler handleData(FlowData data) {
		if (data.isInstanceOf(LibraryContentChangeFlowData.class)) {
			LibraryManagementModuleCanvasFactory canvasFactory = getCanvasDataContainer().getCanvasFactory();
			return new LibraryContentChangeFlowHandler(canvasFactory);
		}
		return super.handleData(data);
	}

	@Override
	protected DesktopCanvasDataContainer<LibraryManagementModuleCanvasFactory> produceCanvasDataContainer() {
		String canvasLabel = I18N.label(CANVAS_LABEL);
		LibraryManagementModuleCanvasFactory factory = new LibraryManagementModuleCanvasFactory();
		String treeLabel = I18N.label(TREE_LABEL);
		TreeDescription description = new DefaultTreeDescription(treeLabel, ICON_FILE, TreeDescriptionMode.WITH_ICON);
		DesktopCanvasDataContainer<LibraryManagementModuleCanvasFactory> container = new DesktopCanvasDataContainerImpl<>(
				factory, canvasLabel, description);
		return container;
	}

}

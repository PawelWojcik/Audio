package net.pwojcik.audio.module;

import java.util.Optional;

import net.pwojcik.audio.container.LibrarySelectedContentContainer;
import net.pwojcik.audio.dataprovider.LibraryDataManager;
import net.pwojcik.audio.flowdata.CurrentPlaylistChangeFlowData;
import net.pwojcik.audio.flowdata.FlowData;
import net.pwojcik.audio.flowdata.FlowHandler;
import net.pwojcik.audio.flowdata.LibraryContentChangeFlowData;
import net.pwojcik.audio.flowhandler.LibraryContentChangeFlowHandler;
import net.pwojcik.audio.locale.I18N;
import net.pwojcik.audio.model.playlist.PlaylistImpl;
import net.pwojcik.audio.tree.DefaultTreeDescription;
import net.pwojcik.audio.tree.DefaultTreeDescription.TreeDescriptionMode;
import net.pwojcik.audio.tree.TreeDescription;

/**
 * Class representing Library Management module.
 * 
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class LibraryManagementModule
		extends AbstractModule<DesktopCanvasDataContainer<LibraryManagementModuleCanvasFactory>> {

	private static final String TREE_LABEL = "LibraryManagement_TreeLabel";
	private static final String CANVAS_LABEL = "LibraryManagement_CanvasLabel";
	private static final String ICON_FILE = "library.png";

	private LibraryDataManager libraryManager;

	@Override
	public void postConstruct() {
		libraryManager = new LibraryDataManager();
		LibraryContentChangeFlowData flowData = new LibraryContentChangeFlowData(
				libraryManager.getMainLevelDirectoryList(), libraryManager.getAudioList());
		flowData.setInitializationSignal(true);
		getBroadcaster().broadcastData(flowData);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <P> Optional<P> provide(Class<P> classRepresentation) {
		if (classRepresentation.equals(LibraryDataManager.class)) {
			return (Optional<P>) Optional.of(libraryManager);
		} else if (classRepresentation.equals(LibrarySelectedContentContainer.class)) {
			LibrarySelectedContentContainer selectionContainer = getCanvasDataContainer().getCanvasFactory()
					.getSelectionContainer();
			return (Optional<P>) Optional.of(selectionContainer);
		}
		return super.provide(classRepresentation);
	}

	@Override
	public FlowHandler handleData(FlowData data) {
		if (data.isInstanceOf(LibraryContentChangeFlowData.class)) {
			LibraryContentChangeFlowData libData = (LibraryContentChangeFlowData) data;
			PlaylistImpl playlist = new PlaylistImpl(libData.getAllAudio());
			CurrentPlaylistChangeFlowData playlistFlowData = new CurrentPlaylistChangeFlowData(playlist);
			getBroadcaster().broadcastData(playlistFlowData);

			LibraryManagementModuleCanvasFactory canvasFactory = getCanvasDataContainer().getCanvasFactory();
			return new LibraryContentChangeFlowHandler(canvasFactory);
		}
		return super.handleData(data);
	}

	@Override
	protected DesktopCanvasDataContainer<LibraryManagementModuleCanvasFactory> produceCanvasDataContainer() {
		String canvasLabel = I18N.label(CANVAS_LABEL);
		LibraryManagementModuleCanvasFactory factory = new LibraryManagementModuleCanvasFactory(getBroadcaster());
		String treeLabel = I18N.label(TREE_LABEL);
		TreeDescription treeDescription = new DefaultTreeDescription(treeLabel, ICON_FILE,
				TreeDescriptionMode.WITH_ICON);
		DesktopCanvasDataContainer<LibraryManagementModuleCanvasFactory> container = new DesktopCanvasDataContainerImpl<>(
				factory, canvasLabel, treeDescription);
		return container;
	}

}

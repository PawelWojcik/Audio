package net.pwojcik.audio.flowhandler;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.pwojcik.audio.dataprovider.SourceDirectory;
import net.pwojcik.audio.flowdata.FlowData;
import net.pwojcik.audio.flowdata.FlowHandler;
import net.pwojcik.audio.flowdata.LibraryContentChangeFlowData;
import net.pwojcik.audio.module.LibraryManagementModuleCanvasFactory;
import net.pwojcik.audio.module.LibraryManagementModuleCanvasFactory.LibraryCanvasButtonType;

/**
 * Handler for library content change used in library GUI classes.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class LibraryContentChangeFlowHandler implements FlowHandler {

	private LibraryManagementModuleCanvasFactory canvasFactory;

	public LibraryContentChangeFlowHandler(LibraryManagementModuleCanvasFactory factory) {
		canvasFactory = factory;
	}

	@Override
	public void handle(FlowData data) {
		LibraryContentChangeFlowData flowData = (LibraryContentChangeFlowData) data;
		List<SourceDirectory> elementsList = new ArrayList<>(flowData.getDirectories());
		ObservableList<SourceDirectory> items = FXCollections.observableList(elementsList);
		canvasFactory.setListViewItems(items);
		canvasFactory.setButtonEnabled(LibraryCanvasButtonType.REMOVE, !items.isEmpty());
		canvasFactory.setButtonEnabled(LibraryCanvasButtonType.UPDATE, !flowData.isInitializationSignal());
	}

}

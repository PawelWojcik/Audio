package net.pwojcik.audio.evaluator;

import java.io.File;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import net.pwojcik.audio.broadcast.Broadcaster;
import net.pwojcik.audio.dataprovider.LibraryDataManager;
import net.pwojcik.audio.flowdata.LibraryContentChangeFlowData;

final class AddDirectoryToLibraryService extends Service<Void> {

	private final Broadcaster broadcaster;
	private final File selectedDirectory;
	private int foundFilesCount;

	AddDirectoryToLibraryService(File directory, Broadcaster primaryBroadcaster) {
		selectedDirectory = directory;
		broadcaster = primaryBroadcaster;
	}

	int getFoundFilesCount() {
		return foundFilesCount;
	}

	@Override
	protected Task<Void> createTask() {
		return new Task<Void>() {
			@Override
			protected Void call() {
				LibraryDataManager manager = broadcaster.request(LibraryDataManager.class);
				foundFilesCount = manager.addDirectory(selectedDirectory);
				LibraryContentChangeFlowData flowData = new LibraryContentChangeFlowData(
						manager.getMainLevelDirectoryList(), manager.getAudioList());
				broadcaster.broadcastData(flowData);
				return null;
			}
		};
	}
}
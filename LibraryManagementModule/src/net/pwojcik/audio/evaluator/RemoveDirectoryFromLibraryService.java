package net.pwojcik.audio.evaluator;

import java.util.Collection;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import net.pwojcik.audio.broadcast.Broadcaster;
import net.pwojcik.audio.dataprovider.LibraryDataManager;
import net.pwojcik.audio.dataprovider.SourceDirectory;
import net.pwojcik.audio.flowdata.LibraryContentChangeFlowData;

/**
 * Service running in another thread which removes tracks from library.
 * @author Pawel Wojcik
 * @version 1.0
 */
final class RemoveDirectoryFromLibraryService extends Service<Void> {

	private final Broadcaster broadcaster;
	private final Collection<SourceDirectory> selectedDirectories;

	RemoveDirectoryFromLibraryService(Collection<SourceDirectory> selection, Broadcaster primaryBroadcaster) {
		selectedDirectories = selection;
		broadcaster = primaryBroadcaster;
	}

	@Override
	protected Task<Void> createTask() {
		return new Task<Void>() {
			@Override
			protected Void call() {
				LibraryDataManager manager = broadcaster.request(LibraryDataManager.class);
				manager.removeDirectories(selectedDirectories);
				LibraryContentChangeFlowData flowData = new LibraryContentChangeFlowData(
						manager.getMainLevelDirectoryList(), manager.getAudioList());
				broadcaster.broadcastData(flowData);
				return null;
			}
		};
	}

}

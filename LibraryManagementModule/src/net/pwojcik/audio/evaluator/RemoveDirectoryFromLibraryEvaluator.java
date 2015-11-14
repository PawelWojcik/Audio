package net.pwojcik.audio.evaluator;

import java.util.Collection;

import eu.hansolo.enzo.notification.Notification;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ProgressIndicator;
import net.pwojcik.audio.broadcast.Broadcaster;
import net.pwojcik.audio.container.LibrarySelectedContentContainer;
import net.pwojcik.audio.dataprovider.SourceDirectory;
import net.pwojcik.audio.gui.util.GUIUtils;
import net.pwojcik.audio.locale.I18N;

public final class RemoveDirectoryFromLibraryEvaluator implements EventHandler<ActionEvent> {

	private static final String NOTIFICATION_TITLE = "LibraryManagement_RemoveDirectoryNotificationTitle";
	private static final String NOTIFICATION_MESSAGE = "LibraryManagement_RemoveDirectoryNotificationMessage";
	private final ProgressIndicator progressIndicator;
	private final Broadcaster broadcaster;

	public RemoveDirectoryFromLibraryEvaluator(ProgressIndicator progressIdct, Broadcaster primaryBroadcaster) {
		progressIndicator = progressIdct;
		broadcaster = primaryBroadcaster;
	}

	@Override
	public void handle(ActionEvent event) {
		LibrarySelectedContentContainer selectionContainer = broadcaster.request(LibrarySelectedContentContainer.class);
		Collection<SourceDirectory> selection = selectionContainer.getSelectedItems();
		if (selection != null && !selection.isEmpty()) {
			RemoveDirectoryFromLibraryService removeService = new RemoveDirectoryFromLibraryService(selection,
					broadcaster);
			removeService.setOnSucceeded(serviceEvent -> runOnFinish(removeService));
			removeService.start();
			progressIndicator.setVisible(true);
		}
	}

	private void runOnFinish(RemoveDirectoryFromLibraryService removeService) {
		String title = I18N.label(NOTIFICATION_TITLE);
		String message = I18N.label(NOTIFICATION_MESSAGE);
		GUIUtils.alert(title, message, Notification.SUCCESS_ICON);
		progressIndicator.setVisible(false);
		removeService.cancel();
	}

}

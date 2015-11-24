package net.pwojcik.audio.evaluator;

import java.io.File;

import eu.hansolo.enzo.notification.Notification;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import net.pwojcik.audio.broadcast.Broadcaster;
import net.pwojcik.audio.gui.util.GUIUtils;
import net.pwojcik.audio.locale.I18N;

/**
 * Evaluator run when "Add To Library" button is clicked.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class AddDirectoryToLibraryEvaluator implements EventHandler<ActionEvent> {

	private static final String DIRECTORY_CHOOSER_TITLE = "LibraryManagement_DirectoryChooserTitle";
	private static final String NOTIFICATION_TITLE = "LibraryManagement_AddDirectoryNotificationTitle";
	private static final String NOTIFICATION_MESSAGE = "LibraryManagement_AddDirectoryNotificationMessage";
	private final Broadcaster broadcaster;
	private final ProgressIndicator progressIndicator;

	public AddDirectoryToLibraryEvaluator(ProgressIndicator progressIdct, Broadcaster primaryBroadcaster) {
		progressIndicator = progressIdct;
		broadcaster = primaryBroadcaster;
	}

	@Override
	public void handle(ActionEvent event) {
		DirectoryChooser chooser = new DirectoryChooser();
		String chooserTitle = I18N.label(DIRECTORY_CHOOSER_TITLE);
		chooser.setTitle(chooserTitle);
		Stage stage = broadcaster.request(Stage.class);
		File selectedDirectory = chooser.showDialog(stage);
		if (selectedDirectory != null) {
			AddDirectoryToLibraryService addService = new AddDirectoryToLibraryService(selectedDirectory, broadcaster);
			addService.setOnSucceeded(serviceEvent -> runOnFinish(addService));
			addService.start();
			progressIndicator.setVisible(true);
		}
	}

	private void runOnFinish(AddDirectoryToLibraryService addService) {
		String title = I18N.label(NOTIFICATION_TITLE);
		String message = I18N.labelWithParameters(NOTIFICATION_MESSAGE,
				Integer.valueOf(addService.getFoundFilesCount()));
		GUIUtils.alert(title, message, Notification.SUCCESS_ICON);
		progressIndicator.setVisible(false);
		addService.cancel();
	}

}

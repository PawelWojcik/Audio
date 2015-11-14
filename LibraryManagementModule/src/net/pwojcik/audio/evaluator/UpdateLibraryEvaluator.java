package net.pwojcik.audio.evaluator;

import java.util.Optional;

import eu.hansolo.enzo.notification.Notification;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressIndicator;
import net.pwojcik.audio.broadcast.Broadcaster;
import net.pwojcik.audio.dataprovider.LibraryDataManager;
import net.pwojcik.audio.flowdata.LibraryContentChangeFlowData;
import net.pwojcik.audio.gui.util.GUIUtils;
import net.pwojcik.audio.locale.I18N;

public final class UpdateLibraryEvaluator implements EventHandler<ActionEvent> {

	private static final String NOTIFICATION_TITLE = "LibraryManagement_UpdateNotificationTitle";
	private static final String NOTIFICATION_MESSAGE = "LibraryManagement_UpdateNotificationMessage";
	private static final String ALERT_TITLE = "LibraryManagement_UpdateAlertTitle";
	private static final String ALERT_HEADER = "LibraryManagement_UpdateAlertHeader";
	private final ProgressIndicator progressIndicator;
	private final Broadcaster broadcaster;

	public UpdateLibraryEvaluator(ProgressIndicator progressIdct, Broadcaster primaryBroadcaster) {
		progressIndicator = progressIdct;
		broadcaster = primaryBroadcaster;
	}

	@Override
	public void handle(ActionEvent event) {
		Optional<ButtonType> alertResult = showConfirmationAlert();
		if (alertResult.isPresent() && alertResult.get() == ButtonType.OK) {
			progressIndicator.setVisible(true);
			LibraryDataManager manager = broadcaster.request(LibraryDataManager.class);
			manager.updateDirectories();
			broadcastChanges(manager);
			showNotification();
			progressIndicator.setVisible(false);
		}
	}

	private Optional<ButtonType> showConfirmationAlert() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		String title = I18N.label(ALERT_TITLE);
		String header = I18N.label(ALERT_HEADER);
		alert.setTitle(title);
		alert.setHeaderText(header);
		return alert.showAndWait();
	}
	
	private void broadcastChanges(LibraryDataManager manager) {
		LibraryContentChangeFlowData flowData = new LibraryContentChangeFlowData(manager.getMainLevelDirectoryList(),
				manager.getAudioList());
		flowData.setInitializationSignal(true);
		broadcaster.broadcastData(flowData);
	}

	private void showNotification() {
		String title = I18N.label(NOTIFICATION_TITLE);
		String message = I18N.label(NOTIFICATION_MESSAGE);
		GUIUtils.alert(title, message, Notification.SUCCESS_ICON);
	}

}

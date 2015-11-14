package net.pwojcik.audio.gui.util;

import eu.hansolo.enzo.notification.Notification.Notifier;
import javafx.scene.image.Image;

public final class GUIUtils {

	public static void alert(String title, String message, Image type) {
		Notifier notifier = Notifier.INSTANCE;
		notifier.setOnHideNotification(event -> {
			notifier.stop();
		});
		notifier.notify(title, message, type);
	}
	
	private GUIUtils() {}
	
}

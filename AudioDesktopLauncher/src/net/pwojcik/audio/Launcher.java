package net.pwojcik.audio;
	
import javafx.application.Application;
import javafx.stage.Stage;
import net.pwojcik.audio.serviceinitializer.DefaultServiceInitializer;
import net.pwojcik.audio.serviceinitializer.ServiceInitializer;

/**
 * Main launcher of application.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class Launcher extends Application {
	
	@Override
	public void start(Stage stage) {
		ServiceInitializer serviceInitializer = new DefaultServiceInitializer(stage);
		serviceInitializer.initialize();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

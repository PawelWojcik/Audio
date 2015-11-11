package net.pwojcik.audio;
	
import javafx.application.Application;
import javafx.stage.Stage;
import net.pwojcik.audio.serviceinitializer.DefaultServiceInitializer;
import net.pwojcik.audio.serviceinitializer.ServiceInitializer;

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

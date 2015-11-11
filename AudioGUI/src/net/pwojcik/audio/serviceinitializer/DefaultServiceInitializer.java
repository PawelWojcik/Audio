package net.pwojcik.audio.serviceinitializer;

import javafx.stage.Stage;
import net.pwojcik.audio.gui.StageInitializer;
import net.pwojcik.audio.module.Module;
import net.pwojcik.audio.module.factory.DefaultModuleFactory;
import net.pwojcik.audio.module.factory.ModuleFactory;
import net.pwojcik.audio.segment.factory.DesktopSegmentFactory;
import net.pwojcik.audio.segment.factory.SegmentFactory;

/**
 * Default implementation of ServiceInitializer (with JavaFX desktop client).
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class DefaultServiceInitializer extends AbstractServiceInitializer {

	private final Stage stage;

	public DefaultServiceInitializer(Stage primaryStage) {
		stage = primaryStage;
	}

	@Override
	protected SegmentFactory produceSegmentFactory() {
		return new DesktopSegmentFactory();
	}

	@Override
	protected ModuleFactory produceModuleFactory() {
		return new DefaultModuleFactory();
	}

	@Override
	protected void run() {
		getModules().forEach(Module::postConstruct);
		StageInitializer stageInitializer = new StageInitializer(getSegments());
		stageInitializer.run(stage);
	}

}

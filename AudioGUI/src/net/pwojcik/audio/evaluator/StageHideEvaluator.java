package net.pwojcik.audio.evaluator;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public final class StageHideEvaluator implements EventHandler<WindowEvent> {

	@Override
	public void handle(WindowEvent event) {
		System.exit(0);
	}

}

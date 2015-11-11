package net.pwojcik.audio.evaluator;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

/**
 * Evaluator run while closing application.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class StageHideEvaluator implements EventHandler<WindowEvent> {

	@Override
	public void handle(WindowEvent event) {
		System.exit(0);
	}

}

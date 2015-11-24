package net.pwojcik.audio.evaluator.control;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import net.pwojcik.audio.logic.MediaPlayerLogic;

/**
 * Evaluator for Rewind Button located in Control Segment.
 * It reacts when mouse is clicked.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class PlayerRewindEvaluator implements EventHandler<MouseEvent> {

	private final MediaPlayerLogic logic;

	public PlayerRewindEvaluator(MediaPlayerLogic playerLogic) {
		logic = playerLogic;
	}

	@Override
	public void handle(MouseEvent event) {
		logic.rewind();
	}

}

package net.pwojcik.audio.evaluator.control;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import net.pwojcik.audio.logic.MediaPlayerLogic;

/**
 * Evaluator for Forward Button located in Control Segment.
 * It reacts when mouse is clicked.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class PlayerForwardEvaluator implements EventHandler<MouseEvent> {

	private final MediaPlayerLogic logic;

	public PlayerForwardEvaluator(MediaPlayerLogic playerLogic) {
		logic = playerLogic;
	}

	@Override
	public void handle(MouseEvent event) {
		logic.forward();
	}

}


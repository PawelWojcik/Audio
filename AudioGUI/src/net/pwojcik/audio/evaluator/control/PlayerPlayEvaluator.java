package net.pwojcik.audio.evaluator.control;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import net.pwojcik.audio.logic.MediaPlayerLogic;
import net.pwojcik.audio.logic.MediaPlayerState;
import net.pwojcik.audio.segment.factory.statedependent.control.ControlSegmentFactoryForControlState;
import net.pwojcik.audio.segment.implementation.ControlSegmentButtonType;

/**
 * Evaluator for Play/Pause Button located in Control Segment.
 * It reacts when mouse is clicked.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class PlayerPlayEvaluator implements EventHandler<MouseEvent> {

	private final ControlSegmentFactoryForControlState controlCanvasFactory;
	private final MediaPlayerLogic logic;

	public PlayerPlayEvaluator(ControlSegmentFactoryForControlState factory, MediaPlayerLogic playerLogic) {
		controlCanvasFactory = factory;
		logic = playerLogic;
	}

	@Override
	public void handle(MouseEvent event) {
		boolean isRunning = logic.getState() == MediaPlayerState.RUNNING;
		if (isRunning) {
			logic.pause();
		} else {
			logic.play();
			controlCanvasFactory.setButtonEnabled(ControlSegmentButtonType.FORWARD, true);
			controlCanvasFactory.setButtonEnabled(ControlSegmentButtonType.REWIND, true);
			controlCanvasFactory.setButtonEnabled(ControlSegmentButtonType.STOP, true);
		}
		controlCanvasFactory.updatePlayButton(!isRunning);
	}
}

package net.pwojcik.audio.evaluator.control;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import net.pwojcik.audio.logic.MediaPlayerLogic;
import net.pwojcik.audio.segment.factory.statedependent.control.ControlSegmentFactoryForControlState;
import net.pwojcik.audio.segment.implementation.ControlSegmentButtonType;

/**
 * Evaluator for Stop Button located in Control Segment.
 * It reacts when mouse is clicked.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class PlayerStopEvaluator implements EventHandler<MouseEvent> {

	private final ControlSegmentFactoryForControlState controlCanvasFactory;
	private final MediaPlayerLogic logic;

	public PlayerStopEvaluator(ControlSegmentFactoryForControlState factory, MediaPlayerLogic playerLogic) {
		controlCanvasFactory = factory;
		logic = playerLogic;
	}

	@Override
	public void handle(MouseEvent event) {
		logic.stop();
		controlCanvasFactory.setButtonEnabled(ControlSegmentButtonType.FORWARD, false);
		controlCanvasFactory.setButtonEnabled(ControlSegmentButtonType.REWIND, false);
		controlCanvasFactory.setButtonEnabled(ControlSegmentButtonType.STOP, false);
		controlCanvasFactory.updatePlayButton(false);
	}

}

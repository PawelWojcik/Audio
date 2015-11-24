package net.pwojcik.audio.evaluator.control;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import net.pwojcik.audio.logic.MediaPlayerLogic;
import net.pwojcik.audio.segment.factory.statedependent.control.ControlSegmentFactoryForControlState;

/**
 * Evaluator for Mute Button located in Control Segment.
 * It reacts when mouse is clicked.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class PlayerMuteEvaluator implements EventHandler<MouseEvent> {

	private final ControlSegmentFactoryForControlState controlCanvasFactory;
	private final MediaPlayerLogic logic;

	public PlayerMuteEvaluator(ControlSegmentFactoryForControlState factory, MediaPlayerLogic playerLogic) {
		controlCanvasFactory = factory;
		logic = playerLogic;
	}

	@Override
	public void handle(MouseEvent event) {
		logic.setMuted(!logic.isMuted());
		controlCanvasFactory.updateMuteButton(logic.isMuted());
	}

}
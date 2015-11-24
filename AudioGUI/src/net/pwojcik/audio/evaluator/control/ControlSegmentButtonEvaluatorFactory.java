package net.pwojcik.audio.evaluator.control;

import javafx.scene.control.Button;
import net.pwojcik.audio.logic.MediaPlayerLogic;
import net.pwojcik.audio.segment.factory.statedependent.control.ControlSegmentFactoryForControlState;
import net.pwojcik.audio.segment.implementation.ControlSegmentButtonType;

/**
 * Factory for action listeners for Buttons located in Control Segment.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class ControlSegmentButtonEvaluatorFactory {

	/**
	 * Creates all necessary listeners for buttons.
	 * @param factory control segment canvas factory
	 * @param logic player logic
	 */
	public static void create(ControlSegmentFactoryForControlState factory, MediaPlayerLogic logic) {
		createPlayButtonEvaluator(factory.getButton(ControlSegmentButtonType.PLAY), factory, logic);
		createStopButtonEvaluator(factory.getButton(ControlSegmentButtonType.STOP), factory, logic);
		createMuteButtonEvaluator(factory.getButton(ControlSegmentButtonType.MUTE), factory, logic);
		createRewindButtonEvaluator(factory.getButton(ControlSegmentButtonType.REWIND), logic);
		createForwardButtonEvaluator(factory.getButton(ControlSegmentButtonType.FORWARD), logic);
	}
	
	private static void createPlayButtonEvaluator(Button button, ControlSegmentFactoryForControlState factory,
			MediaPlayerLogic logic) {
		button.setOnMouseClicked(new PlayerPlayEvaluator(factory, logic));
	}

	private static void createStopButtonEvaluator(Button button, ControlSegmentFactoryForControlState factory,
			MediaPlayerLogic logic) {
		button.setOnMouseClicked(new PlayerStopEvaluator(factory, logic));
	}
	
	private static void createMuteButtonEvaluator(Button button, ControlSegmentFactoryForControlState factory,
			MediaPlayerLogic logic) {
		button.setOnMouseClicked(new PlayerMuteEvaluator(factory, logic));
	}

	private static void createRewindButtonEvaluator(Button button, MediaPlayerLogic logic) {
		button.setOnMouseClicked(new PlayerRewindEvaluator(logic));
	}

	private static void createForwardButtonEvaluator(Button button, MediaPlayerLogic logic) {
		button.setOnMouseClicked(new PlayerForwardEvaluator(logic));
	}
	
}

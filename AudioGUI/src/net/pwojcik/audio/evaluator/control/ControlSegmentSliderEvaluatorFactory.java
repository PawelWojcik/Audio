package net.pwojcik.audio.evaluator.control;

import javafx.scene.control.Slider;
import net.pwojcik.audio.logic.MediaPlayerLogic;

/**
 * Factory for action listeners for Sliders located in Control Segment.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class ControlSegmentSliderEvaluatorFactory {

	/**
	 * Creates all necessary evaluators.
	 * @param volumeSlider volume slider
	 * @param balanceSlider balance slider
	 * @param logic player logic
	 */
	public static void create(Slider volumeSlider, Slider balanceSlider, MediaPlayerLogic logic) {
		volumeSlider.setOnMousePressed(new VolumeSliderMouseEvaluator(logic));
		volumeSlider.valueChangingProperty().addListener(new VolumeSliderChangeEvaluator(volumeSlider, logic));
		balanceSlider.setOnMousePressed(new BalanceSliderMouseEvaluator(logic));
		balanceSlider.valueChangingProperty().addListener(new BalanceSliderChangeEvaluator(balanceSlider, logic));
	}

}

package net.pwojcik.audio.evaluator.control;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;
import net.pwojcik.audio.logic.MediaPlayerLogic;

/**
 * Evaluator for Volume Slider located in Control Segment.
 * It reacts when knob is dragged.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class VolumeSliderChangeEvaluator implements ChangeListener<Boolean> {

	private final Slider slider;
	private final MediaPlayerLogic logic;

	public VolumeSliderChangeEvaluator(Slider target, MediaPlayerLogic playerLogic) {
		slider = target;
		logic = playerLogic;
	}

	@Override
	public void changed(ObservableValue<? extends Boolean> observable, Boolean wasChanging, Boolean isChanging) {
		double volume = slider.getValue();
		logic.setVolume(volume);
	}

}

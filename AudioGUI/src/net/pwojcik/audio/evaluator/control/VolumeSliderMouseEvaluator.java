package net.pwojcik.audio.evaluator.control;

import javafx.event.EventHandler;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import net.pwojcik.audio.logic.MediaPlayerLogic;

/**
 * Evaluator for Volume Slider located in Control Segment.
 * It reacts when mouse is clicked.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class VolumeSliderMouseEvaluator implements EventHandler<MouseEvent> {

	private final MediaPlayerLogic logic;
	
	public VolumeSliderMouseEvaluator(MediaPlayerLogic playerLogic) {
		logic = playerLogic;
	}
	
	@Override
	public void handle(MouseEvent event) {
		Slider slider = (Slider) event.getSource();
		double volume = slider.getValue();
		logic.setVolume(volume);
	}

}

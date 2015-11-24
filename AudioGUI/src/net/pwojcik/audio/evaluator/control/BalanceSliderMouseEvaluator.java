package net.pwojcik.audio.evaluator.control;

import javafx.event.EventHandler;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import net.pwojcik.audio.logic.MediaPlayerLogic;

/**
 * Evaluator for Balance Slider located in Control Segment.
 * It reacts when mouse is clicked.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class BalanceSliderMouseEvaluator implements EventHandler<MouseEvent> {

	private final MediaPlayerLogic logic;
	
	public BalanceSliderMouseEvaluator(MediaPlayerLogic playerLogic) {
		logic = playerLogic;
	}
	
	@Override
	public void handle(MouseEvent event) {
		Slider slider = (Slider) event.getSource();
		double balance = slider.getValue();
		logic.setBalance(balance);
	}

}

package net.pwojcik.audio.evaluator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import net.pwojcik.audio.segment.Segment;

/**
 * Evaluator that's responding for height changes of West Box. West Box is
 * container holding Control Segment and Navigation Segment.
 * 
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class WestBoxHeightEvaluator implements ChangeListener<Number> {

	private final Segment<?> navigationSegment;
	private final Segment<?> controlSegment;

	public WestBoxHeightEvaluator(Segment<?> navigation, Segment<?> control) {
		navigationSegment = navigation;
		controlSegment = control;
	}

	@Override
	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		navigationSegment.getCanvas().setPrefHeight(newValue.intValue() - controlSegment.getCanvas().getPrefHeight());
	}

}

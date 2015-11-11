package net.pwojcik.audio.evaluator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;
import net.pwojcik.audio.segment.Segment;

/**
 * Evaluator that's responding for height changes on Scene Segment.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class SceneSegmentBoxHeightEvaluator implements ChangeListener<Number> {

	private static final int SECOND_ELEMENT = 1;
	private final Segment sceneSegment;

	public SceneSegmentBoxHeightEvaluator(Segment scene) {
		sceneSegment = scene;
	}

	@Override
	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		Pane canvas = (Pane) sceneSegment.getCanvas().getChildren().get(SECOND_ELEMENT);
		canvas.setPrefHeight(newValue.intValue());
	}
}

package net.pwojcik.audio.evaluator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import net.pwojcik.audio.gui.DesktopViewConstants;
import net.pwojcik.audio.segment.Segment;

public final class WestBoxHeightEvaluator implements ChangeListener<Number> {

	private final Segment navigationSegment;

	public WestBoxHeightEvaluator(Segment scene) {
		navigationSegment = scene;
	}

	@Override
	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		navigationSegment.getCanvas().setPrefHeight(newValue.intValue() - DesktopViewConstants.NAVIGATION_BAR_SIZE
				+ DesktopViewConstants.FIRST_PLAYER_HEIGHT);
	}

}

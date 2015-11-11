package net.pwojcik.audio.evaluator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeView;

/**
 * Evaluator that's responding for width changes on Navigation Segment.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class NavigationSegmentBoxHeightEvaluator implements ChangeListener<Number> {

	private final TreeView<?> treeView;

	public NavigationSegmentBoxHeightEvaluator(TreeView<?> tree) {
		treeView = tree;
	}

	@Override
	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		treeView.setPrefHeight(newValue.intValue());
	}

}

package net.pwojcik.audio.evaluator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeView;

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

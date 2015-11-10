package net.pwojcik.audio.evaluator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeItem;
import net.pwojcik.audio.broadcast.Broadcaster;
import net.pwojcik.audio.flowdata.SceneChangeFlowData;
import net.pwojcik.audio.tree.TreeItemValueWrapper;

public final class TreeContentSelectionChangeEvaluator implements ChangeListener<TreeItem<TreeItemValueWrapper>> {

	private Broadcaster broadcaster;
	
	public TreeContentSelectionChangeEvaluator(Broadcaster primaryBroadcaster) {
		broadcaster = primaryBroadcaster;
	}

	@Override
	public void changed(ObservableValue<? extends TreeItem<TreeItemValueWrapper>> observable,
			TreeItem<TreeItemValueWrapper> oldValue, TreeItem<TreeItemValueWrapper> newValue) {
		if (isChangePossible(newValue, oldValue)) {
			TreeItemValueWrapper treeItemWrapper = newValue.getValue();
			String moduleToRun = treeItemWrapper.getCorrespondingModule();
			broadcaster.broadcastData(new SceneChangeFlowData(moduleToRun));
		}
	}

	private boolean isChangePossible(TreeItem<TreeItemValueWrapper> selectedItem,
			TreeItem<TreeItemValueWrapper> oldSelection) {
		return (selectedItem != null && (oldSelection == null || !oldSelection.equals(selectedItem)));
	}
}

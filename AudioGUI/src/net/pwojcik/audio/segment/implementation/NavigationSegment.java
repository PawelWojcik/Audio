package net.pwojcik.audio.segment.implementation;

import java.util.Collection;

import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import net.pwojcik.audio.evaluator.NavigationSegmentBoxHeightEvaluator;
import net.pwojcik.audio.flowdata.FlowData;
import net.pwojcik.audio.gui.DesktopViewConstants;
import net.pwojcik.audio.gui.tree.TreeViewFactory;
import net.pwojcik.audio.module.Module;
import net.pwojcik.audio.segment.AbstractSegment;
import net.pwojcik.audio.segment.SegmentType;
import net.pwojcik.audio.tree.TreeItemValueWrapper;

public final class NavigationSegment extends AbstractSegment {

	public NavigationSegment(Collection<Module> applicationModules) {
		super(applicationModules);
	}

	@Override
	public void handleData(FlowData data) {
		// TODO Auto-generated method stub
	}

	@Override
	public SegmentType getSegmentType() {
		return SegmentType.NAVIGATION;
	}

	@Override
	public Pane produceCanvas() {
		TreeViewFactory factory = new TreeViewFactory(getBroadcaster());
		TreeView<TreeItemValueWrapper> treeView = factory.prepareTreeView(getModules());
		final VBox box = new VBox();
		int width = DesktopViewConstants.NAVIGATION_BAR_SIZE - DesktopViewConstants.NAVIGATION_BAR_SPACING * 2;
		box.setMinWidth(width);
		box.setMaxWidth(width);
		int minHeight = DesktopViewConstants.SCENE_HEIGHT - DesktopViewConstants.FIRST_PLAYER_HEIGHT
				- DesktopViewConstants.NAVIGATION_BAR_SIZE - 20;
		box.setMinHeight(minHeight);
		box.heightProperty().addListener(new NavigationSegmentBoxHeightEvaluator(treeView));
		box.setMaxHeight(Double.MAX_VALUE);
		box.getChildren().add(treeView);
		return box;
	}

}

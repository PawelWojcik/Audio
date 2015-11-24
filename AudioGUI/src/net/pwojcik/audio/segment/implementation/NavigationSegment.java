package net.pwojcik.audio.segment.implementation;

import java.util.Collection;

import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import net.pwojcik.audio.evaluator.NavigationSegmentBoxHeightEvaluator;
import net.pwojcik.audio.gui.DesktopViewConstants;
import net.pwojcik.audio.gui.tree.TreeViewFactory;
import net.pwojcik.audio.module.Module;
import net.pwojcik.audio.segment.AbstractSegment;
import net.pwojcik.audio.segment.SegmentType;
import net.pwojcik.audio.tree.TreeItemValueWrapper;

/**
 * Navigation Segment describes area when main application's navigation is
 * located.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class NavigationSegment extends AbstractSegment<SingleSegmentState> {

	public NavigationSegment(Collection<Module> applicationModules) {
		super(applicationModules);
	}

	@Override
	public SegmentType getSegmentType() {
		return SegmentType.NAVIGATION;
	}

	@Override
	public Pane produceCanvas(SingleSegmentState currentState) {
		TreeViewFactory factory = new TreeViewFactory(getBroadcaster());
		TreeView<TreeItemValueWrapper> treeView = factory.prepareTreeView(getModules());
		final VBox box = new VBox();
		int minHeight = DesktopViewConstants.SCENE_HEIGHT - DesktopViewConstants.SEEK_SEGMENT_HEIGHT
				- DesktopViewConstants.NAVIGATION_BAR_SIZE - 20;
		box.setMinHeight(minHeight);
		box.heightProperty().addListener(new NavigationSegmentBoxHeightEvaluator(treeView));
		box.setMaxHeight(Double.MAX_VALUE);
		box.getChildren().add(treeView);
		return box;
	}
	
	@Override
	protected SingleSegmentState getDefaultState() {
		return SingleSegmentState.DEFAULT;
	}

}

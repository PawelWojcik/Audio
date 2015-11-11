package net.pwojcik.audio.module;

import java.util.Optional;

import javafx.scene.layout.Pane;
import net.pwojcik.audio.tree.TreeDescription;

public final class DesktopCanvasDataContainerImpl implements DesktopCanvasDataContainer {

	private final Pane canvas;
	private final String canvasLabel;
	private final Optional<TreeDescription> treeDescription;
	
	public DesktopCanvasDataContainerImpl(Pane pane, String label) {
		canvas = pane;
		canvasLabel = label;
		treeDescription = Optional.empty();
	}
	
	public DesktopCanvasDataContainerImpl(Pane pane, String label, TreeDescription treeDesc) {
		canvas = pane;
		canvasLabel = label;
		treeDescription = Optional.of(treeDesc);
	}
	
	@Override
	public Optional<TreeDescription> getTreeDescription() {
		return treeDescription;
	}

	@Override
	public Pane getCanvas() {
		return canvas;
	}

	@Override
	public String getCanvasLabel() {
		return canvasLabel;
	}

}

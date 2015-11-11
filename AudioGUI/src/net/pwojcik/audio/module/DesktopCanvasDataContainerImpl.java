package net.pwojcik.audio.module;

import java.util.Optional;

import javafx.scene.layout.Pane;
import net.pwojcik.audio.gui.canvasfactory.CanvasFactory;
import net.pwojcik.audio.tree.TreeDescription;

/**
 * Default implementation of {@linkplain DesktopCanvasDataContainer} interface.
 * @author Pawel Wojcik
 * @version 1.0
 * @param <T> any valid subclass of CanvasFactory
 */
public final class DesktopCanvasDataContainerImpl<T extends CanvasFactory> implements DesktopCanvasDataContainer<T> {

	private final T canvasFactory;
	private final String canvasLabel;
	private final Optional<TreeDescription> treeDescription;
	
	public DesktopCanvasDataContainerImpl(T factory, String label) {
		canvasFactory = factory;
		canvasLabel = label;
		treeDescription = Optional.empty();
	}
	
	public DesktopCanvasDataContainerImpl(T factory, String label, TreeDescription treeDesc) {
		canvasFactory = factory;
		canvasLabel = label;
		treeDescription = Optional.of(treeDesc);
	}
	
	@Override
	public Optional<TreeDescription> getTreeDescription() {
		return treeDescription;
	}

	@Override
	public Pane getCanvas() {
		return canvasFactory.getCanvas();
	}

	@Override
	public String getCanvasLabel() {
		return canvasLabel;
	}

	@Override
	public T getCanvasFactory() {
		return canvasFactory;
	}

}

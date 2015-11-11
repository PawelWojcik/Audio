package net.pwojcik.audio.gui.canvasfactory;

import javafx.scene.layout.Pane;

/**
 * Abstract representation of CanvasFactory.
 * @author Pawel Wojcik
 * @version 1.0
 */
public abstract class AbstractCanvasFactory implements CanvasFactory {

	private Pane canvas;

	public AbstractCanvasFactory() {
		canvas = produce();
	}
	
	@Override
	public final Pane getCanvas() {
		return canvas;
	}

	protected abstract Pane produce();
	
}

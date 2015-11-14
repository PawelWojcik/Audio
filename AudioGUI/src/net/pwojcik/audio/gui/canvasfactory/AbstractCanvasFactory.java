package net.pwojcik.audio.gui.canvasfactory;

import javafx.scene.layout.Pane;
import net.pwojcik.audio.broadcast.Broadcaster;

/**
 * Abstract representation of CanvasFactory.
 * @author Pawel Wojcik
 * @version 1.0
 */
public abstract class AbstractCanvasFactory implements CanvasFactory {

	private final Broadcaster broadcaster;
	private final Pane canvas;

	public AbstractCanvasFactory(Broadcaster primaryBroadcaster) {
		broadcaster = primaryBroadcaster;
		canvas = produce();
	}
	
	@Override
	public final Pane getCanvas() {
		return canvas;
	}
	
	protected final Broadcaster getBroadcaster() {
		return broadcaster;
	}

	protected abstract Pane produce();
	
}

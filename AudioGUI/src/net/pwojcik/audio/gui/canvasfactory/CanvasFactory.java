package net.pwojcik.audio.gui.canvasfactory;

import javafx.scene.layout.Pane;

/**
 * Interface representing factory for module-specific GUI panels. 
 * @author Pawel Wojcik
 * @version 1.0
 */
public interface CanvasFactory {

	/**
	 * Returns main panel of module.
	 * @return module's main panel
	 */
	Pane getCanvas();
}

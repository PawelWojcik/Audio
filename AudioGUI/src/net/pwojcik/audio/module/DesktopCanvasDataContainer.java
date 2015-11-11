package net.pwojcik.audio.module;

import java.util.Optional;

import javafx.scene.layout.Pane;
import net.pwojcik.audio.gui.canvasfactory.CanvasFactory;
import net.pwojcik.audio.tree.TreeDescription;

/**
 * Extension for CanvasDataContainer suitable for desktop client.
 * @author Pawel Wojcik
 * @version 1.0
 * @param <T> any valid subclass of CanvasFactory
 */
public interface DesktopCanvasDataContainer<T extends CanvasFactory> extends CanvasDataContainer {

	/**
	 * Returns description of tree node representing module. If representation
	 * on tree is not necessary, then empty Optional value should be returned.
	 * 
	 * @return decription of tree node for this module
	 */
	Optional<TreeDescription> getTreeDescription();

	/**
	 * Returns GUI representation for that module.
	 * @return panel for module
	 */
	Pane getCanvas();

	/**
	 * Returns label for module-specific canvas.
	 * @return canvas' label
	 */
	String getCanvasLabel();
	
	/**
	 * Return factory of canvas which holds many specific GUI properties.
	 * @return canvas factory
	 */
	T getCanvasFactory();
}

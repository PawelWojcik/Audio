package net.pwojcik.audio.module;

import java.util.Optional;

import javafx.scene.layout.Pane;
import net.pwojcik.audio.tree.TreeDescription;

public interface DesktopCanvasDataContainer extends CanvasDataContainer {

	/**
	 * Returns description of tree node representing module. If representation
	 * on tree is not necessary, then empty Optional value should be returned.
	 * 
	 * @return decription of tree node for this module
	 */
	public Optional<TreeDescription> getTreeDescription();

	/**
	 * Returns GUI representation for that module.
	 * @return panel for module
	 */
	public Pane getCanvas();

	/**
	 * Returns label for module-specific canvas.
	 * @return canvas' label
	 */
	public String getCanvasLabel();
}

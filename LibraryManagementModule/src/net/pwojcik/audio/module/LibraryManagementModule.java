package net.pwojcik.audio.module;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import net.pwojcik.audio.locale.I18N;
import net.pwojcik.audio.tree.DefaultTreeDescription;
import net.pwojcik.audio.tree.DefaultTreeDescription.TreeDescriptionMode;
import net.pwojcik.audio.tree.TreeDescription;

public final class LibraryManagementModule extends AbstractModule<DesktopCanvasDataContainer> {

	private static final String TREE_LABEL = "LibraryManagement_TreeLabel";
	private static final String CANVAS_LABEL = "LibraryManagement_CanvasLabel";
	private static final String ICON_FILE = "library.png";

	@Override
	protected DesktopCanvasDataContainer produceCanvasDataContainer() {
		String canvasLabel = I18N.label(CANVAS_LABEL);
		Pane canvas = getCanvas();
		String treeLabel = I18N.label(TREE_LABEL);
		TreeDescription description = new DefaultTreeDescription(treeLabel, ICON_FILE, TreeDescriptionMode.WITH_ICON);
		return new DesktopCanvasDataContainerImpl(canvas, canvasLabel, description);
	}

	private Pane getCanvas() {
		HBox box = new HBox();
		box.setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));
		box.getChildren().add(new Text("LIB MODULE"));
		return box;
	}

}

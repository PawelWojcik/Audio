package net.pwojcik.audio.module;

import java.util.Optional;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import net.pwojcik.audio.flowdata.FlowData;
import net.pwojcik.audio.locale.I18N;
import net.pwojcik.audio.tree.DefaultTreeDescription;
import net.pwojcik.audio.tree.DefaultTreeDescription.TreeDescriptionMode;
import net.pwojcik.audio.tree.TreeDescription;

public final class LibraryManagementModule extends AbstractModule {

	private static final String TREE_LABEL = "LibraryManagement_TreeLabel";
	private static final String ICON_FILE = "library.png";
	
	@Override
	public void handleData(FlowData data) {

	}

	@Override
	public Optional<TreeDescription> getTreeDescription() {
		String label = I18N.label(TREE_LABEL);
		TreeDescription description = new DefaultTreeDescription(label, ICON_FILE, TreeDescriptionMode.WITH_ICON);
		return Optional.of(description);
	}

	@Override
	public Pane getCanvas() {
		HBox box = new HBox();
		box.setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));
		box.getChildren().add(new Text("LIB MODULE"));
		return box;
	}


}

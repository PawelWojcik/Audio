package net.pwojcik.audio.module;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import net.pwojcik.audio.broadcast.Broadcaster;
import net.pwojcik.audio.gui.canvasfactory.AbstractCanvasFactory;
import net.pwojcik.audio.gui.util.ImageProvider;
import net.pwojcik.audio.gui.util.table.NowPlayingTagBasedTable;
import net.pwojcik.audio.model.Audio;
import net.pwojcik.audio.model.playlist.Playlist;

/**
 * Main factory for Now Playing Module GUI elements.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class NowPlayingModuleCanvasFactory extends AbstractCanvasFactory {

	private NowPlayingTagBasedTable table;

	public NowPlayingModuleCanvasFactory(Broadcaster primaryBroadcaster) {
		super(primaryBroadcaster);
	}

	@Override
	protected Pane produce() {
		VBox box = new VBox();
		box.setAlignment(Pos.CENTER);
		box.getStyleClass().add("containerWithBorder");
		VBox imageViewContainer = new VBox();
		imageViewContainer.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		ImageView imageView = ImageProvider.getImageView(getClass(), "img.png");
		imageViewContainer.getChildren().add(imageView);

		table = new NowPlayingTagBasedTable(getBroadcaster());
		box.getChildren().addAll(imageViewContainer, table, new Label("xx"));
		return box;
	}

	/**
	 * Updates table of elements with tracks contained in given playlist.
	 * @param playlist set of tracks
	 */
	public void updateTable(Playlist playlist) {
		ObservableList<Audio> tableData = FXCollections.observableArrayList(playlist.getAudioList());
		table.setItems(tableData);
	}

}

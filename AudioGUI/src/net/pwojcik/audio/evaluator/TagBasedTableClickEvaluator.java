package net.pwojcik.audio.evaluator;

import java.util.Collection;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import net.pwojcik.audio.broadcast.Broadcaster;
import net.pwojcik.audio.flowdata.PlayerSignalFlowData;
import net.pwojcik.audio.model.Audio;
import net.pwojcik.audio.model.playlist.PlaylistImpl;
import net.pwojcik.audio.model.tag.AudioTagType;

/**
 * Evaluator for table which columns are based on {@linkplain AudioTagType} values.
 * It reacts when mouse is clicked twice on table row.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class TagBasedTableClickEvaluator implements EventHandler<MouseEvent> {

	private static final int TWO_CLICKS = 2;
	private final Broadcaster broadcaster;
	private final Collection<Audio> selection;

	public TagBasedTableClickEvaluator(Broadcaster primaryBroadcaster, ObservableList<Audio> selectedItems) {
		broadcaster = primaryBroadcaster;
		selection = selectedItems;
	}

	@Override
	public void handle(MouseEvent event) {
		if (!selection.isEmpty() && event.getClickCount() == TWO_CLICKS) {
			PlayerSignalFlowData flowData = new PlayerSignalFlowData(new PlaylistImpl(selection));
			broadcaster.broadcastData(flowData);
		}
	}

}

package net.pwojcik.audio.segment.factory.statedependent;

import javafx.scene.control.Button;
import net.pwojcik.audio.gui.util.ImageProvider;

/**
 * Abstract representation of Segment factory dependent on specific SegmentState.
 * @author Pawel Wojcik
 * @version 1.0
 */
public abstract class StateDependentSegmentFactory {
	
	protected Button createDetailsButton() {
		Button detailsButton = new Button();
		detailsButton.setGraphic(ImageProvider.getImageView(StateDependentSegmentFactory.class, "switchscreen.png"));
		detailsButton.setDisable(true);
		return detailsButton;
	}
}

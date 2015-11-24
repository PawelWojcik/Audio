package net.pwojcik.audio.segment.factory.statedependent.control;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import net.pwojcik.audio.gui.util.ImageProvider;
import net.pwojcik.audio.segment.factory.statedependent.StateDependentSegmentFactory;

/**
 * Factory for buttons located in Control Segment.
 * @author Pawel Wojcik
 * @version 1.0
 */
final class ControlSegmentButtonFactory {
	
	/**
	 * Creates Shuffle Button.
	 * @return button.
	 */
	public static Button createShuffleButton() {
		Button shuffleButton = new Button();
		shuffleButton.setGraphic(ImageProvider.getImageView(StateDependentSegmentFactory.class, "player/shuffle.png"));
		shuffleButton.setDisable(true);
		return shuffleButton;
	}

	/**
	 * Creates Repeat Button.
	 * @return button.
	 */
	public static Button createRepeatButton() {
		Button repeatButton = new Button();
		repeatButton.setGraphic(ImageProvider.getImageView(StateDependentSegmentFactory.class, "player/repeat.png"));
		repeatButton.setDisable(true);
		return repeatButton;
	}

	/**
	 * Creates Repeat All Button.
	 * @return button.
	 */
	public static Button createRepeatListButton() {
		Button repeatListButton = new Button();
		repeatListButton
				.setGraphic(ImageProvider.getImageView(StateDependentSegmentFactory.class, "player/repeatlist.png"));
		repeatListButton.setDisable(true);
		return repeatListButton;
	}

	/**
	 * Creates Previous Button.
	 * @return button.
	 */
	public static Button createPreviousButton() {
		Button previousButton = new Button();
		previousButton.setGraphic(ImageProvider.getImageView(StateDependentSegmentFactory.class, "player/prev.png"));
		previousButton.setDisable(true);
		return previousButton;
	}

	/**
	 * Creates Rewind Button.
	 * @return button.
	 */
	public static Button createRewindButton() {
		Button rewindButton = new Button();
		rewindButton.setGraphic(ImageProvider.getImageView(StateDependentSegmentFactory.class, "player/rewind.png"));
		rewindButton.setDisable(true);
		return rewindButton;
	}

	/**
	 * Creates Play button.
	 * @param icon initializing icon (pause/play)
	 * @return button
	 */
	public static Button createPlayButton(Node icon) {
		Button playButton = new Button();
		playButton.setGraphic(icon);
		playButton.setDisable(true);
		return playButton;
	}

	/**
	 * Creates Stop Button.
	 * @return button.
	 */
	public static Button createStopButton() {
		Button stopButton = new Button();
		stopButton.setGraphic(ImageProvider.getImageView(StateDependentSegmentFactory.class, "player/stop.png"));
		stopButton.setDisable(true);
		return stopButton;
	}

	/**
	 * Creates Forward Button.
	 * @return button.
	 */
	public static Button createForwardButton() {
		Button forwardButton = new Button();
		forwardButton.setGraphic(ImageProvider.getImageView(StateDependentSegmentFactory.class, "player/forward.png"));
		forwardButton.setDisable(true);
		return forwardButton;
	}

	/**
	 * Creates Next Button.
	 * @return button.
	 */
	public static Button createNextButton() {
		Button nextButton = new Button();
		nextButton.setGraphic(ImageProvider.getImageView(StateDependentSegmentFactory.class, "player/next.png"));
		nextButton.setDisable(true);
		return nextButton;
	}

	/**
	 * Creates Mute Button.
	 * @param icon initializing icon (mute/loud)
	 * @return button
	 */
	public static Button createVolumeButton(Node icon) {
		Button volumeButton = new Button();
		volumeButton.setGraphic(icon);
		volumeButton.setTooltip(new Tooltip("WYCISZ"));
		return volumeButton;
	}
	
}

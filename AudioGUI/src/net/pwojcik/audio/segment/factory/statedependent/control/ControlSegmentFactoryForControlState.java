package net.pwojcik.audio.segment.factory.statedependent.control;

import java.util.HashMap;
import java.util.Map;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import net.pwojcik.audio.evaluator.control.ControlSegmentButtonEvaluatorFactory;
import net.pwojcik.audio.evaluator.control.ControlSegmentSliderEvaluatorFactory;
import net.pwojcik.audio.gui.DesktopViewConstants;
import net.pwojcik.audio.gui.util.ImageProvider;
import net.pwojcik.audio.logic.MediaPlayerLogic;
import net.pwojcik.audio.segment.factory.statedependent.MuteButtonState;
import net.pwojcik.audio.segment.factory.statedependent.PlayButtonState;
import net.pwojcik.audio.segment.factory.statedependent.StateDependentSegmentFactory;
import net.pwojcik.audio.segment.implementation.ControlSegmentButtonType;

public final class ControlSegmentFactoryForControlState extends StateDependentSegmentFactory {

	private final Map<ControlSegmentButtonType, Button> buttonMap;
	private final Map<PlayButtonState, Node> playButtonIconMap;
	private final Map<MuteButtonState, Node> muteButtonIconMap;
	private final MediaPlayerLogic logic;

	public ControlSegmentFactoryForControlState(MediaPlayerLogic playerLogic) {
		logic = playerLogic;
		buttonMap = new HashMap<>();
		playButtonIconMap = new HashMap<>();
		muteButtonIconMap = new HashMap<>();
	}

	public void setButtonEnabled(ControlSegmentButtonType type, boolean enable) {
		buttonMap.get(type).setDisable(!enable);
	}

	public void updatePlayButton(boolean showPauseIcon) {
		PlayButtonState buttonState = showPauseIcon ? PlayButtonState.PAUSE : PlayButtonState.PLAY;
		buttonMap.get(ControlSegmentButtonType.PLAY).setGraphic(playButtonIconMap.get(buttonState));
	}

	public Button getButton(ControlSegmentButtonType type) {
		return buttonMap.get(type);
	}

	public void updateMuteButton(boolean muted) {
		MuteButtonState buttonState = muted ? MuteButtonState.MUTED : MuteButtonState.ACTIVE;
		buttonMap.get(ControlSegmentButtonType.MUTE).setGraphic(muteButtonIconMap.get(buttonState));
	}

	public Pane create() {
		HBox container = new HBox();
		container.getStyleClass().add("containerWithBorder");
		container.setAlignment(Pos.CENTER);

		GridPane box = initializeBox();
		initializeAlternativeButtonIcons();
		Node artworkContainer = createArtworkContainer();
		Button detailsButton = createDetailsButton();
		Button shuffleButton = ControlSegmentButtonFactory.createShuffleButton();
		Button repeatButton = ControlSegmentButtonFactory.createRepeatButton();
		Button repeatListButton = ControlSegmentButtonFactory.createRepeatListButton();
		Button volumeButton = ControlSegmentButtonFactory
				.createVolumeButton(muteButtonIconMap.get(MuteButtonState.ACTIVE));
		Slider volumeSlider = ControlSegmentSliderFactory.createVolumeSlider();
		Label balanceButton = createBalanceLabel();
		Slider balanceSlider = ControlSegmentSliderFactory.createBalanceSlider();
		Button previousButton = ControlSegmentButtonFactory.createPreviousButton();
		Button rewindButton = ControlSegmentButtonFactory.createRewindButton();
		Button playButton = ControlSegmentButtonFactory.createPlayButton(playButtonIconMap.get(PlayButtonState.PLAY));
		Button stopButton = ControlSegmentButtonFactory.createStopButton();
		Button forwardButton = ControlSegmentButtonFactory.createForwardButton();
		Button nextButton = ControlSegmentButtonFactory.createNextButton();

		buttonMap.put(ControlSegmentButtonType.SHUFFLE, shuffleButton);
		buttonMap.put(ControlSegmentButtonType.REPEAT, repeatButton);
		buttonMap.put(ControlSegmentButtonType.REPEAT_ALL, repeatListButton);
		buttonMap.put(ControlSegmentButtonType.PREVIOUS, previousButton);
		buttonMap.put(ControlSegmentButtonType.REWIND, rewindButton);
		buttonMap.put(ControlSegmentButtonType.STOP, stopButton);
		buttonMap.put(ControlSegmentButtonType.FORWARD, forwardButton);
		buttonMap.put(ControlSegmentButtonType.NEXT, nextButton);
		buttonMap.put(ControlSegmentButtonType.MUTE, volumeButton);
		buttonMap.put(ControlSegmentButtonType.PLAY, playButton);

		ControlSegmentButtonEvaluatorFactory.create(this, logic);
		ControlSegmentSliderEvaluatorFactory.create(volumeSlider, balanceSlider, logic);

		box.add(artworkContainer, 0, 0, 3, 3);
		box.add(detailsButton, 5, 1);
		box.add(shuffleButton, 3, 2);
		box.add(repeatButton, 4, 2);
		box.add(repeatListButton, 5, 2);
		box.add(volumeButton, 0, 3);
		box.add(volumeSlider, 1, 3, 5, 1);
		box.add(balanceButton, 0, 4);
		box.add(balanceSlider, 1, 4, 5, 1);
		box.add(previousButton, 0, 5);
		box.add(rewindButton, 1, 5);
		box.add(playButton, 2, 5);
		box.add(stopButton, 3, 5);
		box.add(forwardButton, 4, 5);
		box.add(nextButton, 5, 5);

		container.getChildren().add(box);
		return container;
	}

	@Override
	protected Button createDetailsButton() {
		Button button = super.createDetailsButton();
		buttonMap.put(ControlSegmentButtonType.DETAILS, button);
		return button;
	}

	private void initializeAlternativeButtonIcons() {
		playButtonIconMap.put(PlayButtonState.PLAY,
				ImageProvider.getImageView(StateDependentSegmentFactory.class, "player/play.png"));
		playButtonIconMap.put(PlayButtonState.PAUSE,
				ImageProvider.getImageView(StateDependentSegmentFactory.class, "player/pause.png"));

		muteButtonIconMap.put(MuteButtonState.ACTIVE,
				ImageProvider.getImageView(StateDependentSegmentFactory.class, "player/loud.png"));
		muteButtonIconMap.put(MuteButtonState.MUTED,
				ImageProvider.getImageView(StateDependentSegmentFactory.class, "player/mute.png"));
	}

	private Label createBalanceLabel() {
		Label balanceLabel = new Label();
		balanceLabel.setGraphic(ImageProvider.getImageView(StateDependentSegmentFactory.class, "player/balance.png"));
		balanceLabel.setTooltip(new Tooltip("?"));
		return balanceLabel;
	}

	private static Node createArtworkContainer() {
		ImageView artwork = ImageProvider.getImageView(StateDependentSegmentFactory.class, "blankcover.png");
		artwork.setFitWidth(DesktopViewConstants.ARTWORK_WIDTH_SMALL);
		artwork.setFitHeight(DesktopViewConstants.ARTWORK_HEIGHT_SMALL);
		HBox pictureRegion = new HBox(artwork);
		pictureRegion.setPadding(new Insets(0, 0, 0, 5));
		return pictureRegion;
	}

	private static GridPane initializeBox() {
		GridPane box = new GridPane();
		box.setPadding(new Insets(5, 0, 5, 0));
		for (int i = 0; i < 6; i++) {
			ColumnConstraints column = new ColumnConstraints(25);
			column.setHalignment(HPos.CENTER);
			box.getColumnConstraints().add(column);
		}
		box.setAlignment(Pos.TOP_CENTER);
		box.setHgap(6);
		box.setVgap(5);
		return box;
	}

}

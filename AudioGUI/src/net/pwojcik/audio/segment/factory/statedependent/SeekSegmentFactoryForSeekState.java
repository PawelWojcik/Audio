package net.pwojcik.audio.segment.factory.statedependent;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import net.pwojcik.audio.gui.DesktopViewConstants;

/**
 * Factory for Seek Segment desired for 'SEEK' state.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class SeekSegmentFactoryForSeekState extends StateDependentSegmentFactory{

	/**
	 * Create full canvas.
	 * @return GUI representation
	 */
	public Pane create() {
		Button detailsButton = createDetailsButton();
		HBox sliderContainer = prepareSliderContainer();

		BorderPane borderPane = new BorderPane();
		borderPane.getStyleClass().add("containerWithBorder");
		borderPane.setPadding(new Insets(DesktopViewConstants.SPACING));
		borderPane.setLeft(detailsButton);
		borderPane.setRight(sliderContainer);
		BorderPane.setAlignment(detailsButton, Pos.CENTER_LEFT);

		return borderPane;
	}
	
	private static HBox prepareSliderContainer() {
		HBox box = new HBox();
		box.setAlignment(Pos.CENTER);
		box.setSpacing(DesktopViewConstants.SPACING);
		box.setMinHeight(DesktopViewConstants.SEEK_SEGMENT_HEIGHT);
		box.setMaxHeight(DesktopViewConstants.SEEK_SEGMENT_HEIGHT);

		Label startLabel = new Label("0:00");
		Slider positionSlider = new Slider();
		positionSlider = new Slider();
		positionSlider.setMinWidth(DesktopViewConstants.SCENE_WIDTH - 120);
		positionSlider.setMin(0);
		Label endLabel = new Label("155:55:55");
		box.getChildren().addAll(startLabel, positionSlider, endLabel);
		return box;
	}
	
}

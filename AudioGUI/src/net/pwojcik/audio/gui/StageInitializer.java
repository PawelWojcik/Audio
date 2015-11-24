package net.pwojcik.audio.gui;

import java.util.Collection;
import java.util.function.Predicate;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.pwojcik.audio.evaluator.SceneSegmentBoxHeightEvaluator;
import net.pwojcik.audio.evaluator.SceneSegmentBoxWidthEvaluator;
import net.pwojcik.audio.evaluator.StageHideEvaluator;
import net.pwojcik.audio.evaluator.WestBoxHeightEvaluator;
import net.pwojcik.audio.locale.I18N;
import net.pwojcik.audio.segment.Segment;
import net.pwojcik.audio.segment.SegmentType;
import net.pwojcik.audio.segment.implementation.ControlSegment;
import net.pwojcik.audio.segment.implementation.NavigationSegment;
import net.pwojcik.audio.segment.implementation.SceneSegment;
import net.pwojcik.audio.segment.implementation.SeekSegment;

/**
 * Class responsible for initializations of application's window.
 * 
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class StageInitializer {

	private static final String APPLICATION_TITLE = "Application_Title";
	private static final String CSS_FILENAME = "application.css";

	private ControlSegment controlSegment;
	private NavigationSegment navigationSegment;
	private SeekSegment seekSegment;
	private SceneSegment sceneSegment;

	public StageInitializer(Collection<Segment<?>> segments) {
		controlSegment = fetchSegment(segments, SegmentType.CONTROL);
		navigationSegment = fetchSegment(segments, SegmentType.NAVIGATION);
		seekSegment = fetchSegment(segments, SegmentType.SEEK);
		sceneSegment = fetchSegment(segments, SegmentType.SCENE);
	}

	/**
	 * Performs necessary initializations
	 * 
	 * @param stage
	 *            primary stage of application
	 */
	public void run(Stage stage) {
		stage.setTitle(I18N.label(APPLICATION_TITLE));
		BorderPane borderPane = new BorderPane();
		VBox westBox = new VBox();
		
		controlSegment.setContainer(westBox);
		seekSegment.setContainer(borderPane);
		
		borderPane.getStyleClass().add("paddingTaker");
		borderPane.setTop(seekSegment.getCanvas());

		westBox.setSpacing(DesktopViewConstants.SPACING);
		westBox.setMinWidth(DesktopViewConstants.NAVIGATION_BAR_SIZE);
		westBox.setMaxWidth(DesktopViewConstants.NAVIGATION_BAR_SIZE);
		westBox.setMaxHeight(Double.MAX_VALUE);
		westBox.heightProperty().addListener(new WestBoxHeightEvaluator(navigationSegment, controlSegment));
		westBox.getChildren().add(controlSegment.getCanvas());
		westBox.getChildren().add(navigationSegment.getCanvas());
		
		HBox mainBox = new HBox();
		mainBox.setSpacing(DesktopViewConstants.SPACING);
		mainBox.widthProperty().addListener(new SceneSegmentBoxWidthEvaluator(sceneSegment));
		mainBox.heightProperty().addListener(new SceneSegmentBoxHeightEvaluator(sceneSegment));
		mainBox.getChildren().add(westBox);
		mainBox.getChildren().add(sceneSegment.getCanvas());
		borderPane.setCenter(mainBox);
		
		BorderPane.setMargin(seekSegment.getCanvas(), new Insets(0, 0, DesktopViewConstants.SPACING, 0));
		Scene scene = new Scene(borderPane, DesktopViewConstants.SCENE_WIDTH, DesktopViewConstants.SCENE_HEIGHT);
		scene.getStylesheets().add(getClass().getResource(CSS_FILENAME).toExternalForm());
		stage.setScene(scene);
		stage.show();
		stage.setOnHiding(new StageHideEvaluator());

		double offsetHeight = stage.getHeight() - scene.getHeight();
		stage.setMinHeight(offsetHeight + DesktopViewConstants.SCENE_HEIGHT);
		double offsetWidth = stage.getWidth() - scene.getWidth();
		stage.setMinWidth(offsetWidth + DesktopViewConstants.SCENE_WIDTH);
	}

	@SuppressWarnings("unchecked")
	private <S extends Segment<?>> S fetchSegment(Collection<Segment<?>> segments, SegmentType type) {
		return (S) segments.stream().filter(setFilter(type)).findFirst().get();
	}

	private Predicate<? super Segment<?>> setFilter(SegmentType type) {
		return segment -> segment.getSegmentType() == type;
	}

}

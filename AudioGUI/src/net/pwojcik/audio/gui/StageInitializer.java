package net.pwojcik.audio.gui;

import java.util.Collection;
import java.util.function.Predicate;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.pwojcik.audio.Launcher;
import net.pwojcik.audio.evaluator.SceneSegmentBoxHeightEvaluator;
import net.pwojcik.audio.evaluator.SceneSegmentBoxWidthEvaluator;
import net.pwojcik.audio.evaluator.StageHideEvaluator;
import net.pwojcik.audio.evaluator.WestBoxHeightEvaluator;
import net.pwojcik.audio.segment.Segment;
import net.pwojcik.audio.segment.SegmentType;

public final class StageInitializer {

	private static final String CSS_FILENAME = "application.css";

	private Segment controlSegment;
	private Segment navigationSegment;
	private Segment seekSegment;
	private Segment sceneSegment;

	public StageInitializer(Collection<Segment> segments) {
		controlSegment = fetchSegment(segments, SegmentType.CONTROL);
		navigationSegment = fetchSegment(segments, SegmentType.NAVIGATION);
		seekSegment = fetchSegment(segments, SegmentType.SEEK);
		sceneSegment = fetchSegment(segments, SegmentType.SCENE);
	}

	public void run(Stage stage) {
		// TODO title in properties file
		stage.setTitle("XXX");
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(seekSegment.getCanvas());
		HBox mainBox = new HBox();
		mainBox.widthProperty().addListener(new SceneSegmentBoxWidthEvaluator(sceneSegment));
		mainBox.heightProperty().addListener(new SceneSegmentBoxHeightEvaluator(sceneSegment));

		VBox westBox = new VBox();
		westBox.setPadding(new Insets(DesktopViewConstants.NAVIGATION_BAR_SPACING));
		westBox.setSpacing(DesktopViewConstants.NAVIGATION_BAR_SPACING);
		westBox.setMinWidth(DesktopViewConstants.NAVIGATION_BAR_SIZE);
		westBox.setMaxWidth(DesktopViewConstants.NAVIGATION_BAR_SIZE);
		westBox.setMinHeight(DesktopViewConstants.SCENE_HEIGHT - DesktopViewConstants.FIRST_PLAYER_HEIGHT);
		westBox.setMaxHeight(Double.MAX_VALUE);
		westBox.heightProperty().addListener(new WestBoxHeightEvaluator(navigationSegment));
		westBox.getChildren().add(controlSegment.getCanvas());
		westBox.getChildren().add(navigationSegment.getCanvas());

		mainBox.getChildren().add(westBox);
		mainBox.getChildren().add(sceneSegment.getCanvas());
		borderPane.setCenter(mainBox);

		Scene scene = new Scene(borderPane, DesktopViewConstants.SCENE_WIDTH, DesktopViewConstants.SCENE_HEIGHT);
		scene.getStylesheets().add(Launcher.class.getResource(CSS_FILENAME).toExternalForm());
		stage.setScene(scene);
		stage.show();
		stage.setOnHiding(new StageHideEvaluator());

		double offsetHeight = stage.getHeight() - scene.getHeight();
		stage.setMinHeight(offsetHeight + DesktopViewConstants.SCENE_HEIGHT);
		double offsetWidth = stage.getWidth() - scene.getWidth();
		stage.setMinWidth(offsetWidth + DesktopViewConstants.SCENE_WIDTH);
	}

	private Segment fetchSegment(Collection<Segment> segments, SegmentType type) {
		return segments.stream().filter(setFilter(type)).findFirst().get();
	}

	private Predicate<? super Segment> setFilter(SegmentType type) {
		return segment -> segment.getSegmentType() == type;
	}

}

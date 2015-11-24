package net.pwojcik.audio.segment.implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.google.common.collect.Lists;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import net.pwojcik.audio.exception.ModulesConfigurationException;
import net.pwojcik.audio.flowdata.FlowData;
import net.pwojcik.audio.flowdata.FlowHandler;
import net.pwojcik.audio.flowdata.SceneChangeFlowData;
import net.pwojcik.audio.flowhandler.SceneChangeFlowHandler;
import net.pwojcik.audio.gui.DesktopViewConstants;
import net.pwojcik.audio.module.DesktopCanvasDataContainer;
import net.pwojcik.audio.module.Module;
import net.pwojcik.audio.segment.AbstractSegment;
import net.pwojcik.audio.segment.SegmentType;

/**
 * Scene Segment is main area of application where content area is shared
 * between modules.
 * 
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class SceneSegment extends AbstractSegment<SingleSegmentState> {

	private static final String NO_DEFAULT_MODULE = "None of supported modules is decared as default module.";
	private final Module defaultModule;
	private Stage stage;
	private VBox box;
	private Label canvasTitle;

	public SceneSegment(Collection<Module> applicationModules, Stage primaryStage) {
		super(applicationModules);
		defaultModule = fetchDefaultModule();
		stage = primaryStage;
	}

	@Override
	public Collection<String> getObservedFlowTypes() {
		Collection<String> observedFlowTypes = new ArrayList<>();
		observedFlowTypes.add(SceneChangeFlowData.class.getName().toString());
		return observedFlowTypes;
	}

	@Override
	public FlowHandler handleData(FlowData data) {
		if (data.isInstanceOf(SceneChangeFlowData.class)) {
			FlowHandler handler = new SceneChangeFlowHandler(getModules(), box, canvasTitle);
			return handler;
		}
		return super.handleData(data);
	}

	@Override
	public SegmentType getSegmentType() {
		return SegmentType.SCENE;
	}

	@Override
	public Collection<String> getProvidedResources() {
		return Lists.newArrayList(Stage.class.getName());
	}

	@Override
	@SuppressWarnings("unchecked")
	public <P> Optional<P> provide(Class<P> classRepresentation) {
		return (Optional<P>) Optional.of(stage);
	}

	@Override
	public Pane produceCanvas(SingleSegmentState currentState) {
		box = new VBox();
		box.setSpacing(DesktopViewConstants.SPACING);
		box.setAlignment(Pos.CENTER);
		
		DesktopCanvasDataContainer<?> canvasDataContainer = (DesktopCanvasDataContainer<?>) defaultModule
				.getCanvasDataContainer();
		HBox canvasTitleContainer = prepareCanvasTitleContainer(canvasDataContainer.getCanvasLabel());
		// TODO specify properties of font bitch
		box.getChildren().add(canvasTitleContainer);
		box.getChildren().add(canvasDataContainer.getCanvas());
		return box;
	}

	@Override
	protected SingleSegmentState getDefaultState() {
		return SingleSegmentState.DEFAULT;
	}

	private HBox prepareCanvasTitleContainer(String label) {
		canvasTitle = new Label(label);
		canvasTitle.setFont(Font.font(26));
		HBox canvasTitleContainer = new HBox();
		canvasTitleContainer.getChildren().add(canvasTitle);
		ObservableList<String> cssClasses = canvasTitleContainer.getStyleClass();
		cssClasses.add("containerWithBorder");
		cssClasses.add("paddingTaker");
		return canvasTitleContainer;
	}

	private Module fetchDefaultModule() {
		Optional<Module> result = getModules().stream().filter(module -> module.isApplicationDefaultModule())
				.findFirst();
		if (!result.isPresent()) {
			throw new ModulesConfigurationException(NO_DEFAULT_MODULE);
		}
		return result.get();
	}
}

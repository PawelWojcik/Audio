package net.pwojcik.audio.segment.implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.google.common.collect.Lists;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.pwojcik.audio.flowdata.FlowData;
import net.pwojcik.audio.flowdata.FlowHandler;
import net.pwojcik.audio.flowdata.SceneChangeFlowData;
import net.pwojcik.audio.flowhandler.SceneChangeFlowHandler;
import net.pwojcik.audio.module.Module;
import net.pwojcik.audio.segment.AbstractSegment;
import net.pwojcik.audio.segment.SegmentType;

/**
 * Scene Segment is main area of application where content area is shared
 * between modules.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class SceneSegment extends AbstractSegment {

	private Stage stage;
	private VBox box;
	private Label canvasTitle;

	public SceneSegment(Collection<Module> applicationModules, Stage primaryStage) {
		super(applicationModules);
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
	
	@SuppressWarnings("unchecked")
	@Override
	public <P> Optional<P> provide(Class<P> classRepresentation) {
		return (Optional<P>) Optional.of(stage);
	}
	
	@Override
	public Pane produceCanvas() {
		box = new VBox();
		box.setAlignment(Pos.CENTER);
		canvasTitle = new Label("");
		// TODO sepcify properties of font bitch
		// TODO add more topMargin to canvasTitle
		box.getChildren().add(canvasTitle);
		box.getChildren().add(new HBox());
		return box;
	}
}

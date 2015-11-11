package net.pwojcik.audio.segment.implementation;

import java.util.ArrayList;
import java.util.Collection;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import net.pwojcik.audio.flowdata.FlowData;
import net.pwojcik.audio.flowdata.FlowHandler;
import net.pwojcik.audio.flowdata.SceneChangeFlowData;
import net.pwojcik.audio.flowhandler.SceneChangeFlowHandler;
import net.pwojcik.audio.module.Module;
import net.pwojcik.audio.segment.AbstractSegment;
import net.pwojcik.audio.segment.SegmentType;

public final class SceneSegment extends AbstractSegment {

	private VBox box;
	private Text canvasTitle;

	public SceneSegment(Collection<Module> applicationModules) {
		super(applicationModules);
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
	public Pane produceCanvas() {
		box = new VBox();
		canvasTitle = new Text("");
		box.setBackground(new Background(new BackgroundFill(Color.DARKORANGE, null, null)));
		box.getChildren().add(canvasTitle);
		box.getChildren().add(new HBox());
		return box;
	}
}

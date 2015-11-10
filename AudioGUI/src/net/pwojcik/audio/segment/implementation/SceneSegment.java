package net.pwojcik.audio.segment.implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import net.pwojcik.audio.flowdata.FlowData;
import net.pwojcik.audio.flowdata.SceneChangeFlowData;
import net.pwojcik.audio.gui.DesktopViewConstants;
import net.pwojcik.audio.module.Module;
import net.pwojcik.audio.segment.AbstractSegment;
import net.pwojcik.audio.segment.SegmentType;

public final class SceneSegment extends AbstractSegment {

	private HBox box;

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
	public void handleData(FlowData data) {
		if (data.isInstanceOf(SceneChangeFlowData.class)) {
			// TODO move handleDAta to diffrerent class
			SceneChangeFlowData sceneChangeData = (SceneChangeFlowData) data;
			String moduleType = sceneChangeData.getModuleType();
			Optional<Module> coupledModule = getModules().stream().filter(areModuleTypesEqual(moduleType)).findFirst();
			if (!coupledModule.isPresent()) {
				// TODO
				// throw exception that module is not present but shoudl
			}
			Module module = coupledModule.get();
			Pane moduleCanvas = module.getCanvas();
			moduleCanvas.setPrefWidth(box.widthProperty().get());
			moduleCanvas.setPrefHeight(box.heightProperty().get() - DesktopViewConstants.NAVIGATION_BAR_SPACING- DesktopViewConstants.FIRST_PLAYER_HEIGHT*5);
			box.getChildren().clear();
			box.getChildren().add(moduleCanvas);
			System.out.println("ZMIANA SCENY");
		}
	}

	@Override
	public SegmentType getSegmentType() {
		return SegmentType.SCENE;
	}

	@Override
	public Pane produceCanvas() {
		box = new HBox();
		box.setBackground(new Background(new BackgroundFill(Color.DARKORANGE, null, null)));
		box.getChildren().add(new HBox());
		return box;
	}

	private Predicate<? super Module> areModuleTypesEqual(String moduleType) {
		return module -> module.getType().equals(moduleType);
	}

}

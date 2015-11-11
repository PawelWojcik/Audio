package net.pwojcik.audio.flowhandler;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import net.pwojcik.audio.exception.CanvasDataContainerException;
import net.pwojcik.audio.flowdata.FlowData;
import net.pwojcik.audio.flowdata.FlowHandler;
import net.pwojcik.audio.flowdata.SceneChangeFlowData;
import net.pwojcik.audio.gui.ModuleNotFoundException;
import net.pwojcik.audio.module.CanvasDataContainer;
import net.pwojcik.audio.module.DesktopCanvasDataContainer;
import net.pwojcik.audio.module.Module;

/**
 * 
 * FlowHandler implementation that is responsible for changing scene in application.
 * As scene changing we consider changing module to that one, which provides it's own GUI panel.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class SceneChangeFlowHandler implements FlowHandler {

	private static final int SECOND_ELEMENT_INDEX = 1;
	private final Collection<Module> allModules;
	private final Pane mainBox;
	private final Label canvasTitle;
	
	public SceneChangeFlowHandler(Collection<Module> modules, Pane box, Label title) {
		allModules = modules;
		mainBox = box;
		canvasTitle = title;
	}

	@Override
	public void handle(FlowData flowData) {
		SceneChangeFlowData data = (SceneChangeFlowData) flowData;
		String moduleType = data.getModuleType();
		Optional<Module> coupledModule = allModules.stream().filter(areModuleTypesEqual(moduleType)).findFirst();
		if (!coupledModule.isPresent()) {
			throw new ModuleNotFoundException(moduleType);
		}
		Module module = coupledModule.get();
		CanvasDataContainer canvasDataContainer = module.getCanvasDataContainer();
		if(!(canvasDataContainer instanceof DesktopCanvasDataContainer)) {
			throw new CanvasDataContainerException();
		}
		
		DesktopCanvasDataContainer<?> desktopDataContainer = (DesktopCanvasDataContainer<?>) canvasDataContainer;
		canvasTitle.setText(desktopDataContainer.getCanvasLabel());
		canvasTitle.setFont(Font.font(26));
		Pane moduleCanvas = desktopDataContainer.getCanvas();
		moduleCanvas.setPrefWidth(mainBox.widthProperty().get());
		moduleCanvas.setPrefHeight(mainBox.heightProperty().get());
		mainBox.getChildren().remove(SECOND_ELEMENT_INDEX);
		mainBox.getChildren().add(moduleCanvas);
	}
	
	private Predicate<? super Module> areModuleTypesEqual(String moduleType) {
		return module -> module.getType().equals(moduleType);
	}

}

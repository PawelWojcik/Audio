package net.pwojcik.audio.segment.implementation;

import java.util.ArrayList;
import java.util.Collection;

import javafx.scene.layout.Pane;
import net.pwojcik.audio.flowdata.ControlSegmentButtonsEnableFlowData;
import net.pwojcik.audio.flowdata.FlowData;
import net.pwojcik.audio.flowdata.FlowHandler;
import net.pwojcik.audio.flowdata.PlayerSignalFlowData;
import net.pwojcik.audio.flowhandler.ControlSegmentButtonsEnableHandler;
import net.pwojcik.audio.flowhandler.PlayerSignalFlowHandler;
import net.pwojcik.audio.logic.MediaPlayerLogic;
import net.pwojcik.audio.module.Module;
import net.pwojcik.audio.segment.AbstractSegment;
import net.pwojcik.audio.segment.MultiCanvasSegment;
import net.pwojcik.audio.segment.SegmentType;
import net.pwojcik.audio.segment.factory.statedependent.control.ControlSegmentFactoryForControlState;

/**
 * Control Segment is located where all player's control buttons are present.
 * 
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class ControlSegment extends AbstractSegment<ControlSegmentState>
		implements MultiCanvasSegment<ControlSegmentState> {

	private static final int FIRST_ELEMENT = 0;
	private Pane container;
	private ControlSegmentFactoryForControlState controlStateFactory;
	private MediaPlayerLogic logic;
	
	public ControlSegment(Collection<Module> applicationModules) {
		super(applicationModules);
		logic = new MediaPlayerLogic();
	}

	@Override
	public FlowHandler handleData(FlowData data) {
		if(data.isInstanceOf(PlayerSignalFlowData.class)) {
			return new PlayerSignalFlowHandler(logic, controlStateFactory);
		} else if(data.isInstanceOf(ControlSegmentButtonsEnableFlowData.class)) {
			return new ControlSegmentButtonsEnableHandler(controlStateFactory);
		}
		return super.handleData(data);
	}
	
	@Override
	public SegmentType getSegmentType() {
		return SegmentType.CONTROL;
	}
	
	/**
	 * Sets container in which Control Segment is located.
	 * @param box parent container
	 */
	public void setContainer(Pane box) {
		container = box;
	}

	@Override
	public void changeCanvas(ControlSegmentState newState) {
		setState(newState);
		container.getChildren().remove(FIRST_ELEMENT);
		container.getChildren().add(FIRST_ELEMENT, getCanvas());
	}

	@Override
	public Pane produceCanvas(ControlSegmentState currentState) {
		if(currentState==ControlSegmentState.CONTROLS) {
			controlStateFactory = new ControlSegmentFactoryForControlState(logic);
			return controlStateFactory.create();
		}
		throw new UnsupportedOperationException();
	}

	@Override
	protected ControlSegmentState getDefaultState() {
		return ControlSegmentState.CONTROLS;
	}
	
	@Override
	public Collection<String> getObservedFlowTypes() {
		Collection<String> observedFlowTypes = new ArrayList<>();
		observedFlowTypes.add(PlayerSignalFlowData.class.getName().toString());
		observedFlowTypes.add(ControlSegmentButtonsEnableFlowData.class.getName().toString());
		return observedFlowTypes;
	}

}

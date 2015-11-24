package net.pwojcik.audio.flowhandler;

import net.pwojcik.audio.flowdata.ControlSegmentButtonsEnableFlowData;
import net.pwojcik.audio.flowdata.FlowData;
import net.pwojcik.audio.flowdata.FlowHandler;
import net.pwojcik.audio.segment.factory.statedependent.control.ControlSegmentFactoryForControlState;

/**
 * Handler responsible for enabling and disabling buttons located in Control Segment.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class ControlSegmentButtonsEnableHandler implements FlowHandler {

	private final ControlSegmentFactoryForControlState controlStateFactory;
	
	public ControlSegmentButtonsEnableHandler(ControlSegmentFactoryForControlState controlCanvasFactory) {
		controlStateFactory = controlCanvasFactory;
	}
	
	@Override
	public void handle(FlowData data) {
		ControlSegmentButtonsEnableFlowData flowData = (ControlSegmentButtonsEnableFlowData) data;
		controlStateFactory.setButtonEnabled(flowData.getButtonType(), flowData.isEnabled());
	}

}

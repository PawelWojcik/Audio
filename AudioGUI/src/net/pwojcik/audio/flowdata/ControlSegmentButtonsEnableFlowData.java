package net.pwojcik.audio.flowdata;

import net.pwojcik.audio.segment.implementation.ControlSegmentButtonType;

/**
 * Flow data containing change of 'enabled' property of button located in Control Segment.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class ControlSegmentButtonsEnableFlowData extends AbstractFlowData {

	private final ControlSegmentButtonType buttonType;
	private final boolean enabled;
	
	public ControlSegmentButtonsEnableFlowData(ControlSegmentButtonType type, boolean enable) {
		buttonType = type;
		enabled = enable;
	}
	
	/**
	 * Returns type of target button.
	 * @return
	 */
	public ControlSegmentButtonType getButtonType() {
		return buttonType;
	}
	
	/**
	 * Returns whether button should be enabled.
	 * @return
	 */
	public boolean isEnabled() {
		return enabled;
	}
}

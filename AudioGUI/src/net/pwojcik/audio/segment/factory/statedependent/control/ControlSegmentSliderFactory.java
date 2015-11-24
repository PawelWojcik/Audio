package net.pwojcik.audio.segment.factory.statedependent.control;

import javafx.scene.control.Slider;

/**
 * Factory for Sliders located in Control Segment.
 * @author Pawel Wojcik
 * @version 1.0
 */
final class ControlSegmentSliderFactory {

	/**
	 * Creates Volume Slider.
	 * @return slider
	 */
	static Slider createVolumeSlider() {
		Slider volumeSlider = new Slider(0, 1, 1);
		volumeSlider.setShowTickMarks(true);
		volumeSlider.setSnapToTicks(true);
		volumeSlider.setSnapToPixel(true);
		volumeSlider.setMajorTickUnit(0.5f);
		volumeSlider.setMinorTickCount(20);
		return volumeSlider;
	}

	/**
	 * Creates Balance Slider.
	 * @return slider
	 */
	static Slider createBalanceSlider() {
		Slider balanceSlider = new Slider(-1, 1, 0);
		balanceSlider.setShowTickMarks(true);
		balanceSlider.setSnapToTicks(true);
		balanceSlider.setSnapToPixel(true);
		balanceSlider.setMajorTickUnit(1);
		balanceSlider.setMinorTickCount(10);
		return balanceSlider;
	}
}

package net.pwojcik.audio.segment;

public interface MultiCanvasSegment<S extends SegmentState> {

	void changeCanvas(S newState);
}

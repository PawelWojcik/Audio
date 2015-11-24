package net.pwojcik.audio.segment.implementation;

import java.util.Collection;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import net.pwojcik.audio.module.Module;
import net.pwojcik.audio.segment.AbstractSegment;
import net.pwojcik.audio.segment.MultiCanvasSegment;
import net.pwojcik.audio.segment.SegmentType;
import net.pwojcik.audio.segment.factory.statedependent.SeekSegmentFactoryForSeekState;

/**
 * Seek Segment represents area where currently played track's moment can be
 * changed.
 * 
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class SeekSegment extends AbstractSegment<SeekSegmentState>
		implements MultiCanvasSegment<SeekSegmentState> {

	private BorderPane container;

	public SeekSegment(Collection<Module> applicationModules) {
		super(applicationModules);
	}

	/**
	 * Sets container in which SeekSegment is located.
	 * @param borderPane parent container
	 */
	public void setContainer(BorderPane borderPane) {
		container = borderPane;
	}

	@Override
	public SegmentType getSegmentType() {
		return SegmentType.SEEK;
	}

	@Override
	public Pane produceCanvas(SeekSegmentState currentState) {
		if (currentState == SeekSegmentState.SEEK) {
			SeekSegmentFactoryForSeekState seekStateFactory = new SeekSegmentFactoryForSeekState();
			return seekStateFactory.create();
		}
		throw new UnsupportedOperationException();
	}

	@Override
	public void changeCanvas(SeekSegmentState newState) {
		setState(newState);
		container.setTop(getCanvas());
	}

	@Override
	protected SeekSegmentState getDefaultState() {
		return SeekSegmentState.SEEK;
	}
	
}

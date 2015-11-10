package net.pwojcik.audio.segment;

import java.util.Collection;
import java.util.Collections;

import javafx.scene.layout.Pane;
import net.pwojcik.audio.broadcast.AbstractBroadcastParticipant;
import net.pwojcik.audio.module.Module;

/**
 * Abstract representation of  {@link net.pwojcik.audio.segment.Segment}.
 * @author Pawel Wojcik
 * @version 1.0
 */
public abstract class AbstractSegment extends AbstractBroadcastParticipant implements Segment {
	
	private final Collection<Module> modules;
	private Pane canvas;
	
	public AbstractSegment(Collection<Module> applicationModules) {
		modules = applicationModules;
	}
	
	@Override
	public Collection<String> getObservedFlowTypes() {
		return Collections.emptyList();
	}

	@Override
	public Pane getCanvas() {
		if (canvas == null) {
			canvas = produceCanvas();
		}
		return canvas;
	}
	
	protected final Collection<Module> getModules() {
		return modules;
	}

	protected abstract Pane produceCanvas();
	
}

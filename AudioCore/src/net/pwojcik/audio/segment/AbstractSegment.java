package net.pwojcik.audio.segment;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javafx.scene.layout.Pane;
import net.pwojcik.audio.broadcast.AbstractBroadcastParticipant;
import net.pwojcik.audio.module.Module;

/**
 * Abstract representation of {@link net.pwojcik.audio.segment.Segment}.
 * 
 * @author Pawel Wojcik
 * @version 1.0
 */
public abstract class AbstractSegment<S extends SegmentState> extends AbstractBroadcastParticipant
		implements Segment<S> {

	private final Collection<Module> modules;
	private final Map<S, Pane> canvasMap;
	private S state;

	public AbstractSegment(Collection<Module> applicationModules) {
		modules = applicationModules;
		canvasMap = new HashMap<>();
		state = getDefaultState();
	}

	@Override
	public Collection<String> getObservedFlowTypes() {
		return Collections.emptyList();
	}

	@Override
	public Pane getCanvas() {
		if (!canvasMap.containsKey(state)) {
			canvasMap.put(state, produceCanvas(state));
		}
		return canvasMap.get(state);
	}

	@Override
	public Collection<String> getProvidedResources() {
		return Collections.emptyList();
	}

	@Override
	public <P> Optional<P> provide(Class<P> classRepresentation) {
		return Optional.empty();
	}

	@Override
	public S getState() {
		return state;
	}

	@Override
	public void setState(S newState) {
		state = newState;
	}

	protected final Collection<Module> getModules() {
		return modules;
	}

	protected abstract Pane produceCanvas(S byState);

	protected abstract S getDefaultState();
}

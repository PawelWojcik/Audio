package net.pwojcik.audio.segment.factory;

import java.util.Collection;

import net.pwojcik.audio.module.Module;
import net.pwojcik.audio.segment.Segment;

/**
 * Abstract factory for {@link net.pwojcik.audio.segment.Segment} objects.
 * @author Pawel Wojcik
 * @version 1.0
 */
public abstract class AbstractSegmentFactory implements SegmentFactory {

	private Collection<Segment<?>> segments;

	@Override
	public final Collection<Segment<?>> create(Collection<Module> applicationModules) {
		if (segments == null) {
			segments = produceSegments(applicationModules);
		}
		return segments;
	}

	protected abstract Collection<Segment<?>> produceSegments(Collection<Module> applicationModules);

}

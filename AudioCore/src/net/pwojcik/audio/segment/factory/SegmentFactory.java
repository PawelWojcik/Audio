package net.pwojcik.audio.segment.factory;

import java.util.Collection;

import net.pwojcik.audio.module.Module;
import net.pwojcik.audio.segment.Segment;
import net.pwojcik.audio.segment.SegmentState;

/**
 * Factory of  {@link net.pwojcik.audio.segment.Segment} objects. 
 * @author Pawel Wojcik
 * @version 1.0
 */
public interface SegmentFactory {

	/**
	 * Creates collection of Segments available in application.
	 * @param applicationModules modules available in application
	 * @return collection segments
	 */
	Collection<Segment<? extends SegmentState>> create(Collection<Module> applicationModules);
}

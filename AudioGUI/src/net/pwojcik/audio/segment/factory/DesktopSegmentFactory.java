package net.pwojcik.audio.segment.factory;

import java.util.ArrayList;
import java.util.Collection;

import net.pwojcik.audio.module.Module;
import net.pwojcik.audio.segment.Segment;
import net.pwojcik.audio.segment.factory.AbstractSegmentFactory;
import net.pwojcik.audio.segment.implementation.ControlSegment;
import net.pwojcik.audio.segment.implementation.NavigationSegment;
import net.pwojcik.audio.segment.implementation.SceneSegment;
import net.pwojcik.audio.segment.implementation.SeekSegment;

public final class DesktopSegmentFactory extends AbstractSegmentFactory {

	@Override
	protected Collection<Segment> produceSegments(Collection<Module> applicationModules) {
		Collection<Segment> segments = new ArrayList<>();
		segments.add(new ControlSegment(applicationModules));
		segments.add(new NavigationSegment(applicationModules));
		segments.add(new SeekSegment(applicationModules));
		segments.add(new SceneSegment(applicationModules));
		return segments;
	}

}

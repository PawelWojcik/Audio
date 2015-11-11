package net.pwojcik.audio.serviceinitializer;

import java.util.ArrayList;
import java.util.Collection;

import net.pwojcik.audio.broadcast.BroadcastParticipant;
import net.pwojcik.audio.broadcast.Broadcaster;
import net.pwojcik.audio.module.Module;
import net.pwojcik.audio.module.factory.ModuleFactory;
import net.pwojcik.audio.segment.Segment;
import net.pwojcik.audio.segment.factory.SegmentFactory;

/**
 * Abstract representation of
 * {@link net.pwojcik.audio.serviceinitializer.ServiceInitializer}.
 * 
 * @author Pawel Wojcik
 * @version 1.0
 */
public abstract class AbstractServiceInitializer implements ServiceInitializer {

	private Collection<Segment> segments;
	private Collection<Module> modules;

	@Override
	public final void initialize() {
		modules = createModules();
		segments = createSegments(modules);
		Broadcaster broadcaster = new Broadcaster();
		Collection<BroadcastParticipant> broadcastParticipants = mergeBroadcastParticipants();
		broadcaster.registerParticipants(broadcastParticipants);
		run();
	}

	protected final Collection<Segment> getSegments() {
		return segments;
	}
	
	protected final Collection<Module> getModules() {
		return modules;
	}

	private Collection<Segment> createSegments(Collection<Module> applicationModules) {
		SegmentFactory factory = produceSegmentFactory();
		return factory.create(applicationModules);
	}

	private Collection<Module> createModules() {
		ModuleFactory factory = produceModuleFactory();
		return factory.create();
	}

	private Collection<BroadcastParticipant> mergeBroadcastParticipants() {
		Collection<BroadcastParticipant> participants = new ArrayList<>(segments);
		participants.addAll(modules);
		return participants;
	}

	/**
	 * Runs additional initialization dependent from chosen application's GUI
	 * type.
	 */
	protected abstract void run();

	protected abstract SegmentFactory produceSegmentFactory();

	protected abstract ModuleFactory produceModuleFactory();
}

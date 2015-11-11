package net.pwojcik.audio.segment.implementation;

import java.util.Collection;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import net.pwojcik.audio.module.Module;
import net.pwojcik.audio.segment.AbstractSegment;
import net.pwojcik.audio.segment.SegmentType;

/**
 * Control Segment is located where all player's control buttons are present.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class ControlSegment extends AbstractSegment {

	public ControlSegment(Collection<Module> applicationModules) {
		super(applicationModules);
	}

	@Override
	public SegmentType getSegmentType() {
		return SegmentType.CONTROL;
	}

	@Override
	public Pane produceCanvas() {
		HBox box = new HBox();
		box.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
		box.getChildren().add(new Text("CONTROL SEGMENT"));
		return box;
	}

}

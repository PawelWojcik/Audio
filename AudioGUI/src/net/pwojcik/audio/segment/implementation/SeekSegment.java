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
 * Seek Segment represents area where currently played track's moment can be
 * changed.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class SeekSegment extends AbstractSegment {

	public SeekSegment(Collection<Module> applicationModules) {
		super(applicationModules);
	}

	@Override
	public SegmentType getSegmentType() {
		return SegmentType.SEEK;
	}

	@Override
	public Pane produceCanvas() {
		HBox box = new HBox();
		box.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
		box.getChildren().add(new Text("SEEK SEGMENT"));
		return box;
	}

}

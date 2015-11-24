package net.pwojcik.audio.flowhandler;

import net.pwojcik.audio.flowdata.FlowData;
import net.pwojcik.audio.flowdata.FlowHandler;
import net.pwojcik.audio.flowdata.PlayerSignalFlowData;
import net.pwojcik.audio.logic.MediaPlayerLogic;
import net.pwojcik.audio.model.playlist.Playlist;
import net.pwojcik.audio.segment.factory.statedependent.control.ControlSegmentFactoryForControlState;
import net.pwojcik.audio.segment.implementation.ControlSegmentButtonType;

/**
 * FlowHandler implementation that is responsible for playing current playlist.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class PlayerSignalFlowHandler implements FlowHandler {

	private final MediaPlayerLogic logic;
	private final ControlSegmentFactoryForControlState controlCanvasFactory;

	public PlayerSignalFlowHandler(MediaPlayerLogic mainLogic,
			ControlSegmentFactoryForControlState controlStateFactory) {
		logic = mainLogic;
		controlCanvasFactory = controlStateFactory;
	}

	@Override
	public void handle(FlowData data) {
		PlayerSignalFlowData signalData = (PlayerSignalFlowData) data;
		Playlist playlist = signalData.getSignal();
		logic.changePlaylist(signalData.getSignal());
		
		setButtonsEnabled(playlist);
		updatePlayButton();
	}

	private void updatePlayButton() {
		controlCanvasFactory.updatePlayButton(true);
	}

	private void setButtonsEnabled(Playlist playlist) {
		boolean emptySignal = playlist.getAudioList().isEmpty();
		controlCanvasFactory.setButtonEnabled(ControlSegmentButtonType.DETAILS, !emptySignal);
		controlCanvasFactory.setButtonEnabled(ControlSegmentButtonType.FORWARD, !emptySignal);
		controlCanvasFactory.setButtonEnabled(ControlSegmentButtonType.MUTE, !emptySignal);
		controlCanvasFactory.setButtonEnabled(ControlSegmentButtonType.NEXT, false);
		controlCanvasFactory.setButtonEnabled(ControlSegmentButtonType.PLAY, !emptySignal);
		controlCanvasFactory.setButtonEnabled(ControlSegmentButtonType.PREVIOUS, false);
		controlCanvasFactory.setButtonEnabled(ControlSegmentButtonType.REPEAT, !emptySignal);
		controlCanvasFactory.setButtonEnabled(ControlSegmentButtonType.REPEAT_ALL, !emptySignal);
		controlCanvasFactory.setButtonEnabled(ControlSegmentButtonType.REWIND, !emptySignal);
		controlCanvasFactory.setButtonEnabled(ControlSegmentButtonType.SHUFFLE, !emptySignal);
		controlCanvasFactory.setButtonEnabled(ControlSegmentButtonType.STOP, !emptySignal);
	}

}

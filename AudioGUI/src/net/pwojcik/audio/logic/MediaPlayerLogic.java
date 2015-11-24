package net.pwojcik.audio.logic;

import java.io.File;
import java.util.Optional;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import net.pwojcik.audio.model.Audio;
import net.pwojcik.audio.model.AudioModel;
import net.pwojcik.audio.model.playlist.Playlist;

/**
 * Logic of primary Media Player.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class MediaPlayerLogic {

	private MediaPlayerState state;
	private Optional<MediaPlayer> optionalMediaPlayer;
	private Playlist playlist;
	private int playlistPointer;

	public MediaPlayerLogic() {
		state = MediaPlayerState.STOPPED;
		optionalMediaPlayer = Optional.empty();
	}

	/**
	 * Sets new playlist in player.
	 * @param signal new playlist
	 */
	public void changePlaylist(Playlist signal) {
		playlist = signal;

		if (optionalMediaPlayer.isPresent() && state == MediaPlayerState.RUNNING) {
			stop();
		}

		Media media = produceMedia();
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		optionalMediaPlayer = Optional.of(mediaPlayer);
		play();
	}

	/**
	 * Stops playing audio.
	 */
	public void stop() {
		optionalMediaPlayer.get().stop();
		state = MediaPlayerState.STOPPED;
	}

	/**
	 * Returns current time of song that's being played.
	 * @return
	 */
	public double getCurrentTime() {
		if (optionalMediaPlayer.isPresent()) {
			return optionalMediaPlayer.get().getCurrentTime().toMillis();
		}
		return 0;
	}

	/**
	 * Forwards 10 seconds of song.
	 */
	public void forward() {
		optionalMediaPlayer.ifPresent(player -> {
			Duration pushedTime = new Duration(getCurrentTime() + AudioModel.FORWARD_REVIND_MOVE_STEP_MILLIS);
			player.seek(pushedTime);
		});
	}

	/**
	 * Rewinds 10 seconds of song.
	 */
	public void rewind() {
		optionalMediaPlayer.ifPresent(player -> {
			Duration pushedTime = new Duration(getCurrentTime() - AudioModel.FORWARD_REVIND_MOVE_STEP_MILLIS);
			player.seek(pushedTime);
		});
	}

	/**
	 * Checks whether player is muted.
	 * @return true if player is muted
	 */
	public boolean isMuted() {
		if (optionalMediaPlayer.isPresent()) {
			return optionalMediaPlayer.get().isMute();
		}
		return false;
	}

	/**
	 * Sets flag determining whether player is muted.
	 * @param muted true if player should be muted
	 */
	public void setMuted(boolean muted) {
		optionalMediaPlayer.ifPresent(player -> {
			player.setMute(muted);
		});
	}

	/**
	 * Returns current state of player.
	 * @return state of player
	 */
	public MediaPlayerState getState() {
		return state;
	}

	private Media produceMedia() {
		Audio audio = playlist.getAudio(playlistPointer);
		String source = new File(audio.getPath()).toURI().toString();
		Media media = new Media(source);
		return media;
	}

	/**
	 * Pauses current playback.
	 */
	public void pause() {
		optionalMediaPlayer.ifPresent(player -> {
			state = MediaPlayerState.PAUSED;
			player.pause();
		});
	}

	/**
	 * Plays/resumes current playback.
	 */
	public void play() {
		optionalMediaPlayer.ifPresent(player -> {
			state = MediaPlayerState.RUNNING;
			player.play();
		});
	}

	/**
	 * Sets volume of player. Values must fit in [0, 1].
	 * @param volume volume value
	 */
	public void setVolume(double volume) {
		optionalMediaPlayer.ifPresent(player -> {
			player.setVolume(volume);
		});
	}

	/**
	 * Sets balance of player. Values must fit in [-1, 1].
	 * @param balance balance value
	 */
	public void setBalance(double balance) {
		optionalMediaPlayer.ifPresent(player -> {
			player.setBalance(balance);
		});
	}

	/**
	 * Jums into given timestamp of the track.
	 * @param value timestamp
	 */
	public void seek(double value) {
		optionalMediaPlayer.ifPresent(player -> {
			Duration duration = new Duration(value);
			player.seek(duration);
		});
	}

}

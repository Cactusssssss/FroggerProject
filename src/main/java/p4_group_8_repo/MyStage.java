package p4_group_8_repo;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * <p>
 * MyStage class contains methods for music selection, music playing and music pausing
 * </p>
 * <p>
 * Usage:</p>
 * <pre><code>MyStage background = new Mystage();</pre></code>
 * 
 * @author Pang CH
 *
 */
public class MyStage extends World{
	private String musicFile = "src/main/java/p4_group_8_repo/Frogger Main Song Theme (loop).mp3";//select track
	private MediaPlayer mediaPlayer;

	public void act(long now) {
	}
	
	private void selectMusic() { //sound file as media from musicFile
		Media sound = new Media(new File(musicFile).toURI().toString());
		this.mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
	}
	
	
	/**
	 * Selects and plays the preset music file
	 */
	public void playMusic() { //start music (game beginning)
		selectMusic();
	    mediaPlayer.play();
	}
	/**
	 * Pauses the music
	 */
	public void pauseMusic() {
		mediaPlayer.pause();
	}
	/**
	 * Resumes the music after it is paused
	 */
	public void resumeMusic() { // resume music that was paused
		mediaPlayer.play();
	}
	/**
	 * Stops the currently playing music
	 */
	public void stopMusic() { //ends music (game ending)
		mediaPlayer.stop();
	}

}

package p4_group_8_repo;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MyStage extends World{
	private String musicFile = "src/p4_group_8_repo/Frogger Main Song Theme (loop).mp3";//select track
	private MediaPlayer mediaPlayer;

	public void act(long now) {
	}
	
	public MyStage(){
	}
	
	private void selectMusic() { //sound file as media from musicFile
		Media sound = new Media(new File(musicFile).toURI().toString());
		this.mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
	}
	
	
	
	public void playMusic() { //start music (game beginning)
		selectMusic();
	    mediaPlayer.play();
	}
	public void pauseMusic() {
		mediaPlayer.pause();
	}
	public void resumeMusic() { // resume music that was paused
		mediaPlayer.play();
	}
	public void stopMusic() { //ends music (game ending)
		mediaPlayer.stop();
	}

}

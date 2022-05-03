package main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Manages the sound files for the game.  Sound files are found in the res folder under sound.
 * 
 * @author Robert Wilson
 *
 */
public class Sound {
	// The sound itself
	Clip clip;
	
	// the path of the sounds to import
	URL soundURL[] = new URL[30];
	
	/**
	 * Populates the sounds for the game and places them in the soundURL list
	 */
	public Sound() {		
		soundURL[0] = getClass().getResource("/sound/BlueBoyAdventure.wav");
		soundURL[1] = getClass().getResource("/sound/coin.wav");
		soundURL[2] = getClass().getResource("/sound/powerup.wav");
		soundURL[3] = getClass().getResource("/sound/unlock.wav");
		soundURL[4] = getClass().getResource("/sound/fanfare.wav");
		soundURL[5] = getClass().getResource("/sound/hitmonster.wav");
		soundURL[6] = getClass().getResource("/sound/receivedamage.wav");
		soundURL[7] = getClass().getResource("/sound/hodvent.wav");
		soundURL[8] = getClass().getResource("/sound/sword_strike1.wav");
		soundURL[9] = getClass().getResource("/sound/levelup.wav");
		soundURL[10] = getClass().getResource("/sound/cursor.wav");
		soundURL[11] = getClass().getResource("/sound/burning.wav");
	}
	
	/**
	 * Loads the files into clip.
	 * 
	 * @param index (int) the index of the path in soundURL
	 */
	public void setFile(int index) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[index]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Start the sound.
	 */
	public void play() {
		clip.start();
	}
	
	/**
	 * Keep the sound playing on a loop.
	 */
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	/**
	 * Stop the sound from playing
	 */
	public void stop() {
		clip.stop();
	}
}

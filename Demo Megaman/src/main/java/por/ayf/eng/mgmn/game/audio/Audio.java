package por.ayf.eng.mgmn.game.audio;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import por.ayf.eng.mgmn.util.Util;

/**
 *  Class will define the use of sounds in the game.
 * 
 *  @author: Ángel Yagüe Flor.
 *  @version: 1.0 - Stable.
 *  @version: 1.1 - Refactor the project.
 */

public class Audio {
	
	/**
	 *  Method that play a sound of the game.
	 * 
	 *  @param sound: clip that store the sound.
	 *  @param name of the sound.
	 */
	
	public static void playSound(Clip sound, String name) {
		try {
			sound = AudioSystem.getClip();
			sound.open(AudioSystem.getAudioInputStream(Audio.class.getResource("/sounds/" + name)));
		} catch (Exception ex) {
			Util.logMessage(Util.LEVEL_ERROR, "Ha ocurrido un error al recuperar un audio.", Audio.class, ex);
		}
		sound.start();
	}
	
	/**
	 *  Method that play the music of the game in loop.
	 * 
	 *  @param music: clip that store the music.
	 *  @param name of the music will sound.
	 *  @param start: frame where's starts the loop.
	 *  @param end: frame where's ends the loop. -1 means the end.
	 */
	
	public static Clip playBackgroundMusic(Clip music, String name, int start, int end) {
		try {
			music = AudioSystem.getClip();
			music.open(AudioSystem.getAudioInputStream(Audio.class.getResource("/music/" + name)));
		} catch (Exception ex) {
			Util.logMessage(Util.LEVEL_ERROR, "Ha ocurrido un error al recuperar un audio.", Audio.class, ex);
		}
		
		music.setLoopPoints(start, end);
		music.start();
		music.loop(Clip.LOOP_CONTINUOUSLY);
		
		return music;
	}
}

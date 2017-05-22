package bwyap.familyfeud.sound;

import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import bwyap.utility.logging.Logger;

/**
 * A class responsible for managing the loading and playing of sounds.
 * This class is implemented as a singleton, use {@code getInstance()} to get the SoundManager instance.
 * @author bwyap
 *
 */
public class SoundManager {
	
	private static SoundManager INSTANCE;
	
	private Map<String, Clip> sounds;
	private boolean muted = false;
	
	/**
	 * Get the current sound manager (singleton)
	 * @return
	 */
	public static SoundManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new SoundManager();
		}
		return INSTANCE;
	}
	
	private SoundManager() {
		sounds = new HashMap<String, Clip>();
	}
	
	/**
	 * Load a sound file into the sound manager
	 * @param name
	 * @param path
	 */
	public void loadClip(String name, String path) {
		Clip in = null;
		
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource(path));
			in = AudioSystem.getClip();
			in.open(audioIn);
			sounds.put(name, in);
		}
		catch (Exception e) {
			e.printStackTrace();
			Logger.err("Error occurred when trying to load audio clip <" + path + ">");
		}
	}
	
	/**
	 * Play the clip with the specified name from the beginning.
	 * If the clip was already playing, it is reset and played from the beginning.
	 * @param name
	 */
	public void playClip(String name) {
		if (muted) return;
		Clip clip = getClip(name);
		if (clip != null) {
			stopClip(name);
			clip.setFramePosition(0);
			clip.start();
		}
	}
	
	/**
	 * Stop the specified clip from playing
	 * @param name
	 */
	public void stopClip(String name) {
		Clip clip = getClip(name);
		if (clip.isRunning()) clip.stop();
	}
	
	/**
	 * Stop all clips from playing
	 */
	public void stopAllClips() {
		for(String clipName : sounds.keySet()) {
			stopClip(clipName);
		}
	}
	
	/**
	 * Adjust the gain of the specified clip by the level in decibels 
	 * @param name
	 * @param level
	 */
	public void setClipVolume(String name, float level) {
		Clip clip = getClip(name);
		
		FloatControl gainControl =  (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(level);
	}
	
	/**
	 * Set whether the sound manager is muted or not
	 * @param mute
	 */
	public void setMuted(boolean muted) {
		this.muted = muted;
		if (true) stopAllClips();
	}
	
	/**
	 * Check if the sound manager is muted
	 * @return
	 */
	public boolean isMuted() {
		return muted;
	}
	
	/**
	 * Get the clip with the specified name
	 * @param name
	 * @return
	 */
	private Clip getClip(String name) {
		return sounds.get(name);
	}
	
}

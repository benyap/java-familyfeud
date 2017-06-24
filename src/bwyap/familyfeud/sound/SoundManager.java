package bwyap.familyfeud.sound;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
	
	/**
	 * Regions for which sound is supported
	 * @author bwyap
	 *
	 */
	public enum SoundRegion {
		US, AU
	}
	
	private SoundRegion currentRegion = SoundRegion.AU;	// Default sound region is AU
	private Map<SoundRegion, Map<String, Clip>> sounds;
	
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
		sounds = new HashMap<SoundRegion, Map<String, Clip>>();
		for (SoundRegion region : SoundRegion.values()) {
			sounds.put(region, new HashMap<String, Clip>());
		}
	}
	
	/**
	 * Get the current sound region the sound manager is set to use
	 * @return
	 */
	public SoundRegion getCurrentRegion() {
		return currentRegion;
	}
	
	/**
	 * Set the sound region that the sound manager should use
	 * @param region
	 */
	public void setSoundRegion(SoundRegion region) {
		this.currentRegion = region;
	}
	
	/**
	 * Load a sound file into the sound manager
	 * @param name
	 * @param region
	 * @param path
	 */
	public void loadClip(String name, SoundRegion region, String path) {
		Clip in = null;
		
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource(path));
			in = AudioSystem.getClip();
			in.open(audioIn);
			sounds.get(region).put(name, in);
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
	 * @param region
	 */
	public void playClip(String name, SoundRegion region) {
		if (muted) return;
		Clip clip = getClip(name, region);
		if (clip != null) {
			stopClip(name);
			clip.setFramePosition(0);
			clip.start();
		}
	}
	
	/**
	 * Play the clip with the specified name from the current region from the beginning.
	 * If the clip was already playing, it is reset and played from the beginning.
	 * @param name
	 */
	public void playClip(String name) {
		playClip(name, currentRegion);
	}
	
	/**
	 * Stop the specified clip from playing
	 * @param name
	 * @param region
	 */
	public void stopClip(String name, SoundRegion region) {
		Clip clip = getClip(name, region);
		if (clip.isRunning()) clip.stop();
	}
	
	/**
	 * Stop the specified clip from playing
	 * @param name
	 */
	public void stopClip(String name) {
		stopClip(name, currentRegion);
	}
	
	/**
	 * Stop all clips from playing
	 */
	public void stopAllClips() {
		for(SoundRegion region : sounds.keySet()) {
			for(String clipName : sounds.get(region).keySet()) {
				stopClip(clipName);
			}
		}
	}
	
	/**
	 * Adjust the gain of the specified clip by the level in decibels 
	 * @param name
	 * @param region
	 * @param level
	 */
	public void setClipVolume(String name, SoundRegion region, float level) {
		Clip clip = getClip(name, region);
		
		FloatControl gainControl =  (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(level);
	}
	
	/**
	 * Adjust the gain of the specified clip by the level in decibels 
	 * @param name
	 * @param level
	 */
	public void setClipVolume(String name, float level) {
		setClipVolume(name, currentRegion, level);
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
	 * @param region
	 * @return
	 */
	private Clip getClip(String name, SoundRegion region) {
		return sounds.get(region).get(name);
	}
	
	/**
	 * Get the set containing all sound clips loaded in the system for the specified region
	 * @param region
	 */
	public Set<String> getClips(SoundRegion region) {
		return sounds.get(region).keySet();
	}
	
	/**
	 * Toggle if the sound manager is muted
	 */
	public void toggleMute() {
		if (isMuted()) setMuted(false);
		else setMuted(true);
	}
	
}

package main.java.Game.Utils;

import java.io.File;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundSystem {

	public static HashMap<String, Clip> sounds = new HashMap<String, Clip>();
	
	public static boolean MUTE = true;
	
	public static String DRINK = "Glasses", BUY = "Buy", FAIL = "Fail", COURSE = "Course", JOB = "Job", HOUSE = "Keys", 
			LIFT = "Lifting", WORK = "Money", RUN = "Run", SLEEP = "Sleep", STUDY = "Study";
	
	
	public static void loadSoundsFromNamedFolder(String name) {
		try {
			File file = new File(Utils.RES_URL + "/" + name);
			String[] sounds = file.list();
			int length = sounds.length;
			for (int i = 0; i < length; i++) {
				try {
					File soundFile = new File(file.getPath() + "/" + sounds[i]);
					Clip soundClip = AudioSystem.getClip();
					AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
					soundClip.open(ais);
					SoundSystem.sounds.put(Utils.removeWAV(sounds[i]), soundClip);
				} catch (Exception e) {
					Logger.error("Could not load sound " + sounds[i]);
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			Logger.error("There was a problem loading sounds");
			e.printStackTrace();
		}
	}
	
	public static void setMute(boolean value) {
		SoundSystem.MUTE = value;
	}
	
	public static void playSound(String sound) {
		try {
			if (!SoundSystem.MUTE) {
				sounds.get(sound).setFramePosition(0);
				sounds.get(sound).loop(0);
			}
		} catch (Exception e) {
			Logger.warn("Could not find that sound to play - you might not have added it to the activity");
		}
	
	}
	
}

package main.java.Game.Utils;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class SoundSystem {

	public static HashMap<String, Clip> sounds = new HashMap<String, Clip>();
	
	public static boolean MUTE = true;
	
	public static List<String> strings = new ArrayList<String>();
	
	public static String DRINK = "Glasses", BUY = "Buy", FAIL = "Fail", COURSE = "Course", JOB = "Job", HOUSE = "Keys", 
			LIFT = "Lifting", WORK = "Money", RUN = "Run", SLEEP = "Sleep", STUDY = "Study";
	
	public SoundSystem() {
		
	}
	
	public void loadSounds() {
		//Fill list
			
		SoundSystem.strings.add(SoundSystem.DRINK);
		SoundSystem.strings.add(SoundSystem.BUY);
		SoundSystem.strings.add(SoundSystem.FAIL);
		SoundSystem.strings.add(SoundSystem.COURSE);
		SoundSystem.strings.add(SoundSystem.JOB);
		SoundSystem.strings.add(SoundSystem.HOUSE);
		SoundSystem.strings.add(SoundSystem.LIFT);
		SoundSystem.strings.add(SoundSystem.WORK);
		SoundSystem.strings.add(SoundSystem.RUN);
		SoundSystem.strings.add(SoundSystem.SLEEP);
		SoundSystem.strings.add(SoundSystem.STUDY);
		
		//Load sounds
		Iterator<String> itr = SoundSystem.strings.iterator();
		
		while (itr.hasNext()) {
		
			String name = itr.next();
			ClassLoader cl = null;
			
			try {
				cl = this.getClass().getClassLoader();
				if (cl.equals(null)) {
					System.out.println("Problem with class loader");
				}
			} catch (Exception e) {
				System.out.println("Problem with class loader");
			}
			
			InputStream is = null;
			try {
				is = cl.getResourceAsStream("main/resources/sounds/" + name + ".wav");
				if (is.equals(null)) {
					System.out.println("Could not load " + name);
				}
			} catch (Exception e) {
				System.out.println("Could not load " + name);
			}
		
			Clip soundClip = null;
			try {
				soundClip = AudioSystem.getClip();
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
				System.out.println("Problem getting clip");
			}
			
			AudioInputStream ais = null;
			
			try {
				ais = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Problem getting audio input stream");
			}
			
			try {
				soundClip.open(ais);
			} catch (Exception e) {
				System.out.println("Problem opening the clip");
			}
			
			try {
				SoundSystem.sounds.put(name, soundClip);
			} catch (Exception e) {
				System.out.println("Problem putting sounds to list");
			}
			
			
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

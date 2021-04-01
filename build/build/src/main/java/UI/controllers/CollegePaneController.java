package main.java.UI.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.java.Game.Activities.Activity;
import main.java.Game.Character.Player;
import main.java.Game.Character.Skills;
import main.java.Game.Utils.Clock;
import main.java.Game.Utils.SoundSystem;
import main.java.Game.Utils.Utils;

public class CollegePaneController {

	@FXML
	public Button readButton, courseButton;
	@FXML
	public AnchorPane collegePane;
	@FXML 
	public Label textbookLabel, courseLabel;
	
	public Activity readActivity;
	public Activity courseActivity;

	public HomepageController hp;
	
	
	public void init(HomepageController hp) {
		this.hp = hp;
		
		// Initialise activities
		readActivity = new Activity("Study", 50, Clock.toTime(0, 30));
		readActivity.addSkill(Skills.INTELLIGENCE, 1);
		Utils.addActivityToLabel(textbookLabel, readActivity);

		courseActivity = new Activity("Take course", 300, Clock.toTime(2, 0));
		courseActivity.addSkill(Skills.INTELLIGENCE, 5);
		Utils.addActivityToLabel(courseLabel, courseActivity);
	}

	public void read() {
		if (Player.canDo(readActivity)) {
			Player.doActivity(readActivity);
			SoundSystem.playSound(SoundSystem.STUDY);
		}
	}

	public void course() {
		if (Player.canDo(courseActivity)) {
			Player.doActivity(courseActivity);
			SoundSystem.playSound(SoundSystem.COURSE);
		}
	}
}

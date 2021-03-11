package UI.controllers;

import Game.Activities.Activity;
import Game.Character.Player;
import Game.Character.Skills;
import Game.Utils.Clock;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class CollegePaneController {

	@FXML
	public Button readButton, courseButton;
	public AnchorPane collegePane;

	public Activity readActivity;
	public Activity courseActivity;

	public HomepageController hp;

	public void init(HomepageController hp) {
		this.hp = hp;

		// Initialise activities
		readActivity = new Activity("Study", 50, 30);
		readActivity.addSkill(Skills.INTELLIGENCE, 1);

		courseActivity = new Activity("Take course", 300, Clock.toTime(2, 0));
		courseActivity.addSkill(Skills.INTELLIGENCE, 5);
	}

	public void read() {
		if (Player.canDo(readActivity)) {
			Player.doActivity(readActivity);
		}
	}

	public void course() {
		if (Player.canDo(courseActivity)) {
			Player.doActivity(courseActivity);
		}
	}
}

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

	public Activity readActivity = new Activity("Study", 50, 30);
	public Activity courseActivity = new Activity("Take course", 300, Clock.toTime(2, 0));

	public HomepageController hp;

	public void init(HomepageController hp) {
		this.hp = hp;
	}

	public void read() {
		if (Player.canDo(readActivity)) {
			Player.doActivity(readActivity);
			Player.skills.addSkillPoint(Skills.INTELLIGENCE, 1); // Include this in activity struct
		}
	}

	public void course() {
		if (Player.canDo(courseActivity)) {
			Player.doActivity(courseActivity);
			Player.skills.addSkillPoint(Skills.INTELLIGENCE, 5); // As above
		}
	}
}

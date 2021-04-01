package main.java.UI.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import main.java.Game.Activities.Activity;
import main.java.Game.Character.Player;
import main.java.Game.Character.Skills;
import main.java.Game.Utils.Clock;
import main.java.Game.Utils.SoundSystem;
import main.java.Game.Utils.Utils;

public class GymPaneController {

	@FXML
	Button runButton;
	@FXML
	Label runLabel, liftLabel;
	@FXML
	Button liftButton;
	@FXML
	Pane gymPane;
	@FXML
	ImageView treadmillImage;

	// Activities
	public Activity runActivity, liftActivity;

	HomepageController homepageController;

	public void init(HomepageController controller) {
		this.homepageController = controller;
		// treadmillImage.fitWidthProperty().bind(homepageController.mainController.main.primaryStage.getProperties().);

		// Init activities
		runActivity = new Activity("Run", 0, Clock.toTime(0, 30), SoundSystem.RUN);
		runActivity.addSkill(Skills.CARDIOVASCULAR, 1);
		
		Utils.addActivityToLabel(runLabel, runActivity);

		liftActivity = new Activity("Lift", 10, Clock.toTime(1, 0), SoundSystem.LIFT);
		liftActivity.addSkill(Skills.STRENGTH, 1);
		Utils.addActivityToLabel(liftLabel, liftActivity);
		
	}

	public void run() {
		Player.doActivity(runActivity);
	}

	public void lift() {
		Player.doActivity(liftActivity);
	}

}

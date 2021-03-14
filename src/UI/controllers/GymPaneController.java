package UI.controllers;

import Game.Activities.Activity;
import Game.Character.Player;
import Game.Character.Skills;
import Game.Utils.Clock;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class GymPaneController {

	@FXML
	Button runButton;
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
		runActivity = new Activity("Run", 0, Clock.toTime(0, 30));
		runActivity.addSkill(Skills.CARDIOVASCULAR, 1);

		liftActivity = new Activity("Lift", 10, Clock.toTime(1, 0));
		liftActivity.addSkill(Skills.STRENGTH, 1);
	}

	public void run() {
		Player.doActivity(runActivity);
	}

	public void lift() {
		Player.doActivity(liftActivity);
	}

}

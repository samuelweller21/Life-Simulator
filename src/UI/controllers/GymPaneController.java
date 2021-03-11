package UI.controllers;

import Game.Character.Player;
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

	HomepageController homepageController;

	public void init(HomepageController controller) {
		this.homepageController = controller;
		// treadmillImage.fitWidthProperty().bind(homepageController.mainController.main.primaryStage.getProperties().);
	}

	public void run() {
		Player.skills.addSkillPoint("Cardiovascular", 1);
		Player.clock.tick(30);
	}

	public void lift() {
		Player.skills.addSkillPoint("Strength", 1);
		Player.clock.tick(45);
	}

}

package UI.controllers;

import Game.Character.Player;
import Game.Utils.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

//This is the controller for the player overview pane

public class HomePaneController {

	// Itself
	@FXML
	public Pane homePane;

	// Controllers refs
	public HomepageController homepageController;

	// Components
	@FXML
	private Label nameLabel, ageLabel, cashLabel;

	public void init(HomepageController homepageController) {
		Logger.initialise("Initializing hompage controller in homepane controller");
		this.homepageController = homepageController;
		nameLabel.textProperty().bind(Player.name);
		ageLabel.textProperty().bind(Player.s_age);
		cashLabel.textProperty().bind(Player.s_cash);
	}

}

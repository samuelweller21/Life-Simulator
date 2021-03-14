package UI.controllers;

import Game.Character.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class HomePaneController {

	@FXML
	public Label houseLabel;
	@FXML
	public Button sleepButton;
	@FXML
	public Pane homePane;

	HomepageController hp;

	public void init(HomepageController hp) {
		this.hp = hp;
	}

	public void sleep() {
		Player.clock.nextMorning();
	}

}

package main.java.UI.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import main.java.Game.Character.Player;

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

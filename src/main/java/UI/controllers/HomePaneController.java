package main.java.UI.controllers;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import main.java.Game.Character.Player;
import main.java.Game.House.House;

public class HomePaneController {

	@FXML
	public Label houseLabel, bedLabel, homeLabel;
	@FXML
	public Button sleepButton;
	@FXML
	public Pane homePane;
	@FXML
	public ImageView homeImage;

	HomepageController hp;

	public void init(HomepageController hp) {
		this.hp = hp;
		homeLabel.setText("House:");
		bedLabel.setText("Bed:");
		Player.buyHouse(new House("Homeless", 0));
		houseLabel.textProperty().bind(Player.houseName);
		GridPane.setHalignment(homeLabel, HPos.RIGHT);
		GridPane.setHalignment(bedLabel, HPos.RIGHT);
	}

	public void sleep() {
		Player.clock.nextMorning();
	}

}

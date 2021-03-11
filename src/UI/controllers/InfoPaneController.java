package UI.controllers;

import Game.Character.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class InfoPaneController {

	@FXML
	public Label dayLabel, timeLabel, cashLabel;
	@FXML
	public HBox infoPaneHBox;

	HomepageController homepageController;

	public void init(HomepageController controller) {
		this.homepageController = controller;
		dayLabel.textProperty().bind(Player.clock.dayProp);
		timeLabel.textProperty().bind(Player.clock.minuteProp);
		cashLabel.textProperty().bind(Player.s_cash);
	}

}

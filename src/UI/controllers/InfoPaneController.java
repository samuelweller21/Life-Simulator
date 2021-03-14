package UI.controllers;

import Game.Character.Player;
import Game.Items.Item;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class InfoPaneController {

	@FXML
	public Label dayLabel, timeLabel, cashLabel;
	@FXML
	public HBox infoPaneHBox;
	@FXML
	public Button tryButton;

	HomepageController homepageController;

	public void init(HomepageController controller) {
		this.homepageController = controller;
		dayLabel.textProperty().bind(Player.clock.dayProp);
		timeLabel.textProperty().bind(Player.clock.minuteProp);
		cashLabel.textProperty().bind(Player.s_cash);
	}
	
	public void doTry() {
//		Item mobileItem = new Item("Mobile Phone", 600, 1);
//		if (!Player.items.remove(mobileItem)) {
//			System.out.println("You don't have that item");
//		}
	}

}

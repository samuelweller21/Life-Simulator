package main.java.UI.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import main.java.Game.Character.Player;
import main.java.Game.Main.Main;
import main.java.Game.Utils.Logger;

public class InfoPaneController {

	@FXML
	public Label dayLabel, timeLabel, cashLabel, yearLabel;
	@FXML
	public HBox infoPaneHBox;
	@FXML
	public Button cheatSkillsButton, cheatMoneyButton;

	HomepageController homepageController;

	public void init(HomepageController controller) {
		this.homepageController = controller;
		dayLabel.textProperty().bind(Player.clock.dayProp);
		timeLabel.textProperty().bind(Player.clock.minuteProp);
		cashLabel.textProperty().bind(Player.s_cash);
		yearLabel.textProperty().bind(Player.clock.yearProp);
		if (!Main.CHEAT) {
			infoPaneHBox.getChildren().remove(cheatSkillsButton);
			infoPaneHBox.getChildren().remove(cheatMoneyButton);
		}
	}
	
	public void doTry() {
//		Item mobileItem = new Item("Mobile Phone", 600, 1);
//		if (!Player.items.remove(mobileItem)) {
//			System.out.println("You don't have that item");
//		}
		Player.skills.addHumour(10);
		Player.skills.addCardiovascular(10);
		Player.skills.addStrength(10);
		Player.skills.addIntelligence(10);
	}
	
	public void cheatSkills() {
		Player.skills.addHumour(10);
		Player.skills.addCardiovascular(10);
		Player.skills.addStrength(10);
		Player.skills.addIntelligence(10);
		Logger.warn("Player is cheating skills");
	}
	
	public void cheatMoney() {
		Player.giveMoney(1000);
		Logger.warn("Player is cheating money");
	}
	
	

}

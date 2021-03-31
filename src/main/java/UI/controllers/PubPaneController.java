package main.java.UI.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import main.java.Game.Activities.Activity;
import main.java.Game.Character.Player;
import main.java.Game.Character.Skills;
import main.java.Game.Utils.Clock;
import main.java.Game.Utils.Utils;

public class PubPaneController {
	@FXML
	public Label drinkLabel;
	@FXML
	public Pane pubPane;
	
	
	public Activity drinkActivity;
	
	public MainController mc;
	
	public void init(MainController mc) {
		this.mc = mc;
		drinkActivity = new Activity("Get drunk", 20, Clock.toTime(2, 0));
		drinkActivity.addSkill(Skills.HUMOUR, 1);
		Utils.addActivityToLabel(drinkLabel, drinkActivity);
	}

	@FXML
	public void drink(ActionEvent event) {
		if (Player.canDo(drinkActivity)) {
			Player.doActivity(drinkActivity);
		}
 	}
}

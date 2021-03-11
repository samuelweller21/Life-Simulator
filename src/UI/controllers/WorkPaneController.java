package UI.controllers;

import Game.Character.Player;
import Game.Utils.Clock;
import Game.Utils.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class WorkPaneController {

	// Components
	@FXML
	Pane workPane;
	@FXML
	Label jobLabel;
	@FXML
	Label payLabel;

	@FXML
	HomepageController homepageController;

	public void init(HomepageController homepageController) {
		Logger.initialise("Initializing hompage controller in workpane controller");
		this.homepageController = homepageController;
	}

	public void work() {
		Player.work(Clock.toTime(1, 0));
	}

	public void updateDetails() {
		jobLabel.setText(Player.getJob().getJobName());
		payLabel.setText(Double.toString(Player.getJob().getPay()));
	}

}

package UI.controllers;

import java.io.IOException;

import Game.Utils.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class HomepageController {

	// Components

	@FXML
	private Label homeLabel, schoolLabel, workLabel, collegeLabel;

	@FXML
	private BorderPane layout;

	// Sub panels

	@FXML
	public PlayerPaneController playerPaneController;
	@FXML
	public WorkPaneController workPaneController;
	@FXML
	public InfoPaneController infoPaneController;
	@FXML
	public GymPaneController gymPaneController;
	@FXML
	public CollegePaneController collegePaneController;
	@FXML
	public HomePaneController homePaneController;
	@FXML
	public BackpackPaneController backpackController;

	// Main controller
	@FXML
	public MainController mainController;

	public void init(MainController mc) {
		this.mainController = mc;
		playerPaneController.init(this);
		infoPaneController.init(this);

		// Initialise workPane
		FXMLLoader work_loader = null, gym_loader = null, college_loader = null, backpack_loader = null;
		try {
			work_loader = new FXMLLoader(getClass().getResource("/UI/view/WorkPane.fxml"));
			work_loader.load();
			gym_loader = new FXMLLoader(getClass().getResource("/UI/view/GymPane.fxml"));
			gym_loader.load();
			college_loader = new FXMLLoader(getClass().getResource("/UI/view/CollegePane.fxml"));
			college_loader.load();
			backpack_loader = new FXMLLoader(getClass().getResource("/UI/view/BackpackPane.fxml"));
			backpack_loader.load();
		} catch (IOException e) {
			e.printStackTrace();
			Logger.error("Failed to load a XML file from homepageController");
		}

		workPaneController = work_loader.getController();
		workPaneController.init(this);
		gymPaneController = gym_loader.getController();
		gymPaneController.init(this);
		collegePaneController = college_loader.getController();
		collegePaneController.init(this);
		backpackController = backpack_loader.getController();
		backpackController.init(this);
	}

	public void showHome() {
		Logger.ui("Setting centre to home in homepage");
		layout.setCenter(homePaneController.homePane);
	}

	public void showWork() {
		Logger.ui("Setting centre to work in homepage");
		workPaneController.updateDetails();
		layout.setCenter(workPaneController.workPane);
	}

	public void showGym() {
		Logger.ui("Setting centre to gym in homepage");
		layout.setCenter(gymPaneController.gymPane);
	}

	public void showCollege() {
		Logger.ui("Setting centre to college in homepage");
		layout.setCenter(collegePaneController.collegePane);
	}

	public void showBackpack() {
		Logger.ui("Setting centre to backpack in homepage");
		layout.setCenter(backpackController.backpackPane);
	}



}

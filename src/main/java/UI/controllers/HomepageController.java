package main.java.UI.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import main.java.Game.Utils.Logger;

public class HomepageController {

	// Components

	@FXML
	private Label homeLabel, schoolLabel, workLabel, collegeLabel, backpackLabel, gymLabel;

	@FXML
	private BorderPane layout;
	@FXML
	public VBox menuBox;

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
	
	//MenuBar
	public UIMenuBar menuBar;

	public void init(MainController mc) {
		this.mainController = mc;
		menuBar = new UIMenuBar();
		menuBar.init(mc);
		menuBox.getChildren().add(0, menuBar);
		
		playerPaneController.init(this);
		infoPaneController.init(this);
		

		// Initialise workPane
		FXMLLoader work_loader = null, gym_loader = null, college_loader = null, backpack_loader = null;
		try {
			work_loader = new FXMLLoader(getClass().getResource("/main/java/UI/view/WorkPane.fxml"));
			work_loader.load();
			gym_loader = new FXMLLoader(getClass().getResource("/main/java/UI/view/GymPane.fxml"));
			gym_loader.load();
			college_loader = new FXMLLoader(getClass().getResource("/main/java/UI/view/CollegePane.fxml"));
			college_loader.load();
			backpack_loader = new FXMLLoader(getClass().getResource("/main/java/UI/view/BackpackPane.fxml"));
			backpack_loader.load();
		} catch (IOException e) {
			e.printStackTrace();
			Logger.error("Failed to load a XML file from homepageController");
		}

		homePaneController.init(this);
		workPaneController = work_loader.getController();
		workPaneController.init(this);
		gymPaneController = gym_loader.getController();
		gymPaneController.init(this);
		collegePaneController = college_loader.getController();
		collegePaneController.init(this);
		backpackController = backpack_loader.getController();
		backpackController.init(this);
		
		//Add all style classes
		homeLabel.getStyleClass().add("HomepageButtons");
		schoolLabel.getStyleClass().add("HomepageButtons");
		gymLabel.getStyleClass().add("HomepageButtons");
		workLabel.getStyleClass().add("HomepageButtons");
		backpackLabel.getStyleClass().add("HomepageButtons");
		homeLabel.getStyleClass().add("HomepageButtonsClicked");
		schoolLabel.getStyleClass().add("HomepageButtonsClicked");
		gymLabel.getStyleClass().add("HomepageButtonsClicked");
		workLabel.getStyleClass().add("HomepageButtonsClicked");
		backpackLabel.getStyleClass().add("HomepageButtonsClicked");
	}

	public void showHome() {
		Logger.ui("Setting centre to home in homepage");
		layout.setCenter(homePaneController.homePane);
		setAllLabelsBack();
		homeLabel.setStyle("-fx-text-fill: #138039");
	}
	
	public void setAllLabelsBack() {
		homeLabel.setStyle("-fx-text-fill: #DC143C");
		schoolLabel.setStyle("-fx-text-fill: #DC143C");
		gymLabel.setStyle("-fx-text-fill: #DC143C");
		workLabel.setStyle("-fx-text-fill: #DC143C");
		backpackLabel.setStyle("-fx-text-fill: #DC143C");
	}

	public void showWork() {
		Logger.ui("Setting centre to work in homepage");
		layout.setCenter(workPaneController.workPane);
		setAllLabelsBack();
		workLabel.setStyle("-fx-text-fill: #138039");
	}

	public void showGym() {
		Logger.ui("Setting centre to gym in homepage");
		layout.setCenter(gymPaneController.gymPane);
		setAllLabelsBack();
		gymLabel.setStyle("-fx-text-fill: #138039");
	}

	public void showCollege() {
		Logger.ui("Setting centre to college in homepage");
		layout.setCenter(collegePaneController.collegePane);
		setAllLabelsBack();
		schoolLabel.setStyle("-fx-text-fill: #138039");
	}

	public void showBackpack() {
		Logger.ui("Setting centre to backpack in homepage");
		layout.setCenter(backpackController.backpackPane);
		setAllLabelsBack();
		backpackLabel.setStyle("-fx-text-fill: #138039");
	}



}

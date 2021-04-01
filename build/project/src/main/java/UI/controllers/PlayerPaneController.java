package main.java.UI.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import main.java.Game.Character.Player;
import main.java.Game.Character.SkillsTable;
import main.java.Game.Utils.Logger;

//This is the controller for the player overview pane

public class PlayerPaneController {

	// Itself
	@FXML
	public AnchorPane playerPane;

	// Controllers refs
	public HomepageController homepageController;

	// Components
	@FXML
	private Label nameLabel, ageLabel, cashLabel;
	@FXML
	public TableView skillsTable;
	
	public TableView<SkillsTable> skillTable;
	public TableColumn skillsColumn, valueColumn;
	@FXML
	public GridPane playerPaneLayout;

	public void init(HomepageController homepageController) {
		Logger.initialise("Initializing playerpane controller in homepane controller");
		this.homepageController = homepageController;
		nameLabel.textProperty().bind(Player.name);
		ageLabel.textProperty().bind(Player.s_age);
		cashLabel.textProperty().bind(Player.s_cash);

		// Init columns
		skillsColumn = new TableColumn("Skills");
		skillsColumn.setSortable(false);
		skillsColumn.setCellValueFactory(new PropertyValueFactory<>("skill"));
		valueColumn = new TableColumn("Value");
		valueColumn.setSortable(false);
		valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
		Player.skills.sort();
		skillsTable.setItems(Player.skills.skillsProp);
		skillsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		skillsTable.getColumns().addAll(skillsColumn, valueColumn);
		skillsTable.setFocusTraversable(false);

	}

}

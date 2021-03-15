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
	public TableView<SkillsTable> skillTable;
	public TableColumn skillsColumn, valueColumn;
	@FXML
	public GridPane playerPaneLayout;

	public void init(HomepageController homepageController) {
		Logger.initialise("Initializing playerpane controller in homepane controller");
		this.homepageController = homepageController;
		nameLabel.textProperty().bind(Player.name);
		cashLabel.textProperty().bind(Player.s_cash);

		// Init columns
		skillTable = new TableView<>();
		skillsColumn = new TableColumn("Skills");
		skillsColumn.setSortable(false);
		skillsColumn.setCellValueFactory(new PropertyValueFactory<>("skill"));
		valueColumn = new TableColumn("Value");
		valueColumn.setSortable(false);
		valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
		skillTable.setItems(Player.skills.skillsProp);
		skillTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		skillTable.getColumns().addAll(skillsColumn, valueColumn);
		skillTable.setFocusTraversable(false);
		playerPaneLayout.setMargin(skillTable, new Insets(5));

		// Add to layout
		playerPaneLayout.add(skillTable, 0, 3, 2, 1);
		playerPaneLayout.autosize();

	}

}

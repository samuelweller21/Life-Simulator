package main.java.UI.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import main.java.Game.Character.Player;
import main.java.Game.Items.Item;
import main.java.Game.Utils.Logger;

public class BackpackPaneController {
	@FXML
	public AnchorPane backpackPane;
	@FXML
	public VBox backpackBox;

	public HomepageController hp;

	public TableView<Item> itemsTable;
	public TableColumn nameColumn, quantityColumn;

	public void init(HomepageController hp) {
		this.hp = hp;
		Logger.info("Backpack init successful");

		// Init items table
		itemsTable = new TableView<>();
		nameColumn = new TableColumn("Item");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		quantityColumn = new TableColumn("Quantity");
		quantityColumn.setCellValueFactory(new PropertyValueFactory("quantity"));
		itemsTable.setItems(Player.items.itemsProp);
		itemsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		nameColumn.setSortable(false);
		quantityColumn.setSortable(false);
		itemsTable.getColumns().addAll(nameColumn, quantityColumn);
		backpackBox.getChildren().add(itemsTable);
	}

}

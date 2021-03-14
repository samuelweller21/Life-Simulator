package UI.controllers;

import Game.Character.Player;
import Game.Items.ItemsTable;
import Game.Utils.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.cell.PropertyValueFactory;

public class BackpackPaneController {
	@FXML
	public AnchorPane backpackPane;

	public HomepageController hp;

	// To Do Item table view ...
	public TableView<ItemsTable> itemsTable;
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
		itemsTable.getColumns().addAll(nameColumn, quantityColumn);
		backpackPane.getChildren().add(itemsTable);
		backpackPane.autosize();
	}

}

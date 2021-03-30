package main.java.UI.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import main.java.Game.Character.Player;
import main.java.Game.Items.Item;
import main.java.Game.Items.Items;
import main.java.Game.Utils.Logger;

public class ShopPaneController {
	@FXML
	public TableView<Item> shopTable;
	@FXML
	public Button buyButton;

	public TableColumn nameColumn, priceColumn;
	
	@FXML
	public Spinner<Integer> itemNumber;
	
	@FXML
	public Pane shopPane;
	
	public MainController mc;
	
	public void init(MainController mc) {
		this.mc = mc;
		
		// Init items table
		nameColumn = new TableColumn("Item");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		priceColumn = new TableColumn("Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory("price"));
		nameColumn.setSortable(false);
		priceColumn.setSortable(false);
		shopTable.setItems(Items.global);
		shopTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		shopTable.getColumns().addAll(nameColumn, priceColumn);
	
		//Init Spinner
		  
	    final int initialValue = 1;
        SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, initialValue);
 
        itemNumber.setValueFactory(valueFactory);
		
	}

	@FXML
	public void buy(ActionEvent event) {
		int number = itemNumber.getValue();
		Item item = shopTable.getSelectionModel().getSelectedItem();
		if (Player.enoughCash(item.getS_Price()*number)) {
			Player.buy(item, number);
		} else {
			if (!Player.mc.cashFlickerer.isRunning()) {
				Player.mc.cashFlickerer.restart();
			} else {
				Logger.warn("Thread is already running, is user spamming?");
			}
		}
	}
	
}

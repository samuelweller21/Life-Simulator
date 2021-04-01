package main.java.UI.controllers;

import java.util.Iterator;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.java.Game.House.House;
import main.java.Game.House.HouseBox;
import main.java.Game.House.Houses;

public class EstateAgentController {
	
	public MainController mc;
	
	@FXML
	public VBox estateAgentBox;
	@FXML
	public Pane estatePane;
	
	public void init(MainController mc) {
		this.mc = mc;
	}

	public void loadHouses() {
		//Add houses
		Iterator<House> itr = Houses.global.iterator();
		while (itr.hasNext()) {
			addHouse(itr.next());
		}
	}
	
	public void addHouse(House house) {
		estateAgentBox.getChildren().add(new HouseBox(house));
	}
	
	
}

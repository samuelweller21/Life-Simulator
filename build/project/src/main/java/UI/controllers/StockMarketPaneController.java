package main.java.UI.controllers;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.java.Game.StockMarket.MarketView;
import main.java.Game.StockMarket.StockMarket;
import main.java.Game.StockMarket.TheMarket;

public class StockMarketPaneController {
	@FXML
	public VBox marketContainer;
	@FXML
	public VBox stockScroller;
	@FXML
	public Pane stockPane;
	
	public MainController mc;
	
	public ArrayList<MarketView> views = new ArrayList<MarketView>();
	
	public void init(MainController mc) {
		this.mc = mc;
		
		Iterator<StockMarket> itr = TheMarket.markets.values().iterator();
		while (itr.hasNext()) {
			StockMarket sm = itr.next();
			MarketView mv = new MarketView(sm);
			views.add(mv);
			stockScroller.getChildren().add(mv);
		}
		
	}
	
	public void updateGraphs() {
		Iterator<MarketView> itr = views.iterator();
		while (itr.hasNext()) {
			itr.next().updateGraphs();
		}

	}
	

}

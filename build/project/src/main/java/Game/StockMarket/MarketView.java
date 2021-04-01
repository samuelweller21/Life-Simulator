package main.java.Game.StockMarket;

import java.util.Collections;
import java.util.List;

import com.google.common.primitives.Doubles;

import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import main.java.Game.Character.Player;
import main.java.Game.Utils.Logger;
import main.java.Game.Utils.SoundSystem;

public class MarketView extends VBox {

	
	public Label graphName, investedLabel, amountLabel;
	
	public Label amountInvestedLabel;
	
	public StockMarket sm;
	
	public Button buy, sell;
	
	public TextField valueField;
	
	public HBox titleGraph;
	
	public LineChart<Number,Number> lineChart;
	public NumberAxis xAxis, yAxis;
	public XYChart.Series series;
	
	public void buy() {
		try {
			double value = Double.parseDouble(valueField.getText());
			if (value <= Player.getCash()) {
				sm.buyValue(value);
				Player.pay(value);
			} else {
				Logger.player("You don't have enough money");
				if (!Player.mc.cashFlickerer.isRunning()) {
					Player.mc.cashFlickerer.restart();
				} else {
					Logger.warn("Thread is already running, is user spamming?");
				}
				SoundSystem.playSound(SoundSystem.FAIL);
			}
		} catch (Exception e) {
			Logger.player("You must type a double into that field");
		}
		
	}
	
	public void sell() {
		try {
			double value = Double.parseDouble(valueField.getText());
			if (sm.getPositionValue() >= value) {
				sm.sellValue(value);
				Player.giveMoney(value);
			} else {
				Logger.player("You don't have enough invested");
				SoundSystem.playSound(SoundSystem.FAIL);
			}
		} catch (Exception e) {
			Logger.player("You must type a double into that field");
		}
	}
	
	public MarketView(StockMarket sm) {
		
		this.sm = sm;
		
		//First set up the vbox
		
		//Add label
		graphName = new Label(sm.getName() + ":");
		//this.getChildren().add(graphName);
		
		//Add Graph
        xAxis = new NumberAxis();
        yAxis = new NumberAxis();
		lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("Last 30 days");
        yAxis.setLabel("Price (£)");
        lineChart.setTitle(sm.getName());
        series = new XYChart.Series();
        series.setName(sm.getName());
        yAxis.setLowerBound(0);
        lineChart.setCreateSymbols(false);
        xAxis.setTickUnit(1);
        xAxis.setAutoRanging(false);
        xAxis.setMinorTickVisible(false);
        xAxis.setLowerBound(0);
        xAxis.setUpperBound(30);
        lineChart.setPrefHeight(200);
        lineChart.setMinHeight(200);
        lineChart.setMaxHeight(200);
        
		this.getChildren().add(lineChart);
		this.updateGraphs();
		
		//Create HBox
		titleGraph = new HBox();
		
		buy = new Button("Buy");
		buy.setOnAction(e -> buy());
		sell = new Button("Sell");
		sell.setOnAction(e -> sell());
		
		valueField = new TextField();
		valueField.setPromptText("e.g. 100 = £100");
		valueField.setPrefWidth(100);
		
		investedLabel = new Label("Amount Invested:");
		amountLabel = new Label("Amount:");
		amountInvestedLabel = new Label();
		amountInvestedLabel.textProperty().bind(sm.s_value);
		
		//Add
		titleGraph.getChildren().add(investedLabel);
		titleGraph.getChildren().add(amountInvestedLabel);
		titleGraph.getChildren().add(amountLabel);
		titleGraph.getChildren().add(valueField);
		titleGraph.getChildren().add(buy);
		titleGraph.getChildren().add(sell);
		
		//Format
		titleGraph.setMargin(buy, new Insets(10));
		titleGraph.setMargin(sell, new Insets(10));
		titleGraph.setMargin(valueField, new Insets(10));
		titleGraph.setMargin(graphName, new Insets(10));
		titleGraph.setMargin(investedLabel, new Insets(10));
		titleGraph.setMargin(amountLabel, new Insets(10));
		titleGraph.setMargin(amountInvestedLabel, new Insets(10));

		titleGraph.fillHeightProperty().set(true);
		titleGraph.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
		
		//Add to VBox
		
		this.fillWidthProperty().set(true);
		this.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
		this.getChildren().add(titleGraph);
		
	}
	
	public void updateGraphs() {
		
		//Update axis
        double[] lastMonth = sm.getLastMonth();
		yAxis.setUpperBound(Doubles.max(lastMonth));
        
        series.getData().removeAll(series.getData());
        
        for (int i = 1; i <= sm.getLastMonth().length; i++) {
        	series.getData().add(new XYChart.Data(i, lastMonth[i-1]));
        }
        
        lineChart.getData().removeAll(lineChart.getData());
        lineChart.getData().add(series);
	}
	
}

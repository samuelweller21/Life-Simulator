package main.java.Game.StockMarket;

import javafx.beans.property.SimpleStringProperty;
import main.java.Game.Utils.Utils;
import net.sourceforge.jdistlib.Normal;
import net.sourceforge.jdistlib.rng.MersenneTwister;


public class StockMarket {

	//Properties
	private String marketName = "Unnamed market";
	private double averageGrowth;
	private double averageVariance;
	private int duplicateAvoider;
	private double[] price;
	private double[] lastMonth;
	private double currentPrice;
	private int currentDay;
	private double position;
	public SimpleStringProperty s_value = new SimpleStringProperty("£ 0.00");
	
	public void nextDay() {
		price[currentDay] = price[currentDay-1] + getNextTick(price[currentDay-1]);
		if (price[currentDay] < 0) {
			price[currentDay] = 10;
		}
		currentPrice = price[currentDay];
		for (int i = 0; i < 30; i++) {
			lastMonth[i] = price[currentDay - 29 + i];
		}
		currentDay++;
		s_value.set("£ " + Utils.formatAsPrice(getPositionValue()));
	}
	
	public double getPrice() {
		return currentPrice;
	}
	
	public double getPositionValue() {
		return position*currentPrice;
	}
	
	private void buyUnits(double units) {
		position = position + units;
		s_value.set("£ " + Utils.formatAsPrice(getPositionValue()));
	}
	
	public void sellValue(double value) {
		buyValue(-1*value);
	}
	
	public void buyValue(double value) {
		buyUnits(value / currentPrice);
	}
	
	public double[] getLastMonth() {
		return lastMonth;
	}
	
	public String getName() {
		return marketName;
	}
	
	public StockMarket(String name, double avgGrowth, double vol) {
		this.marketName = name;
		this.averageGrowth = avgGrowth/100;
		this.averageVariance = avgGrowth*vol;
		duplicateAvoider = (int)System.currentTimeMillis();
		
		//Set up first month
		this.price = new double[365*100 + 30];
		this.lastMonth = new double[30];
		price[0] = 100;
		lastMonth[0] = price[0];
		for (int i = 1; i < 30; i++) {
			double value = price[i-1] + getNextTick(price[i-1]);
			if (value < 0) {
				value = 10;
			}
			price[i] = value;
			lastMonth[i] = value;
		}
		currentPrice = price[29];
		currentDay = 30;
		position = 0;
	}
	
	public double getGrowthTick() {
		duplicateAvoider++;
		return Normal.random(1, averageGrowth, averageVariance, new MersenneTwister(duplicateAvoider))[0]; 
	}
	
	public double getNextTick(double money) {
		duplicateAvoider++;
		return money*Normal.random(1, averageGrowth, averageVariance, new MersenneTwister(duplicateAvoider))[0]; 
	}
	
	
}

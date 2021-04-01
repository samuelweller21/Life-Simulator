package main.java.Game.StockMarket;

import java.util.HashMap;
import java.util.Iterator;

import main.java.Game.Character.Player;
import main.java.Game.Utils.Logger;

public class TheMarket {

	public static HashMap<String, StockMarket> markets = new HashMap<String, StockMarket>();
	
	public static String GOLD = "Gold", OIL = "Oil", BITCOIN = "Bitcoin", BIGTECH = "Big Tech", STARTUPS = "Start-Ups";
	
	public static void init() {
		TheMarket.markets.put("Gold", new StockMarket("Gold Market", 1, Volatility.SAFE));
		TheMarket.markets.put("Oil", new StockMarket("Oil Market", 3, Volatility.VOLATILE));
		TheMarket.markets.put("Bitcoin", new StockMarket("Bitcoin Market", 10, Volatility.VOLATILE));
		TheMarket.markets.put("Big Tech", new StockMarket("Big Tech Market", 5, Volatility.SAFE));
		TheMarket.markets.put("Start-ups", new StockMarket("Start-Up Market", 2, Volatility.EXTREME));
	}
	
	public static void getMarket(String name) {
		try {
			TheMarket.markets.get(name);
		} catch (Exception e) {
			Logger.warn("Could not find the market you was looking for");
		}
	}
	
	public static void nextDay() {
		Iterator<StockMarket> itr = TheMarket.markets.values().iterator();
		while (itr.hasNext()) {
			itr.next().nextDay();
		}
		Player.mc.homepageController.stockMarketController.updateGraphs();
	}
	
	public static void buyValue(String name, double value) {
		try {
			TheMarket.markets.get(name).buyValue(value);
		} catch (Exception e) {
			Logger.warn("Could not find that market");
		}
	}
	
	public static boolean sellValue(String name, double value) {
		try {
			if (TheMarket.markets.get(name).getPositionValue() > value) {
				TheMarket.markets.get(name).buyValue(value);
				return true;
			} else {
				Logger.player("You do not have this much invested to sell");
				return false;
			}
		} catch (Exception e) {
			Logger.warn("Could not find that market");
			return false;
		}
	}
	
	
	
}

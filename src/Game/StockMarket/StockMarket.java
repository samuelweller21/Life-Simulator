package Game.StockMarket;

import net.sourceforge.jdistlib.Normal;
import net.sourceforge.jdistlib.rng.MersenneTwister;

/*
 * NB This is a terrible implementation of a stock market.  Highly volatile stocks tend to end at 0.  I should implement Brownian Motion
 */

public class StockMarket {

	//Properties
	private String marketName = "Unnamed market";
	private double averageGrowth;
	private double averageVariance;
	private int duplicateAvoider;
	
	public StockMarket(String name, double avgGrowth, double vol) {
		this.marketName = name;
		this.averageGrowth = avgGrowth/100;
		this.averageVariance = avgGrowth*vol;
		duplicateAvoider = (int)System.currentTimeMillis();
	}
	
	public double getGrowthTick() {
		duplicateAvoider++;
		return Normal.random(1, averageGrowth, averageVariance, new MersenneTwister(duplicateAvoider))[0]; 
	}
	
	public double getNextTick(double money) {
		duplicateAvoider++;
		return money*Normal.random(1, averageGrowth, averageVariance, new MersenneTwister(duplicateAvoider))[0]; 
	}
	
	
	public static void main(String[] args) {
		StockMarket sm = new StockMarket("Test", 2, Volatility.EXTREME);
		double money = 100;
		for (int i = 0; i < 50; i++) {
			double growth = sm.getGrowthTick();
			money *= (1+growth);
			System.out.println(money);
		}
	}
	
	
}

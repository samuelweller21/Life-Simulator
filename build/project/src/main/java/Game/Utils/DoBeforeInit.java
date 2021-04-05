package main.java.Game.Utils;

import main.java.Game.StockMarket.TheMarket;

public class DoBeforeInit {

	public static void run() {
		Utils.ss.loadSounds();
		TheMarket.init();
	}
	
}

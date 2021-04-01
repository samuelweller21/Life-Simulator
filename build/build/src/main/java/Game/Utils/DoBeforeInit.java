package main.java.Game.Utils;

import main.java.Game.StockMarket.TheMarket;

public class DoBeforeInit {

	public static void run() {
		SoundSystem.loadSoundsFromNamedFolder("sounds");
		TheMarket.init();
	}
	
}

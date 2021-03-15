package main.java.Game.Jobs;

import main.java.Game.Utils.Logger;

public class BinCollector extends Job {

	
	public BinCollector() {
		super();
		this.name = "Bin Collector";
		this.wage = 5;
		this.reqSkills = null;
	}
	
	@Override
	public double work() {
		Logger.info("Worked as a bin collector");
		return wage;
	}
}

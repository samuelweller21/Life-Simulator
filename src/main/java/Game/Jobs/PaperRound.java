package main.java.Game.Jobs;

import main.java.Game.Character.Skills;

public class PaperRound extends Job {

	public PaperRound() {
		this.name = "Paper Round";
		this.wage = 6;
		this.reqSkills = new Skills();
		this.reqSkills.addCardiovascular(10);
	}
	
}

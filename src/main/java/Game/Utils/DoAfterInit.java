package main.java.Game.Utils;

import javafx.collections.ListChangeListener;
import main.java.Game.Character.Player;
import main.java.Game.Character.Skills;
import main.java.Game.House.Houses;
import main.java.Game.Items.Item;
import main.java.Game.Items.Items;
import main.java.Game.Jobs.Job;
import main.java.Game.Jobs.Jobs;

public class DoAfterInit {

	public static void run() {
		
		//Load items
		
		Items.addToGlobalAsNamedList("Items");
		
		//Load jobs
		
		
		Jobs testJobs = new Jobs("Test");
		testJobs.getJobsFromNamedList("Jobs");
		testJobs.add(new Job("Test job", 10, new Skills()));
		Logger.info(testJobs.toString());
		Player.setJob(Jobs.getJobFromGlobal("Gardener"));
		
		//Test player logger
		
		Logger.log("Wage: " + Utils.getJobWage(Jobs.getJobFromGlobal("Gardener")));
		for (int i = 0; i < 100; i++) {
			Logger.player("Hello");
		}
		
		//Load houses
		Houses houses = new Houses();
		houses.addToGlobalAsNamedList("Houses");
		Player.mc.homepageController.homePaneController.homeImage.setImage(Houses.getFromGlobal("Homeless").get().getImage());
		Player.mc.homepageController.estateAgentController.loadHouses();
		
		//Start playing sounds
		SoundSystem.setMute(false);
		
		//Testing
	}
	
}

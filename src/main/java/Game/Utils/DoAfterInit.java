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
		
		//N.b. Should probably put all this on another thread for speedier loading
		
		//Load items
		
		Items coolItems = new Items();
		coolItems.add(new Item("Cool Phone", 100,10));
		Player.items.add(new Item("Mobile Phone", 600, 1));
		Player.items.add(coolItems);
		Items.addToGlobal(new Item("My first global item", 10, 1));
		Player.items.add(Items.global);
		Items.addToGlobal(coolItems);
		Player.items.remove(new Item("Cool Phone", 100, 1));
		Player.items.remove(coolItems);
		coolItems.addAsNamedList("Items");
		Player.items.addAsNamedList("Items");
		
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
	}
	
}

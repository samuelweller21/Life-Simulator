package main.java.Game.Utils;

import main.java.Game.Character.Player;
import main.java.Game.Character.Skills;
import main.java.Game.Items.Item;
import main.java.Game.Items.Items;
import main.java.Game.Jobs.Job;
import main.java.Game.Jobs.Jobs;

public class DoAfterInit {

	public static void run() {
		Items coolItems = new Items();
		coolItems.add(new Item("Cool Phone", 100,10));
		Player.items.add(new Item("Mobile Phone", 600, 1));
		Player.items.add(coolItems);
		Player.items.addToGlobal(new Item("My first global item", 10, 1));
		Player.items.add(Player.items.global);
		Player.items.addToGlobal(coolItems);
		Player.items.remove(new Item("Cool Phone", 100, 1));
		Player.items.remove(coolItems);
		
		coolItems.addAsNamedList("Items");
		Player.items.addAsNamedList("Items");
		
		Jobs testJobs = new Jobs("Test");
		testJobs.getJobsFromNamedList("Jobs");
		testJobs.add(new Job("Test job", 10, new Skills()));
		Logger.info(testJobs.toString());
	}
	
}

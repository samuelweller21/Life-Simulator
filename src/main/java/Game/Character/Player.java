package main.java.Game.Character;

import java.text.DecimalFormat;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import main.java.Game.Activities.Activity;
import main.java.Game.Cars.Cars;
import main.java.Game.Cars.ThreeWheeler;
import main.java.Game.House.House;
import main.java.Game.Items.Item;
import main.java.Game.Items.Items;
import main.java.Game.Jobs.Job;
import main.java.Game.Utils.Clock;
import main.java.Game.Utils.Logger;
import main.java.Game.Utils.PlayerLog;
import main.java.UI.controllers.MainController;

public class Player {

	// Controller
	public static MainController mc;

	// Member variables
	public static StringProperty name = new SimpleStringProperty();
	public static IntegerProperty age = new SimpleIntegerProperty(); // TO DO: Update age with clock
	public static StringProperty s_age = new SimpleStringProperty(); // TO DO: Update age with clock

	public static String getName() {
		return name.get();
	}

	public static int getAge() {
		return age.get();
	}

	public static double getCash() {
		return cash.get();
	}

	// Attribs
	public static DoubleProperty cash = new SimpleDoubleProperty();
	public static StringProperty s_cash = new SimpleStringProperty();
	public static StringProperty houseName = new SimpleStringProperty("");

	// Skills
	public static Skills skills;

	// Constants
	final static private double initialCash = 100;
	final static private int initialSkillPoints = 10;
	final static private int initialAge = 18;

	// Car
	private static Cars cars;

	// Items
	public static Items items;

	// Job
	public static Job job;

	// Clock
	public static Clock clock;
	
	//House
	public static House home;

	public static void player_init(String name) {
		// Init attrib vars
		Player.cash.set(initialCash);
		Player.age.set(initialAge);
		Player.name.set(name);

		// Init skill vars
		Player.skills = new Skills(initialSkillPoints);

		// Init cars
		Player.cars = new Cars();
		Player.cars.buy(new ThreeWheeler());
		
		//Init job
		job = new Job();
		
		// Init items
		items = new Items();
		items.add(new Item("Mobile Phone", 600, 1));
		items.add(new Item("Glass", 2, 2));
	}

	public static void init(MainController mc) {
		Player.clock = new Clock();
		Player.addInternalBindings();
		Player.mc = mc;
	}

	public static void pay(double price) {
		if (Player.enoughCash(price)) {
			Player.cash.set(Player.cash.get() - price);
		} else {
			Logger.error(
					"You can requested to make a purchase you cannot afford - Make sure to call _canDo_ before making a purchase");
		}
	}
	
	public static void buyHouse(House house) {
		if (enoughCash(house.getPrice())) {
			Player.home = house;
			Player.mc.homepageController.homePaneController.homeImage.setImage(house.getImage());
			Player.cash.set(Player.cash.get() - house.getPrice());
			PlayerLog.log("You brought a " + house.getName() + " house");
			Player.houseName.set(house.getName());
		} else {
			PlayerLog.log("You do not have enough cash to buy that house");
		}
	}
	
	public static void buy(Item item, int quantity) {
		Player.pay(item.getS_Price()*quantity);
		Player.items.add(item, quantity);
	}

	public static void addInternalBindings() {
		Player.age.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				Player.s_age.set(newValue.toString());
			}
		});
		Player.cash.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				DecimalFormat formatter = new DecimalFormat("0.00");
				Player.s_cash.set("£" + formatter.format(cash.get()));
			}
		});

		// Initialise s_cash and s_age
		DecimalFormat formatter = new DecimalFormat(".00");
		Player.s_cash.set("£" + formatter.format(cash.get()));
		Player.s_age.set(Integer.toString(age.get()));

		Player.clock.init();
	}

	// TO DO: public Player(String name, int intelligence, int strength ... ) {}

	public static void initSkills(int totalSkillPoints) {
		// Gen skills vec
	}

	public static void doActivity(Activity activity) {
		if (Player.canDo(activity)) {
			Player.pay(activity.getPrice());
			Player.spendTime(activity.getTime());
			Player.addSkills(activity.getSkillsToAdd());
			Logger.player("Doing " + activity.getActivityName());
		}
	}

	public static void addSkills(Skills skillsToAdd) {
		Player.skills.addSkills(skillsToAdd);
	}

	public static void spendTime(int time) {
		if (Player.enoughTime(time)) {
			Player.clock.tick(time);
		}
	}

	public static String print() {
		StringBuilder sb = new StringBuilder();
		sb.append("Name: " + name + "\n");
		sb.append("Cash: £" + cash + "\n");
		sb.append("Skills: " + skills.toString() + "\n");
		sb.append("Garage: " + cars.toString());
		return sb.toString();
	}

	public static void work(int time) {
		if (Player.enoughTime(time)) {
			Logger.info(Player.name + " has worked as " + job.getJobName());
			Player.cash.set(cash.get() + (job.work() * time / 60));
			Player.clock.tick(time);
		}
	}

	public static void setJob(Job job) {
		Player.job.setName(job.getName());
		Player.job.setWage(job.getS_wage());
		PlayerLog.log("You got a job as " + job.getName());
		//Player.job.setReqSkills(job.getReqSkills());
	}

	public static Job getJob() {
		return Player.job;
	}

	public static boolean enoughCash(double price) {
		if (price > cash.doubleValue()) {
			// Can't buy
			if (!Player.mc.cashFlickerer.isRunning()) {
				Player.mc.cashFlickerer.restart();
			} else {
				Logger.warn("Thread is already running, is user spamming?");
			}
			return false;
		} else {
			// Can buy
			return true;
		}
	}
	
	public static House getHome() {
		return home;
	}

	public static boolean enoughTime(int time) {
		if (!Player.clock.enoughTime(time)) {
			// Can't buy
			if (!Player.mc.timeFlickerer.isRunning()) {
				Player.mc.timeFlickerer.restart();
			} else {
				Logger.warn("Thread is already running, is user spamming?");
			}
			return false;
		} else {
			// Can buy
			return true;
		}
	}
	
	public static void giveMoney(double money) {
		Player.cash.set(Player.cash.get() + money);
	}

	public static boolean canDo(double price, int time) {
		if (Player.enoughCash(price) && Player.enoughTime(time)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean canDo(Activity activity) {
		boolean cash = Player.enoughCash(activity.getPrice());
		boolean time = Player.enoughTime(activity.getTime());
		if (cash && time) {
			return true;
		} else {
			return false;
		}
	}
}

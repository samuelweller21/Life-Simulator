package main.java.Game.Utils;

import java.text.DecimalFormat;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import main.java.Game.Activities.Activity;
import main.java.Game.Character.Player;
import main.java.Game.StockMarket.TheMarket;

public class Clock {

	// Consts
	public final int END_OF_DAY = (23 * 60) + 30;

	public int day = 1;
	public int minute = 7 * 60;
	public int year = 1;
	public StringProperty dayProp = new SimpleStringProperty();
	public StringProperty minuteProp = new SimpleStringProperty();
	public StringProperty yearProp = new SimpleStringProperty();
	

	public void nextMorning(int wakeTime) {
		day++;
		if (day > 365) {
			day = 1;
			year++;
			Player.age.set(Player.age.get()+1);
		}
		minute = wakeTime;
		updateBindings();
		TheMarket.nextDay();
	}

	public void nextMorning() {
		day++;
		if (day > 365) {
			day = 1;
			year++;
			Player.age.set(Player.age.get()+1);
		}
		minute = 7 * 60;
		updateBindings();
		if (Player.age.get() > 99) {
			PlayerLog.log("Congrulations on finishing - You may continue playing");
			//Could remove homepage and add a blank page showing stats and say something like "you are dead - thanks for playing"
		}
		TheMarket.nextDay();
	}

	public void init() {
		updateBindings();
	}

	public void tick(int time) {
		minute += time;
		while (minute >= 24 * 60) {
			day++;
			minute -= 24 * 60;
		}
		updateBindings();
	}

	public void updateBindings() {
		dayProp.set(getDay());
		minuteProp.set(getTime());
		yearProp.set(getYear());
	}

	public String getYear() {
		return Integer.toString(year);
	}
	
	public String getDay() {
		return (Integer.toString(day));
	}

	public String getTime() {
		DecimalFormat formatter = new DecimalFormat("00");
		String d_hour = formatter.format((minute) / 60);
		String d_minute = formatter.format(minute % 60);
		return (d_hour + ":" + d_minute);
	}

	@Override
	public String toString() {
		DecimalFormat formatter = new DecimalFormat("00");
		String d_hour = formatter.format((minute) / 60);
		String d_minute = formatter.format(minute % 60);
		return ("Day " + day + " - " + "Time " + d_hour + ":" + d_minute);
	}

	public static int toTime(int hours, int minutes) {
		return (60 * hours) + minutes;
	}

	public boolean enoughTime(Activity activity) {
		if (minute + activity.getTime() < END_OF_DAY) {
			return true;
		} else {
			return false;
		}
	}

	public boolean enoughTime(int time) {
		if (minute + time < END_OF_DAY) {
			return true;
		} else {
			return false;
		}
	}

}

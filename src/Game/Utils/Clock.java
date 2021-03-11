package Game.Utils;

import java.text.DecimalFormat;

import Game.Activities.Activity;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Clock {

	// Consts
	public final int END_OF_DAY = (23 * 60) + 30;

	public int day = 1;
	public int minute = 7 * 60;
	public StringProperty dayProp = new SimpleStringProperty();
	public StringProperty minuteProp = new SimpleStringProperty();

	public void nextMorning(int wakeTime) {
		day++;
		minute = wakeTime;
		updateBindings();
	}

	public void nextMorning() {
		day++;
		minute = 7 * 60;
		updateBindings();
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
		Logger.info("Moved forward " + time + " minutes");
	}

	public void updateBindings() {
		dayProp.set(getDay());
		minuteProp.set(getTime());
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
			Logger.info("Enough time for - " + activity.getActivityName());
			return true;
		} else {
			Logger.info("Not enough time for - " + activity.getActivityName());
			return false;
		}
	}

	public boolean enoughTime(int time) {
		if (minute + time < END_OF_DAY) {
			Logger.info("Enough time for that activity");
			return true;
		} else {
			Logger.info("Not enough time for that activity");
			return false;
		}
	}

}

package Game.Activities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Activity {

	private int time;
	private double price;
	private String activityName = "Unnamed activity";
	public StringProperty labelProp = new SimpleStringProperty();
	public static final double FREE = 0;

	public Activity(String name, double price, int time) {
		this.price = price;
		this.time = time;
		this.activityName = name;
		labelProp.set(name);
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public int getTime() {
		return time;
	}

	public double getPrice() {
		return price;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}

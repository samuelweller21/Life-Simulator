package main.java.Game.Activities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import main.java.Game.Character.Skills;

public class Activity {

	private int time;
	private double price;
	private String activityName = "Unnamed activity";
	public StringProperty labelProp = new SimpleStringProperty();
	public static final double FREE = 0;
	public Skills skillsToAdd;

	public Skills getSkillsToAdd() {
		return skillsToAdd;
	}

	public void setSkillsToAdd(Skills skillsToAdd) {
		this.skillsToAdd = skillsToAdd;
	}

	public Activity(String name, double price, int time) {
		this.price = price;
		this.time = time;
		this.activityName = name;
		labelProp.set(name);
		skillsToAdd = new Skills(0);
	}

	public void addSkill(String skillName, int value) {
		skillsToAdd.addSkillPoint(skillName, value);

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

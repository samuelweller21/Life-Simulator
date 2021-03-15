package main.java.Game.Jobs;

import java.util.HashMap;

import main.java.Game.Utils.Logger;

public class Job {

	protected String name;
	protected double wage;
	protected HashMap<String, Integer> reqSkills;
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Name: " + name + ", ");
		sb.append("Pay: " + wage);
		sb.append("Requirements: " + reqSkills.toString());
		return sb.toString();
	}
	
	public String getJobName() {
		return name;
	}
	
	public double work() {
		Logger.warn("No work function implemented");
		return wage;
	}
	
	public double getPay() {
		Logger.warn("Warning: No getPay method implemented");
		return wage;
	}
	
}

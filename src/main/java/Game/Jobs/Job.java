package main.java.Game.Jobs;

import java.util.HashMap;

import main.java.Game.Character.Skills;
import main.java.Game.Utils.Logger;

public class Job {

	protected String name;
	protected double wage;
	protected Skills reqSkills;
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Name: " + name + ", ");
		sb.append("Pay: " + wage);
		sb.append("Skills: " + reqSkills.toString());
		return sb.toString();
	}
	
	public Job(String name, double wage, Skills reqSkills) {
		this.name = name;
		this.wage = wage;
		this.reqSkills = reqSkills;
	}
	
	public Job() {
		reqSkills = new Skills();
	}
	
	public String getJobName() {
		return name;
	}
	
	public double work() {
		Logger.warn("No work function implemented");
		return wage;
	}
	
	public double getPay() {
		Logger.info("Worked as " + name);
		return wage;
	}
	
	public Skills getSkills() {
		return reqSkills;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setWage(double wage) {
		this.wage = wage;
	}
	
	public void setReqSkills(Skills reqSkills) {
		this.reqSkills = reqSkills;
	}
	
}

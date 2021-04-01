package main.java.Game.Jobs;

import javafx.beans.property.SimpleStringProperty;
import main.java.Game.Character.Skills;
import main.java.Game.Utils.Logger;
import main.java.Game.Utils.Utils;

public class Job {

	protected String s_name;
	public SimpleStringProperty name = new SimpleStringProperty("");
	protected double s_wage;
	public SimpleStringProperty wage = new SimpleStringProperty("");
	protected Skills reqSkills;
	protected SimpleStringProperty intelligence = new SimpleStringProperty("");
	protected SimpleStringProperty humour = new SimpleStringProperty("");
	protected SimpleStringProperty strength = new SimpleStringProperty("");
	protected SimpleStringProperty cardiovascular = new SimpleStringProperty("");
	
	public String getS_name() {
		return s_name;
	}
	
	public SimpleStringProperty getPropName() {
		return name;
	}
	
	public SimpleStringProperty getPropWage() {
		return wage;
	}

	public String getName() {
		return name.get();
	}

	public double getS_wage() {
		return s_wage;
	}

	public String getWage() {
		return wage.get();
	}

	public String getIntelligence() {
		return intelligence.get();
	}

	public String getHumour() {
		return humour.get();
	}

	public String getStrength() {
		return strength.get();
	}

	public String getCardiovascular() {
		return cardiovascular.get();
	}

	public Skills getReqSkills() {
		return reqSkills;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Name: " + s_name + ", ");
		sb.append("Pay: " + s_wage);
		sb.append("Skills: " + reqSkills.toString());
		return sb.toString();
	}
	
	public Job(String name, double wage, Skills reqSkills) {
		this.s_name = name;
		this.name.set(name);
		this.s_wage = wage;
		this.wage.set(Double.toString(wage));
		this.reqSkills = reqSkills;
		this.intelligence.set(Integer.toString(reqSkills.getIntelligence()));
		this.humour.set(Integer.toString(reqSkills.getHumour()));
		this.strength.set(Integer.toString(reqSkills.getStrength()));
		this.cardiovascular.set(Integer.toString(reqSkills.getCardiovascular()));
	}
	
	public Job() {
		this.reqSkills = new Skills();
		this.name.set("");
		this.wage.set("");
		this.intelligence.set(Integer.toString(reqSkills.getIntelligence()));
		this.humour.set(Integer.toString(reqSkills.getHumour()));
		this.strength.set(Integer.toString(reqSkills.getStrength()));
		this.cardiovascular.set(Integer.toString(reqSkills.getCardiovascular()));
	}
	
	public String getJobName() {
		return s_name;
	}
	
	public double work() {
		Logger.warn("No work function implemented");
		return s_wage;
	}
	
	public double getPay() {
		Logger.info("Worked as " + s_name);
		return s_wage;
	}
	
	public Skills getSkills() {
		return reqSkills;
	}
	
	public void setName(String name) {
		this.s_name = name;
		this.name.set(s_name);
	}
	
	public void setWage(double wage) {
		this.s_wage = wage;
		this.wage.set(Utils.formatAsPrice(s_wage));
	}
	
	public void setReqSkills(Skills reqSkills) {
		this.reqSkills = reqSkills;
		this.intelligence.set(Integer.toString(reqSkills.getIntelligence()));
		this.humour.set(Integer.toString(reqSkills.getHumour()));
		this.strength.set(Integer.toString(reqSkills.getStrength()));
		this.cardiovascular.set(Integer.toString(reqSkills.getCardiovascular()));
	}
	
}

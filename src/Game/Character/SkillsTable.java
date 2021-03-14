package Game.Character;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;

public class SkillsTable {

	SimpleStringProperty skill;
	String s_skill;
	SimpleStringProperty value;

	public SkillsTable(String skill, String value) {
		this.skill = new SimpleStringProperty(skill);
		s_skill = skill;
		this.value = new SimpleStringProperty(value);
	}

	public String getSSkill() {
		return s_skill;
	}

	public String getSkill() {
		return skill.get();
	}

	public String getValue() {
		return value.get();
	}

	public void setSkill(String skill) {
		this.skill.set(skill);
	}

	public void setValue(String value) {
		this.value.set(value);
	}

	public String toString() {
		return skill.toString() + "- " + value.toString();
	}

	public Observable valueProperty() {
		return value;
	}
}

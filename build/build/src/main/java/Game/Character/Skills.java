package main.java.Game.Character;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.Game.Utils.Logger;
import main.java.Game.Utils.Utils;

public class Skills {

	private HashMap<String, Integer> skills;
	public ObservableList<SkillsTable> skillsProp;
	public static final String INTELLIGENCE = "Intelligence", STRENGTH = "Strength", HUMOUR = "Humour",
			CARDIOVASCULAR = "Cardiovascular";
	
	public void addIntelligence(int value) {
		addSkillPoint(Skills.INTELLIGENCE, value);
	}
	
	public void addStrength(int value) {
		addSkillPoint(Skills.STRENGTH, value);
	}
	
	public void addHumour(int value) {
		addSkillPoint(Skills.HUMOUR, value);
	}
	
	public void addCardiovascular(int value) {
		addSkillPoint(Skills.CARDIOVASCULAR, value);
	}
	
	public void setIntelligence(int value) {
		setSkillPoint(Skills.INTELLIGENCE, value);
	}
	
	public void setStrength(int value) {
		setSkillPoint(Skills.STRENGTH, value);
	}
	
	public void setHumour(int value) {
		setSkillPoint(Skills.HUMOUR, value);
	}
	
	public void setCardiovascular(int value) {
		setSkillPoint(Skills.CARDIOVASCULAR, value);
	}
	
	public void setSkillPoint(String skill, int value) {
		if (skills.containsKey(skill)) {
			Logger.info(skill + " " + skills.get(skill) + " -> " + value);
			int prev = skills.get(skill);
			skills.put(skill, value);
			removeAndReplace(skillsProp, skill, prev, this.skills.get(skill));
		} else {
			Logger.error("There is no skill known as " + skill);
		}
	}
	
	public int getSkillPoint(String skill) {
		return skills.get(skill);
	}
	
	public int getIntelligence() {
		return getSkillPoint(Skills.INTELLIGENCE);
	}
	
	public int getStrength() {
		return getSkillPoint(Skills.STRENGTH);
	}
	
	public int getHumour() {
		return getSkillPoint(Skills.HUMOUR);
	}
	
	public int getCardiovascular() {
		return getSkillPoint(Skills.CARDIOVASCULAR);
	}

	// Allocates skill points randomly
	public Skills(int totalSkillPoints) {

		// Objects
		skills = new HashMap<String, Integer>();
		skillsProp = FXCollections.observableArrayList();
		// skillsProp = FXCollections.observableArrayList(e -> new Observable[] {
		// e.valueProperty() });

		// Pre-allocate
		skills.put(Skills.CARDIOVASCULAR, Utils.UNINITIATED_MEMORY);
		skills.put(Skills.HUMOUR, Utils.UNINITIATED_MEMORY);
		skills.put(Skills.INTELLIGENCE, Utils.UNINITIATED_MEMORY);
		skills.put(Skills.STRENGTH, Utils.UNINITIATED_MEMORY);

		// Get values
		int[] skillPointAssignment = Utils.arrayRandomTotalAllocation(skills.size(), totalSkillPoints);
		int j = 0;
		Iterator<Entry<String, Integer>> itr = skills.entrySet().iterator();

		while (itr.hasNext()) {
			Map.Entry<String, Integer> next = itr.next();
			skills.put(next.getKey(), skillPointAssignment[j]);
			skillsProp.add(new SkillsTable(next.getKey(), Integer.toString(skillPointAssignment[j])));
			j++;
		}

	}
	
	public Skills() {
		// Objects
		skills = new HashMap<String, Integer>();
		skillsProp = FXCollections.observableArrayList();
		// skillsProp = FXCollections.observableArrayList(e -> new Observable[] {
		// e.valueProperty() });

		// Pre-allocate
		skills.put(Skills.CARDIOVASCULAR, Utils.UNINITIATED_MEMORY);
		skills.put(Skills.HUMOUR, Utils.UNINITIATED_MEMORY);
		skills.put(Skills.INTELLIGENCE, Utils.UNINITIATED_MEMORY);
		skills.put(Skills.STRENGTH, Utils.UNINITIATED_MEMORY);
		
		//Now fill the property value
		Iterator<Entry<String, Integer>> itr = skills.entrySet().iterator();

		while (itr.hasNext()) {
			Map.Entry<String, Integer> next = itr.next();
			skillsProp.add(new SkillsTable(next.getKey(), Integer.toString(skills.get(next.getKey()))));
		}
	}

	private void removeAndReplace(ObservableList<SkillsTable> list, String skill, int oldValue, int newValue) {

		// Find the value in the skillsProp list
		skillsProp.stream().filter(st -> skill.equals(st.getSSkill())).findFirst().orElse(null)
				.setValue(Integer.toString(newValue));
		
		//Reorder
		Comparator<SkillsTable> comparator = Comparator.comparing(SkillsTable::getSkill);
		FXCollections.sort(skillsProp, comparator);
	}
	
	public void sort() {
		Comparator<SkillsTable> comparator = Comparator.comparing(SkillsTable::getSkill);
		FXCollections.sort(skillsProp, comparator);
	}
	
	public void addSkills(Skills skillsToAdd) {
		Iterator<Entry<String, Integer>> itr = skillsToAdd.skills.entrySet().iterator();

		while (itr.hasNext()) {
			Map.Entry<String, Integer> next = itr.next();
			if (next.getValue() != 0) {
				skills.put(next.getKey(), this.skills.get(next.getKey()) + next.getValue());
				removeAndReplace(skillsProp, next.getKey(), this.skills.get(next.getKey()) - next.getValue(),
						this.skills.get(next.getKey()));
				Logger.info(next.getKey() + " " + skills.get(next.getKey()) + " -> "
						+ (skills.get(next.getKey()) + next.getValue()));
			}
		}
	}

	public void addSkillPoint(String skill, int increment) {
		if (skills.containsKey(skill)) {
			Logger.info(skill + " " + skills.get(skill) + " -> " + (skills.get(skill) + increment));
			skills.put(skill, skills.get(skill) + increment);
			removeAndReplace(skillsProp, skill, this.skills.get(skill) - increment, this.skills.get(skill));
		} else {
			Logger.error("There is no skill known as " + skill);
		}
	}

	public HashMap<String, Integer> getSkills() {
		return skills;
	}
	
	public boolean isSufficient(Skills reqSkills) {
		Iterator<String> itr = skills.keySet().iterator();
		while (itr.hasNext()) {
			String currentKey = itr.next();
			if (skills.get(currentKey) < reqSkills.skills.get(currentKey)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return skills.toString();
	}
	
	
}

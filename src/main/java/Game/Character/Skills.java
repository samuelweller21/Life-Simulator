package main.java.Game.Character;

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

	// Allocates skill points randomly
	public Skills(int totalSkillPoints) {

		// Objects
		skills = new HashMap<String, Integer>();
		skillsProp = FXCollections.observableArrayList();
		// skillsProp = FXCollections.observableArrayList(e -> new Observable[] {
		// e.valueProperty() });

		// Pre-allocate
		skills.put(Skills.INTELLIGENCE, Utils.UNINITIATED_MEMORY);
		skills.put(Skills.STRENGTH, Utils.UNINITIATED_MEMORY);
		skills.put(Skills.HUMOUR, Utils.UNINITIATED_MEMORY);
		skills.put(Skills.CARDIOVASCULAR, Utils.UNINITIATED_MEMORY);

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
		skills.put(Skills.INTELLIGENCE, Utils.UNINITIATED_MEMORY);
		skills.put(Skills.STRENGTH, Utils.UNINITIATED_MEMORY);
		skills.put(Skills.HUMOUR, Utils.UNINITIATED_MEMORY);
		skills.put(Skills.CARDIOVASCULAR, Utils.UNINITIATED_MEMORY);
		
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

	@Override
	public String toString() {
		return skills.toString();
	}
}

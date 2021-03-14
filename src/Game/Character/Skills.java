package Game.Character;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import Game.Utils.Logger;
import Game.Utils.Utils;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class Skills {

	private HashMap<String, Integer> skills;
	public ObservableList<SkillsTable> skillsProp;
	public static final String INTELLIGENCE = "Intelligence", STRENGTH = "Strength", HUMOUR = "Humour",
			CARDIOVASCULAR = "Cardiovascular";

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

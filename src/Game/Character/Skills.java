package Game.Character;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import Game.Utils.Logger;
import Game.Utils.Utils;

public class Skills {

	private HashMap<String, Integer> skills;
	public static final String INTELLIGENCE = "Intelligence", STRENGTH = "Strength", HUMOUR = "Humour",
			CARDIOVASCULAR = "Cardiovascular";

	// Allocates skill points randomly
	public Skills(int totalSkillPoints) {

		// Objects
		skills = new HashMap<String, Integer>();

		// Pre-allocate
		skills.put("Intelligence", Utils.UNINITIATED_MEMORY);
		skills.put("Strength", Utils.UNINITIATED_MEMORY);
		skills.put("Humour", Utils.UNINITIATED_MEMORY);
		skills.put("Cardiovascular", Utils.UNINITIATED_MEMORY);

		// Get values
		int[] skillPointAssignment = Utils.arrayRandomTotalAllocation(skills.size(), totalSkillPoints);
		int j = 0;
		Iterator<Entry<String, Integer>> itr = skills.entrySet().iterator();

		while (itr.hasNext()) {
			Map.Entry<String, Integer> next = itr.next();
			skills.put(next.getKey(), skillPointAssignment[j]);
			j++;
		}

	}

	public void addSkills(Skills skillsToAdd) {
		Iterator<Entry<String, Integer>> itr = skillsToAdd.skills.entrySet().iterator();

		while (itr.hasNext()) {
			Map.Entry<String, Integer> next = itr.next();
			if (next.getValue() != 0) {
				this.skills.put(next.getKey(), this.skills.get(next.getKey()) + next.getValue());
				Logger.info(next.getKey() + " " + skills.get(next.getKey()) + " -> "
						+ (skills.get(next.getKey()) + next.getValue()));
			}
		}
	}

	public void addSkillPoint(String skill, int increment) {
		if (skills.containsKey(skill)) {
			Logger.info(skill + " " + skills.get(skill) + " -> " + (skills.get(skill) + increment));
			skills.put(skill, skills.get(skill) + increment);
		} else {
			Logger.error("There is no skill known as " + skill);
		}
	}

	@Override
	public String toString() {
		return skills.toString();
	}
}

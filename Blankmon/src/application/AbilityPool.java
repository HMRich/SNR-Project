package application;

import java.util.HashMap;

import application.enums.AbilityIds;

public class AbilityPool {

	private static HashMap<AbilityIds, Ability> mAbilities;

	public static Ability getAbility(AbilityIds ability) {
		if (mAbilities == null) {
			generateAbilities();
		}
		return mAbilities.get(ability);
	}

	private static void generateAbilities() {
		mAbilities = new HashMap<AbilityIds, Ability>();
	}

}

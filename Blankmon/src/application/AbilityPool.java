package application;

import java.util.HashMap;

import application.enums.Abilities;

public class AbilityPool {
	
	private static HashMap<Abilities, Ability> mAbilities;
	
	public static Ability getAbility(Abilities ability) {
		if(mAbilities == null) {
			generateAbilities();
		}
		return mAbilities.get(ability);
	}
	
	private static void generateAbilities() {
		mAbilities = new HashMap<Abilities, Ability>();
	}

}

package application;

import java.util.HashMap;

import Enums.Abilities;

public class AbilityPool {
	
	private static HashMap<Abilities, Ability> abilities;
	
	public static getAbility(Abilities ability) {
		if(abilities == null) {
			generateAbilities();
		}
		return abilities.
	}
	
	private static generateAbilities() {
		abilities = new HashMap<Abilities, Ability>();
		abilities
	}

}

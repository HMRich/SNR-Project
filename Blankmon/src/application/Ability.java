package application;

import application.enums.AbilityIds;

public class Ability {

	private AbilityIds mAbilityId;

	public Ability(AbilityIds abilityId) {
		mAbilityId = abilityId;
	}

	public AbilityIds getAbilityId() {
		return mAbilityId;
	}

	// public abstract void activateAbility(Pokemon target);

}
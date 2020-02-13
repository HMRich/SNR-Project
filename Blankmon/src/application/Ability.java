package application;

import application.enums.AbilityIds;

public abstract class Ability {

	private AbilityIds mAbilityId;

	public Ability(AbilityIds abilityId) {
		mAbilityId = abilityId;
	}

	public AbilityIds getAbilityId() {
		return mAbilityId;
	}

	public abstract void activateAbility(Anature target);

}
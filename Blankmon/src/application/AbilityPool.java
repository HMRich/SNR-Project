package application;

import java.util.HashMap;

import application.abillities.*;
import application.enums.AbilityIds;

public class AbilityPool
{
	private static HashMap<AbilityIds, Ability> mAbilities;

	public static Ability getAbility(AbilityIds abilityId)
	{
		if (mAbilities == null)
		{
			generateAbilities();
		}
		return mAbilities.get(abilityId);
	}

	private static void generateAbilities()
	{
		mAbilities = new HashMap<AbilityIds, Ability>();
		mAbilities.put(AbilityIds.Intimidate, new Intimidate());
		mAbilities.put(AbilityIds.Spiky, new Spiky()); 
		mAbilities.put(AbilityIds.Determined, new Determined()); 
	}
}

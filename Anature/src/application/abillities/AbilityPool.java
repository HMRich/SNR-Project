package application.abillities;

import java.util.HashMap;

import application.enums.AbilityIds;

public class AbilityPool
{
	private static HashMap<AbilityIds, Ability> mAbilities;

	public static Ability getAbility(AbilityIds abilityId)
	{
		if(mAbilities == null)
		{
			generateAbilities();
		}
		
		return mAbilities.get(abilityId);
	}

	private static void generateAbilities()
	{
		mAbilities = new HashMap<AbilityIds, Ability>();
		mAbilities.put(AbilityIds.Tyrannize , new Tyrannize());
		mAbilities.put(AbilityIds.Spiky, new Spiky());
		mAbilities.put(AbilityIds.Determination, new Determination());
		mAbilities.put(AbilityIds.ToughSkin, new Determination());
	}
}

package application.pools;

import java.util.HashMap;

import application.anatures.abillities.Ability;
import application.anatures.abillities.Determination;
import application.anatures.abillities.Spiky;
import application.anatures.abillities.Tyrannize;
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
	}
}

package application.pools;

import java.util.HashMap;

import application.anatures.abillities.Determination;
import application.anatures.abillities.NullAbility;
import application.anatures.abillities.Spiky;
import application.anatures.abillities.Tyrannize;
import application.enums.AbilityIds;
import application.interfaces.IAbility;

public class AbilityPool
{
	private static HashMap<AbilityIds, IAbility> mAbilities;

	public static IAbility getAbility(AbilityIds abilityId)
	{
		if(mAbilities == null)
		{
			generateAbilities();
		}
		
		return mAbilities.get(abilityId);
	}

	private static void generateAbilities()
	{
		mAbilities = new HashMap<AbilityIds, IAbility>();
		mAbilities.put(AbilityIds.Tyrannize , new Tyrannize());
		mAbilities.put(AbilityIds.Spiky, new Spiky());
		mAbilities.put(AbilityIds.Determination, new Determination());
		mAbilities.put(AbilityIds.NullAbility, NullAbility.getNullAbility());
	}
}

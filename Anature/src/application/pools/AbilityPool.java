package application.pools;

import java.util.Collection;
import java.util.HashMap;

import application.anatures.abillities.Determination;
import application.anatures.abillities.LittleGuy;
import application.anatures.abillities.Overclocked;
import application.anatures.abillities.Spiky;
import application.anatures.abillities.Toxic;
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

	public static Collection<IAbility> getAbilities()
	{
		if(mAbilities == null)
		{
			generateAbilities();
		}

		return mAbilities.values();
	}

	private static void generateAbilities()
	{
		mAbilities = new HashMap<AbilityIds, IAbility>();
		mAbilities.put(AbilityIds.Tyrannize, new Tyrannize());
		mAbilities.put(AbilityIds.Spiky, new Spiky());
		mAbilities.put(AbilityIds.Determination, new Determination());
		mAbilities.put(AbilityIds.LittleGuy, new LittleGuy());
		mAbilities.put(AbilityIds.Toxic, new Toxic());
		mAbilities.put(AbilityIds.Overclocked, new Overclocked());
	}
}

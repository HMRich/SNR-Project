package application;

import java.util.HashMap;

import application.abillities.Intimidate;
import application.enums.AbilityIds;
import application.enums.LoggingTypes;
import application.controllers.LoggerController;;

public class AbilityPool
{
	private static HashMap<AbilityIds, Ability> mAbilities;

	public static Ability getAbility(AbilityIds abilityId)
	{
		if(mAbilities == null)
		{
			generateAbilities();
			LoggerController.logEvent(LoggingTypes.Default, "The AbilityPool has been Generated.");
		}
		return mAbilities.get(abilityId);
	}

	private static void generateAbilities()
	{
		mAbilities = new HashMap<AbilityIds, Ability>();
		mAbilities.put(AbilityIds.Intimidate, new Intimidate());
	}
}

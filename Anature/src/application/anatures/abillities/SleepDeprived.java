package application.anatures.abillities;

import application.controllers.LoggerController;
import application.enums.AbilityIds;
import application.enums.LoggingTypes;
import application.enums.StatusEffects;
import application.interfaces.IAbility;
import application.interfaces.IAnature;

public class SleepDeprived implements IAbility
{
	public static String activateAbility(IAnature userAnature)
	{
		if(userAnature == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "userAnature parameter in Determination was null.");
			return "";
		}

		String toReturn = "";

		if(userAnature.getStatus() == StatusEffects.Sleep)
		{
			toReturn = userAnature.getName() + " stayed awake do to its Sleep Deprived ability!";
		}

		return toReturn;
	}

	@Override
	public AbilityIds getAbilityId()
	{
		return AbilityIds.SleepDeprived;
	}

	@Override
	public String getAbilityName()
	{
		return "Sleep Deprived";
	}

	@Override
	public String getAbilityDescription()
	{
		return "This Anature cannot be put to sleep.";
	}

	@Override
	public boolean happensEveryTurn()
	{
		return true;
	}
}

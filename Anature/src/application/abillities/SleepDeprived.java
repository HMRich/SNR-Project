package application.abillities;

import application.controllers.LoggerController;
import application.enums.AbilityIds;
import application.enums.LoggingTypes;

public class SleepDeprived implements Ability
{
	public static void activateAbility()
	{
		// TODO once statuses are added implement this method
		LoggerController.logEvent(LoggingTypes.Error, "Not Yet Implemented: SleepDeprived");
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

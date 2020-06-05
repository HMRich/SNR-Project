package application.anatures.abillities;

import application.anatures.Anature;
import application.controllers.LoggerController;
import application.enums.AbilityIds;
import application.enums.LoggingTypes;
import application.interfaces.IAbility;

public class LittleGuy implements IAbility
{
	public static String activateAbility(Anature userAnature)
	{
		if(userAnature == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "userAnature parameter in LittleGuy was null.");
			return "";
		}

		userAnature.getStats().increaseTempEvasion();

		return userAnature.getName() + " increased it's evasion with its \"Little Guy\" ability!";
	}

	public AbilityIds getAbilityId()
	{
		return AbilityIds.LittleGuy;
	}

	public String toString()
	{
		return "Little Guy";
	}

	public String getAbilityDescription()
	{
		return "User gets a boost to evasion on entry.";
	}

	public boolean happensEveryTurn()
	{
		return false;
	}
}
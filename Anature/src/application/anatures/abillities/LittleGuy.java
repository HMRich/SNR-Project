package application.anatures.abillities;

import application.controllers.LoggerController;
import application.enums.AbilityIds;
import application.enums.LoggingTypes;
import application.interfaces.IAbility;
import application.interfaces.IAnature;

public class LittleGuy implements IAbility
{
	private static final long serialVersionUID = -332976440197763016L;

	public static String activateAbility(IAnature userAnature)
	{
		if(userAnature == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "userAnature parameter in LittleGuy was null.");
			return "";
		}

		userAnature.getStats().increaseTempEvasion();

		return userAnature.getName() + " increased it's evasion with its \"Little Guy\" ability!";
	}

	@Override
	public AbilityIds getAbilityId()
	{
		return AbilityIds.LittleGuy;
	}

	@Override
	public String toString()
	{
		return "Little Guy";
	}

	@Override
	public String getAbilityDescription()
	{
		return "User gets a boost to evasion on entry.";
	}

	@Override
	public boolean happensEveryTurn()
	{
		return false;
	}
}
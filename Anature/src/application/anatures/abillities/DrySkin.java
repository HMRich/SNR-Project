package application.anatures.abillities;

import application.controllers.LoggerController;
import application.enums.AbilityIds;
import application.enums.LoggingTypes;
import application.interfaces.IAbility;
import application.interfaces.IAnature;

public class DrySkin implements IAbility
{
	private static final long serialVersionUID = -8094396331629552627L;

	public static String activateAbility(IAnature userAnature)
	{
		if(userAnature == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "userAnature parameter in DrySkin was null.");
			return "";
		}

		return userAnature.getName() + " nullified the move with its Dry Skin ability!";
	}

	@Override
	public AbilityIds getAbilityId()
	{
		return AbilityIds.DrySkin;
	}

	@Override
	public String toString()
	{
		return "Dry Skin";
	}

	@Override
	public String getAbilityDescription()
	{
		return "The user is able to absorb all water moves, thus nullifying the moves affects!";
	}

	@Override
	public boolean happensEveryTurn()
	{
		return true;
	}
}

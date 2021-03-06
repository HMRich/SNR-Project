package application.anatures.abillities;

import application.controllers.LoggerController;
import application.enums.AbilityIds;
import application.enums.LoggingTypes;
import application.enums.Stat;
import application.interfaces.IAbility;
import application.interfaces.IAnature;
import application.interfaces.IMove;

public class Determination implements IAbility
{
	private static final long serialVersionUID = 1L;

	public static String activateAbility(IAnature userAnature, IMove move, double userOldHp)
	{
		if(hasNull(userAnature, move, userOldHp))
		{
			return "";
		}

		if(move.doesDamage()
				&& (userOldHp == userAnature.getStats().getTotalStat(Stat.HitPoints)))
		{
			userAnature.getStats().setCurrentHitPoints(1);
			return userAnature.getName() + " survived on 1 hp thanks to their Determination!";
		}

		return "";
	}

	private static boolean hasNull(IAnature userAnature, IMove move, double userOldHp)
	{
		if(userAnature == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "userAnature parameter in Determination was null.");
			return true;
		}

		else if(move == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "move parameter in Determination was null.");
			return true;
		}

		return false;
	}

	@Override
	public AbilityIds getAbilityId()
	{
		return AbilityIds.Determination;
	}

	@Override
	public String toString()
	{
		return "Determination";
	}

	@Override
	public String getAbilityDescription()
	{
		return "Makes the Anature unable to be defeated in a single attack.";
	}

	@Override
	public boolean happensEveryTurn()
	{
		return false;
	}
}
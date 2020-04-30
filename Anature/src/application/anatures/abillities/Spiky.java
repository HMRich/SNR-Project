package application.anatures.abillities;

import application.controllers.LoggerController;
import application.enums.AbilityIds;
import application.enums.LoggingTypes;
import application.interfaces.IAbility;
import application.interfaces.IAnature;
import application.interfaces.IMove;

public class Spiky implements IAbility
{
	public static String activateAbility(IAnature userAnature, IAnature targetAnature, IMove moveThatAttacked, boolean isUserAttacking, boolean attackMissed)
	{
		String result = "";

		if(!hasNull(userAnature, targetAnature, moveThatAttacked) && moveThatAttacked.isPhysicalAttack() && !isUserAttacking && !attackMissed)
		{
			int newHp = targetAnature.getCurrentHitPoints() - (targetAnature.getTotalHitPoints() / 8);

			if(newHp < 0)
			{
				newHp = 0;
			}

			targetAnature.updateCurrentHitPoints(newHp);
			result = targetAnature.getName() + " got hurt from attacking " + userAnature.getName() + "'s spikes!";
		}

		return result;
	}

	private static boolean hasNull(IAnature userAnature, IAnature targetAnature, IMove moveThatAttacked)
	{
		if(userAnature == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "userAnature parameter in Determination was null.");
			return true;
		}

		else if(targetAnature == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "targetAnature parameter in Determination was null.");
			return true;
		}

		else if(moveThatAttacked == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "moveThatAttacked parameter in Determination was null.");
			return true;
		}

		return false;
	}

	public AbilityIds getAbilityId()
	{
		return AbilityIds.Spiky;
	}

	public String getAbilityName()
	{
		return "Spiky";
	}

	public String getAbilityDescription()
	{
		return "The opposing Anature takes damage whenever they come into physical contact with this Anature.";
	}

	public boolean happensEveryTurn()
	{
		return true;
	}
}
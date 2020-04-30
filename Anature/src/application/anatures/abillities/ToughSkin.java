package application.anatures.abillities;

import application.controllers.LoggerController;
import application.enums.AbilityIds;
import application.enums.LoggingTypes;
import application.interfaces.IAbility;
import application.interfaces.IAnature;
import application.interfaces.IMove;

public class ToughSkin implements IAbility
{
	public static String activateAbility(IAnature userAnature, IMove moveThatAttacked, int userOldHp, boolean attackMissed)
	{
		if(userAnature == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "userAnature parameter in Tough Skin was null.");
			return "";
		}

		else if(moveThatAttacked == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "moveThatAttacked parameter in Tough Skin was null.");
			return "";
		}

		else if(moveThatAttacked != null && moveThatAttacked.isPhysicalAttack() && !attackMissed)
		{
			double damageDealt = userOldHp - userAnature.getCurrentHitPoints();
			userAnature.updateCurrentHitPoints(userOldHp - (int) (damageDealt * 0.8));

			return userAnature.getName() + " took less damage because of their Tough Skin!";
		}

		return "";
	}

	@Override
	public AbilityIds getAbilityId()
	{
		return AbilityIds.ToughSkin;
	}

	@Override
	public String getAbilityName()
	{
		return "Tough Skin";
	}

	@Override
	public String getAbilityDescription()
	{
		return "The Anature takes less physical damage from moves.";
	}

	@Override
	public boolean happensEveryTurn()
	{
		return true;
	}
}

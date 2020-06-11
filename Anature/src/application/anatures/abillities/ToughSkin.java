package application.anatures.abillities;

import application.anatures.Anature;
import application.controllers.LoggerController;
import application.enums.AbilityIds;
import application.enums.LoggingTypes;
import application.interfaces.IAbility;
import application.interfaces.IMove;

public class ToughSkin implements IAbility
{
	private static final long serialVersionUID = 8267526863103538413L;

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

		else if(moveThatAttacked != null
				&& moveThatAttacked.isPhysicalAttack()
				&& !attackMissed)
		{
			double damageDealt = userOldHp - userAnature.getStats().getCurrentHitPoints();
			userAnature.getStats().setCurrentHitPoints(userOldHp - (int) (damageDealt * 0.8));

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
	public String toString()
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

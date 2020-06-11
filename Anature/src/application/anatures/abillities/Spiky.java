package application.anatures.abillities;

import application.anatures.Anature;
import application.controllers.LoggerController;
import application.enums.AbilityIds;
import application.enums.LoggingTypes;
import application.enums.Stat;
import application.interfaces.IAbility;
import application.interfaces.IMove;

public class Spiky implements IAbility
{
	private static final long serialVersionUID = -6679381514412927390L;

	public static String activateAbility(Anature source, Anature target, IMove sourceMove, boolean isSourceAttacking, boolean attackMissed)
	{
		String result = "";

		if(!hasNull(source, target, sourceMove) && sourceMove.isPhysicalAttack() && !isSourceAttacking && !attackMissed)
		{
			int newHp = target.getStats().getCurrentHitPoints() - (target.getStats().getTotalStat(Stat.HitPoints) / 8);

			if(newHp < 0)
			{
				newHp = 0;
			}

			target.getStats().setCurrentHitPoints(newHp);
			result = target.getName() + " got hurt from attacking " + source.getName() + "'s spikes!";
		}

		return result;
	}

	private static boolean hasNull(Anature userAnature, Anature targetAnature, IMove moveThatAttacked)
	{
		if(userAnature == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "userAnature parameter in Spikey was null.");
			return true;
		}

		else if(targetAnature == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "targetAnature parameter in Spikey was null.");
			return true;
		}

		else if(moveThatAttacked == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "moveThatAttacked parameter in Spikey was null.");
			return true;
		}

		return false;
	}

	@Override
	public AbilityIds getAbilityId()
	{
		return AbilityIds.Spiky;
	}

	@Override
	public String toString()
	{
		return "Spiky";
	}

	@Override
	public String getAbilityDescription()
	{
		return "The opposing Anature takes damage whenever they come into physical contact with this Anature.";
	}

	@Override
	public boolean happensEveryTurn()
	{
		return true;
	}
}
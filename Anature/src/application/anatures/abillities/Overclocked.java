package application.anatures.abillities;

import application.anatures.Anature;
import application.controllers.LoggerController;
import application.enums.AbilityIds;
import application.enums.LoggingTypes;
import application.enums.TempStatsStages;
import application.enums.Type;
import application.interfaces.IAbility;
import application.interfaces.IMove;
import application.interfaces.stats.IStats;

public class Overclocked implements IAbility
{
	private static final long serialVersionUID = -3207815575987072866L;

	public static String activateAbility(Anature source, IMove moveThatAttacked)
	{
		if(hasNull(source, moveThatAttacked))
		{
			return "";
		}

		if(moveThatAttacked.getType() == Type.Electric)
		{
			IStats stats = source.getStats();

			TempStatsStages attackStage = stats.getTempAttack();
			TempStatsStages specialAttackStage = stats.getTempSpecialAttack();

			stats.increaseTempAttack();
			stats.increaseTempSpecialAttack();

			if(attackStage != stats.getTempAttack() || specialAttackStage != stats.getTempSpecialAttack())
			{
				return source.getName() + " became overclocked by the attack!";
			}
		}

		return "";
	}

	private static boolean hasNull(Anature source, IMove moveThatAttacked)
	{
		if(source == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "source parameter in Overclocked was null.");
			return true;
		}

		else if(moveThatAttacked == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "moveThatAttacked parameter in Overclocked was null.");
			return true;
		}

		return false;
	}

	@Override
	public AbilityIds getAbilityId()
	{
		return AbilityIds.Overclocked;
	}

	@Override
	public String toString()
	{
		return "Overclocked";
	}

	@Override
	public String getAbilityDescription()
	{
		return "The user gets an attack & special attack boost when hit with an Electric type move.";
	}

	@Override
	public boolean happensEveryTurn()
	{
		return true;
	}
}

package application.abillities;

import application.Anature;
import application.controllers.LoggerController;
import application.enums.AbilityIds;
import application.enums.LoggingTypes;
import application.moves.Move;

public class ToughSkin implements Ability
{
	public static String activateAbility(Anature userAnature, Move moveThatAttacked, int userOldHp)
	{
		if(userAnature == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "userAnature parameter in Determination was null.");
			return "";
		}
		
		else if(moveThatAttacked == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "moveThatAttacked parameter in Determination was null.");
			return "";
		}
		
		else if(moveThatAttacked != null && moveThatAttacked.isPhysicalAttack())
		{
			double damageDealt = userOldHp - userAnature.getCurrHp();
			userAnature.setCurrHp(userOldHp - (int) (damageDealt * 0.8));
			
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

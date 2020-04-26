package application.abillities;

import application.Anature;
import application.controllers.LoggerController;
import application.enums.AbilityIds;
import application.enums.LoggingTypes;
import application.moves.Move;

public class Spiky implements Ability
{
	public static String activateAbility(Anature userAnature, Anature targetAnature, Move moveThatAttacked, boolean isUserAttacking, boolean attackMissed)
	{
		String result = "";

		if(!hasNull(userAnature, targetAnature, moveThatAttacked) && moveThatAttacked.isPhysicalAttack() && !isUserAttacking && !attackMissed)
		{
			int newHp = targetAnature.getCurrHp() - (targetAnature.getTotalHp() / 8);
			
			if(newHp < 0)
			{
				newHp = 0;
			}
			
			targetAnature.setCurrHp(newHp);
			result = targetAnature.getName() + " got hurt from attacking " + userAnature.getName() + "'s spikes!";
		}
		
		return result;
	}
	
	private static boolean hasNull(Anature userAnature, Anature targetAnature, Move moveThatAttacked)
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
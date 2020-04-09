package application.abillities;

import application.Anature;
import application.enums.AbilityIds;
import application.moves.Move;

public class Determination implements Ability
{
	public static String activateAbility(Anature targetAnature, Move move, double oldHp)
	{
		if(move.doesDamage() && (move.getTotalMovePoints() >= targetAnature.getTotalHp()))
		{
			targetAnature.setCurrHp(1);
			return targetAnature.getName() + " survived on 1 heath thanks to their Determination!"; 
		}
		return "";
	}

	public AbilityIds getAbilityId()
	{
		return AbilityIds.Determination;
	}

	public String getAbilityName()
	{
		return "Determined";
	}

	public String getAbilityDescription()
	{
		return "Makes the Anature unable to be defeated in a single attack.";
	}

	public boolean happensEveryTurn()
	{
		return false;
	}
}
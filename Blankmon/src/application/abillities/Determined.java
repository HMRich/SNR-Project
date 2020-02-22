package application.abillities;

import application.Anature;
import application.enums.AbilityIds;
import application.moves.Move;

public class Determined
{
	public static void activateAbility(Anature targetAnature, Move move, double oldHp)
	{
		if(move.doesDamage() && (move.getTotalMovePoints() >= targetAnature.getTotalHp()))
		{
			targetAnature.setCurrHp(1);
		}
	}

	public static AbilityIds getAbilityId()
	{
		return AbilityIds.Determined;
	}

	public static String getAbilityName()
	{
		return "Determined";
	}

	public static boolean happensEveryTurn()
	{
		return false;
	}
}
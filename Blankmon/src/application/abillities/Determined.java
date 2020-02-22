package application.abillities;

import application.Ability;
import application.Anature;
import application.enums.AbilityIds;
import application.moves.Move;

public class Determined implements Ability
{
	public static void activateAbility(Anature targetAnature, Move move, double oldHp)
	{
		if(move.doesDamage() && (move.getTotalMovePoints() >= targetAnature.getTotalHp()))
		{
			targetAnature.setCurrHp(1);
		}
	}

	@Override
	public AbilityIds getAbilityId()
	{
		return AbilityIds.Determined;
	}

	@Override
	public String getAbilityName()
	{
		return "Determined";
	}

	@Override
	public boolean happensEveryTurn()
	{
		return false;
	}
}
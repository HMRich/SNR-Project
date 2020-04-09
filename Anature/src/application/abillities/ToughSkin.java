package application.abillities;

import application.Anature;
import application.enums.AbilityIds;
import application.moves.Move;

public class ToughSkin implements Ability
{
	public static String activateAbility(Anature attackingAnature, Anature targetAnature, Move move, double oldHp)
	{
		if(move.isPhysicalAttack())
		{
			double hpDifference = oldHp - targetAnature.getCurrHp();
			targetAnature.setCurrHp((int) (hpDifference * 0.80));
			return targetAnature.getName() + " took less damage because of their Tough Skin!";
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

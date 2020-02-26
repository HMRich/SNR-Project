package application.abillities;

import application.Anature;
import application.enums.AbilityIds;
import application.moves.Move;

public class Spiky implements Ability
{
	public static void activateAbility(Anature attackingAnature, Anature targetAnature, Move move)
	{
		if(move.isPhysicalAttack())
		{
			attackingAnature.setCurrHp(attackingAnature.getCurrHp() - (targetAnature.getTotalHp() / 8));
		}
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
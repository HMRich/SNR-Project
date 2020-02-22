package application.abillities;

import application.Ability;
import application.Anature;
import application.enums.AbilityIds;
import application.moves.Move;

public class Spiky implements Ability
{
	public static void activateAbility(Anature attackingAnature, Anature targetAnature, Move move)
	{
		if(move.isPhysicalAttack()) {
			attackingAnature.setCurrHp(attackingAnature.getCurrHp() - (targetAnature.getTotalHp()/8)); 
		}
	}

	@Override
	public AbilityIds getAbilityId()
	{
		return AbilityIds.Spiky;
	}

	@Override
	public String getAbilityName()
	{
		return "Spiky";
	}

	@Override
	public boolean happensEveryTurn()
	{
		return true;
	}
}
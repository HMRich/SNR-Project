package application.abillities;

import application.Anature;
import application.enums.AbilityIds;
import application.moves.Move;

public class Spiky
{
	public static void activateAbility(Anature attackingAnature, Anature targetAnature, Move move)
	{
		if(move.isPhysicalAttack()) {
			attackingAnature.setCurrHp(attackingAnature.getCurrHp() - (targetAnature.getTotalHp()/8)); 
		}
	}

	public static AbilityIds getAbilityId()
	{
		return AbilityIds.Spiky;
	}

	public static String getAbilityName()
	{
		return "Spiky";
	}

	public static boolean happensEveryTurn()
	{
		return true;
	}
}
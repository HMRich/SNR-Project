package application.abillities;

import application.Ability;
import application.Anature;
import application.enums.AbilityIds;
import application.moves.Move;

public class Spiky extends Ability
{
	public Spiky()
	{
		super(AbilityIds.Spiky, "Spiky", true);
	}
	
	public static void activateAbility(Anature attackingAnature, Anature targetAnature, Move move)
	{
		if(move.isPhysicalAttack()) {
			attackingAnature.setCurrHp(attackingAnature.getCurrHp() - (targetAnature.getTotalHp()/8)); 
		}
	}

	@Override
	public void activateAbility(Anature user, Anature target) {
		// TODO Auto-generated method stub
		
	}
}
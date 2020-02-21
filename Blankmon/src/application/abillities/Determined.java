package application.abillities;

import application.Ability;
import application.Anature;
import application.enums.AbilityIds;
import application.moves.Move;

public class Determined extends Ability
{
	public Determined()
	{
		super(AbilityIds.Determined, "Determined", true);
	}
	
	public static void activateAbility(Anature targetAnature, 
			Move move, double oldHp)
	{
		if(move.doesDamage() && (move.getTotalMovePoints() >= targetAnature.getTotalHp())) {
			
			targetAnature.setCurrHp(1);
		}
	}

	@Override
	public void activateAbility(Anature user, Anature target) {
		// TODO Auto-generated method stub
		
	}
}
package application.abillities;

import application.Ability;
import application.Anature;
import application.enums.AbilityIds;

public class Determined extends Ability
{
	public Determined()
	{
		super(AbilityIds.Determined, "Determined", true);
	}
	
	public void activateAbility(Anature user, Anature target)
	{
		/*
		 * if(damageDoneByOpponentAttack >= user.totalHp) {
		 * 	user.currentHp = 1;
		 * }
		 * Ability Result???
		 */
	}
}
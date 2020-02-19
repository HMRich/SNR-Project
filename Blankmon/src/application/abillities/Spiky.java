package application.abillities;

import application.Ability;
import application.Anature;
import application.enums.AbilityIds;

public class Spiky extends Ability
{
	public Spiky()
	{
		super(AbilityIds.Spiky, "Spiky", true);
	}
	
	public void activateAbility(Anature user, Anature target)
	{
		/*
		 * if(opponentMove.isPhysicalAttack) {
		 * 	target.mCurrHp = target.mCurrHp - (target.mTotalHp/8); 
		 * }
		 * Ability Result???
		 */
	}
}
package application.abillities;

import application.Ability;
import application.Anature;
import application.enums.AbilityIds;

public class Intimidate extends Ability
{
	public Intimidate()
	{
		super(AbilityIds.Intimidate, "Intimidate", false);
	}
	
	public void activateAbility(Anature user, Anature target)
	{
		System.out.println("Ability Intimidate"); // TODO
	}
}
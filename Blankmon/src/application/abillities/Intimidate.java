package application.abillities;

import application.Ability;
import application.Anature;
import application.enums.AbilityIds;

public class Intimidate extends Ability
{
	public Intimidate()
	{
		super(AbilityIds.Intimidate);
	}
	
	public void activateAbility(Anature target)
	{
		System.out.println("Ability Intimidate"); // TODO
	}
}

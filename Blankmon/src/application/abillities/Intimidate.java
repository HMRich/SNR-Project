package application.abillities;

import application.Ability;
import application.PICKNAMEmon;
import application.enums.AbilityIds;

public class Intimidate extends Ability
{
	public Intimidate()
	{
		super(AbilityIds.Intimidate);
	}
	
	public void activateAbility(PICKNAMEmon target)
	{
		System.out.println("Ability Intimidate"); // TODO
	}
}

package application.abillities;

import application.Ability;
import application.Anature;
import application.enums.AbilityIds;

public class Intimidate implements Ability
{
	public void activateAbility(Anature user, Anature target)
	{
		System.out.println("Ability Intimidate"); // TODO
	}

	@Override
	public AbilityIds getAbilityId()
	{
		return AbilityIds.Intimidate;
	}

	@Override
	public String getAbilityName()
	{
		return "Intimidate";
	}

	@Override
	public boolean happensEveryTurn()
	{
		return false;
	}
}
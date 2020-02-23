package application.abillities;

import application.Anature;
import application.enums.AbilityIds;

public class Intimidate implements Ability
{
	public void activateAbility(Anature user, Anature target)
	{
		System.out.println("Ability Intimidate"); // TODO
	}

	public AbilityIds getAbilityId()
	{
		return AbilityIds.Intimidate;
	}

	public String getAbilityName()
	{
		return "Intimidate";
	}

	public String getAbilityDescription()
	{
		return "Lowers the opposing Anature's Attack stat.";
	}

	public boolean happensEveryTurn()
	{
		return false;
	}
}
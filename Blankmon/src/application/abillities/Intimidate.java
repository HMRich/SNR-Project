package application.abillities;

import application.Anature;
import application.enums.AbilityIds;

public class Intimidate
{
	public static void activateAbility(Anature user, Anature target)
	{
		System.out.println("Ability Intimidate"); // TODO
	}

	public static AbilityIds getAbilityId()
	{
		return AbilityIds.Intimidate;
	}

	public static String getAbilityName()
	{
		return "Intimidate";
	}

	public static boolean happensEveryTurn()
	{
		return false;
	}
}
package application.abillities;

import application.Anature;
import application.enums.AbilityIds;

public class Tyrannize implements Ability
{
	public static String activateAbility(Anature target)
	{
		target.setTempAttack((int)(target.getTempAttack() * .9));
		return target.getName() + "'s attack was lowered!";
	}

	public AbilityIds getAbilityId()
	{
		return AbilityIds.Tyrannize;
	}

	public String getAbilityName()
	{
		return "Tyrannize";
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
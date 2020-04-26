package application.anatures.abillities;

import application.anatures.Anature;
import application.enums.AbilityIds;

public class Tyrannize implements Ability
{
	public static String activateAbility(Anature user, Anature target)
	{
		target.adjustTempAttack(-0.1);
		return target.getName() + "'s attack was lowered by " + user.getName() + "'s Tyrannize ability!";
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
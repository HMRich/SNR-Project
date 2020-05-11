package application.anatures.abillities;

import application.enums.AbilityIds;
import application.interfaces.IAbility;
import application.interfaces.IAnature;

public class Tyrannize implements IAbility
{
	public static String activateAbility(IAnature user, IAnature target)
	{
		target.adjustAttack(-0.1);
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
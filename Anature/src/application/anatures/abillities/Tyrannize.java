package application.anatures.abillities;

import application.anatures.Anature;
import application.enums.AbilityIds;
import application.interfaces.IAbility;

public class Tyrannize implements IAbility
{
	public static String activateAbility(Anature user, Anature target)
	{
		target.getStats().decreaseTempAttack();
		return target.getName() + "'s attack was lowered by " + user.getName() + "'s Tyrannize ability!";
	}

	public AbilityIds getAbilityId()
	{
		return AbilityIds.Tyrannize;
	}

	public String toString()
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
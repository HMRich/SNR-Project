package application.anatures.abillities;

import java.io.Serializable;

import application.enums.AbilityIds;
import application.interfaces.IAbility;
import application.interfaces.IAnature;

public class Tyrannize implements IAbility, Serializable
{
	private static final long serialVersionUID = -6607975800246823147L;

	public static String activateAbility(IAnature user, IAnature target)
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
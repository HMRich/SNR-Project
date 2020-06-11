package application.anatures.abillities;

import application.anatures.Anature;
import application.enums.AbilityIds;
import application.interfaces.IAbility;

public class Tyrannize implements IAbility
{
	private static final long serialVersionUID = -6607975800246823147L;

	public static String activateAbility(IAnature user, IAnature target)
	{
		target.getStats().decreaseTempAttack();
		return target.getName() + "'s attack was lowered by " + user.getName() + "'s Tyrannize ability!";
	}

	@Override
	public AbilityIds getAbilityId()
	{
		return AbilityIds.Tyrannize;
	}

	@Override
	public String toString()
	{
		return "Tyrannize";
	}

	@Override
	public String getAbilityDescription()
	{
		return "Lowers the opposing Anature's Attack stat.";
	}

	@Override
	public boolean happensEveryTurn()
	{
		return false;
	}
}
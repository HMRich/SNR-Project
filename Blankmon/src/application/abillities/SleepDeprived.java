package application.abillities;

import application.enums.AbilityIds;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class SleepDeprived implements Ability
{
	public static void activateAbility()
	{
		throw new NotImplementedException();
		// TODO once statuses are added implement this method
	}

	@Override
	public AbilityIds getAbilityId()
	{
		return AbilityIds.SleepDeprived;
	}

	@Override
	public String getAbilityName()
	{
		return "Sleep Deprived";
	}

	@Override
	public String getAbilityDescription()
	{
		return "This Anature cannot be put to sleep.";
	}

	@Override
	public boolean happensEveryTurn()
	{
		return true;
	}
}

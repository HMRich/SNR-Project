package application.anatures.abillities;

import application.enums.AbilityIds;
import application.interfaces.IAbility;

public class NullAbility implements IAbility
{
	private static IAbility mNullAbility = new NullAbility();
	
	public static IAbility getNullAbility()
	{
		return mNullAbility;
	}
	
	@Override
	public AbilityIds getAbilityId()
	{
		return AbilityIds.NullAbility;
	}

	@Override
	public String getAbilityName()
	{
		return "Null Ability";
	}

	@Override
	public String getAbilityDescription()
	{
		return "This ability literally does nothing.";
	}

	@Override
	public boolean happensEveryTurn()
	{
		return false;
	}
}

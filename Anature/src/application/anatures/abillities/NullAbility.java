package application.anatures.abillities;

import java.io.Serializable;

import application.enums.AbilityIds;
import application.interfaces.IAbility;

public class NullAbility implements IAbility, Serializable
{
	private static final long serialVersionUID = 6894102485855851274L;

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
	public String toString()
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

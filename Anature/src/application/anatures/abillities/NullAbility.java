package application.anatures.abillities;

import application.enums.AbilityIds;
import application.interfaces.IAbility;

public class NullAbility implements IAbility
{
	private static final long serialVersionUID = 6894102485855851274L;

	private static IAbility mNullAbility;

	public static IAbility getNullAbility()
	{
		if(mNullAbility == null)
		{
			mNullAbility = new NullAbility();
		}

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

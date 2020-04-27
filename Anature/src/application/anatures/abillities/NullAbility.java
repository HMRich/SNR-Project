package application.anatures.abillities;

import application.enums.AbilityIds;

public class NullAbility implements Ability
{
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

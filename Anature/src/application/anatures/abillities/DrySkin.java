package application.anatures.abillities;

import application.enums.AbilityIds;
import application.interfaces.IAbility;
import application.interfaces.IAnature;

public class DrySkin implements IAbility
{
	public static String activateAbility(IAnature userAnature)
	{
		return userAnature.getName() + " nullified the move with its Dry Skin ability!";
	}

	@Override
	public AbilityIds getAbilityId()
	{
		return AbilityIds.Dry_Skin;
	}

	@Override
	public String getAbilityName()
	{
		return "Dry Skin";
	}

	@Override
	public String getAbilityDescription()
	{
		return "The user is able to absorb all water moves, thus nullifying the moves affects!";
	}

	@Override
	public boolean happensEveryTurn()
	{
		return true;
	}
}

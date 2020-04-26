package application.abillities;

import application.Anature;
import application.enums.AbilityIds;

public class DrySkin implements Ability
{
	public static String activateAbility(Anature userAnature)
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
